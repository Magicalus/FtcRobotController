package org.firstinspires.ftc.teamcode.universalCode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;


public class driveTrain {
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor jarmy;

    public IMUInterface imu;
    public double headingOffset;
    public double targetHeading;

    private double fowardSpeed = 0.75;
    private double sideSpeed = 0.75;
    private double rotationSpeed = 0.5;

    private double lastAngle;
    private double currAngle = 0;

    public double rotationMargin = 1;
    private double speed = 1;

    private boolean auton = false;

    //Should only be true when testing with blue back if they move
    private boolean debug = false;

    private LinearOpMode opMode;

    private crane crane;

    public driveTrain(HardwareMap hardwareMap, universalOpMode opmode){
        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        //frontRight.setDirection(DcMotorSimple.Direction.REVERSE);

        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        jarmy = hardwareMap.get(DcMotor.class, "jarmy");
        //jarmy.setDirection(DcMotorSimple.Direction.REVERSE);

        imu = new IMUInterface(hardwareMap);

        lastAngle = imu.getYaw();

        targetHeading = 0;

        headingOffset = 0;

        opMode = opmode;

        this.crane = opmode.crane;
    }
    public void manualDrive(double frontLeftPower, double frontRightPower, double backLeftPower, double jarmyPower){
        frontLeft.setPower(frontLeftPower * speed);
        frontRight.setPower(frontRightPower * speed);
        backLeft.setPower(backLeftPower * speed);
        jarmy.setPower(jarmyPower * speed);
    }

    public void setPower(double newPower){
        if(auton){
            frontLeft.setPower(newPower);
            frontRight.setPower(newPower);
            backLeft.setPower(newPower);
            jarmy.setPower(newPower);
        }else{
            speed = newPower;
        }
    }

    public void setFowardSpeed(double newPower){
        fowardSpeed = newPower;
    }

    public void setMode(DcMotor.RunMode mode){
        frontLeft.setMode(mode);
        frontRight.setMode(mode);
        backLeft.setMode(mode);
        jarmy.setMode(mode);
    }

    public void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior behavior){
        frontLeft.setZeroPowerBehavior(behavior);
        frontRight.setZeroPowerBehavior(behavior);
        backLeft.setZeroPowerBehavior(behavior);
        jarmy.setZeroPowerBehavior(behavior);
    }

    public void setTargetPosition(int target){
        frontLeft.setTargetPosition(target);
        frontRight.setTargetPosition(target);
        backLeft.setTargetPosition(target);
        jarmy.setTargetPosition(target);
    }

    //Auton Shtuff
    public void isAuton(){
        auton = true;
        this.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.setTargetPosition(0);
        this.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //changed from zeropowerbehavior.float
        //brake makes it brake on zero power, resisting any change
    }

    public void moveByEncoder(DcMotor mot, int target, double power){
        mot.setPower(0);
        mot.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mot.setTargetPosition(0);
        mot.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mot.setPower(0);

        mot.setTargetPosition(target);
        mot.setPower(power);
    }

    public void foward(int target){
        imu.resetYaw();
        targetHeading = 0 + headingOffset;
        waitForWheels(target, true);
    }

    private void continueFoward(){
        double leftPower = fowardSpeed - fowardSpeed * (Math.max(imu.getYaw(), 0) / 90);
        double rightPower = fowardSpeed - fowardSpeed * (Math.max(-imu.getYaw(), 0) / 90);
        frontLeft.setPower(leftPower);
        frontRight.setPower(rightPower);
        backLeft.setPower(leftPower);
        jarmy.setPower(rightPower);
    }

    public void side(int target){
        imu.resetYaw();
        targetHeading = 0 + headingOffset;
        waitForWheels(target, false);
    }
    private void continueSide(){
        double frontLeftCorner = sideSpeed - sideSpeed * (Math.max(imu.getYaw(), 0) / 90);
        double frontRightCorner = - (sideSpeed - sideSpeed * (Math.max(-imu.getYaw(), 0) / 90));
        frontLeft.setPower(frontLeftCorner);
        frontRight.setPower(frontRightCorner);
        backLeft.setPower(frontRightCorner);
        jarmy.setPower(frontLeftCorner);
    }

    public void rotate(int target) {
        setPower(0);
        setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        imu.resetYaw();
        turnTo(-target);
        targetHeading = target + headingOffset;
        resetEncoders();
        opMode.sleep(100);

    }

    public void waitForWheels(int target, boolean foward) {
        if(foward){
            this.moveByEncoder(frontLeft, target, 0);
            this.moveByEncoder(frontRight, target, 0);
            this.moveByEncoder(backLeft, target, 0);
            this.moveByEncoder(jarmy, target, 0);
        }else {
            this.moveByEncoder(frontLeft, target, 0);
            this.moveByEncoder(frontRight, -target, 0);
            this.moveByEncoder(backLeft, -target, 0);
            this.moveByEncoder(jarmy, target, 0);
        }
        while(frontLeft.getCurrentPosition() != frontLeft.getTargetPosition() &&
                frontRight.getCurrentPosition() != frontRight.getTargetPosition() &&
                backLeft.getCurrentPosition() != backLeft.getTargetPosition() &&
                jarmy.getCurrentPosition() != jarmy.getTargetPosition() &&
                opMode.opModeIsActive()
        ) {
            crane.craneMaintenance();
            if(foward){
                continueFoward();
            }else{
                continueSide();
            }
            opMode.telemetry.addData("Yaw: ", imu.getYaw());
            opMode.telemetry.addData("frontLeft: ",frontLeft.getCurrentPosition());
            opMode.telemetry.addData("frontRight: ",frontRight.getCurrentPosition());
            opMode.telemetry.addData("backLeft: ",backLeft.getCurrentPosition());
            opMode.telemetry.addData("backRight: ", jarmy.getCurrentPosition());

            opMode.telemetry.addData("left draw slide", crane.getCurrentLeftPosition());
            opMode.telemetry.addData("claw spin", crane.getCurrentSpinniesPosition());

            opMode.telemetry.update();
        }
        resetEncoders();
    }
    public void resetEncoders(){
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        jarmy.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void turnTo(double degrees){

        double yaw = imu.getYaw();

        double error = degrees - yaw;

//        if (error > 180) {
//            error -= 360;
//        } else if (error < -180) {
//            error += 360;
//        }

        turn(error);
    }

    public void turn(double degrees){
        resetAngle();

        double error = degrees;
        ElapsedTime inMargin = new ElapsedTime();
        inMargin.reset();
        double motorPower = 0;

        while (opMode.opModeIsActive() && (Math.abs(error) > 0.5 || Math.abs(error) < 359.5) && inMargin.time() < 1) {
            crane.craneMaintenance();
            if(Math.abs(error) < 20 || Math.abs(error) > 340){
                rotationSpeed = 0.2;
            }else if(Math.abs(error) < 0.5 || Math.abs(error) > 359.5){
                inMargin.reset();
                rotationSpeed = 0.5;
            }
            motorPower = (error < 180 ? -rotationSpeed : rotationSpeed);
            frontLeft.setPower(-motorPower);
            frontRight.setPower(motorPower);
            backLeft.setPower(-motorPower);
            jarmy.setPower(motorPower);
            error -= getAngle();
            opMode.telemetry.addData("error", error);
            opMode.telemetry.update();
            crane.craneMaintenance();
        }

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        jarmy.setPower(0);
    }

    public double getAngle() {

        // Get current orientation
        double yaw = imu.getYaw();
        // Change in angle = current angle - previous angle
        double deltaAngle = yaw - lastAngle;

        // Gyro only ranges from -179 to 180
        // If it turns -1 degree over from -179 to 180, subtract 360 from the 359 to get -1
        if (deltaAngle < -180) {
            deltaAngle += 360;
        } else if (deltaAngle > 180) {
            deltaAngle -= 360;
        }

        // Add change in angle to current angle to get current angle
        currAngle += deltaAngle;
        lastAngle = yaw;
        opMode.telemetry.addData("gyro", yaw);
        return deltaAngle;
    }

    public void resetAngle() {
        lastAngle = imu.getYaw();
        currAngle = 0;
        imu.resetYaw();
    }
}
