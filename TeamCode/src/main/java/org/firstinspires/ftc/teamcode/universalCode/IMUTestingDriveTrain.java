package org.firstinspires.ftc.teamcode.universalCode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class IMUTestingDriveTrain {
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor jarmy;

    private IMUInterface imu;
    public double heading;
    public double targetHeading;

    private double fowardSpeed = 0.75;
    private double rotationSpeed = 0.3;
    private double sideSpeed = 0.75;

    private double rotationMargin = 5;
    private double speed = 1;

    private boolean auton = false;

    public IMUTestingDriveTrain(HardwareMap hardwareMap){
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

        targetHeading = 0;
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
        targetHeading = 0;
        waitForWheels(target, true);
    }

    private void continueFoward(int target){
        double leftPower = fowardSpeed - fowardSpeed * (Math.max(-imu.getYaw(), 0) / 90);
        double rightPower = fowardSpeed - fowardSpeed * (Math.max(imu.getYaw(), 0) / 90);
        frontLeft.setPower(leftPower);
        frontRight.setPower(rightPower);
        backLeft.setPower(leftPower);
        jarmy.setPower(rightPower);
    }

    public void side(int target){
        imu.resetYaw();
        targetHeading = 0;
        waitForWheels(target, false);
    }
    private void continueSide(int target){
        double frontLeftCorner = fowardSpeed - fowardSpeed * (Math.max(imu.getYaw(), 0) / 90);
        double frontRightCorner = - (fowardSpeed - fowardSpeed * (Math.max(imu.getYaw(), 0) / 90));
        frontLeft.setPower(frontLeftCorner);
        frontRight.setPower(frontRightCorner);
        backLeft.setPower(frontRightCorner);
        jarmy.setPower(frontLeftCorner);
    }

    public void rotate(int target){
        imu.resetYaw();
        heading = 0;
        this.setPower(0);
        this.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        if(target > 120){
            targetHeading = target - 100;
            waitForHeading();
            imu.resetYaw();
            heading = 0;
            targetHeading = 100;
            waitForHeading();
        }else if(target < -120){
            targetHeading = target + 100;
            waitForHeading();
            imu.resetYaw();
            targetHeading = - 100;
            waitForHeading();
        }
        targetHeading = target;
        waitForHeading();
    }
    public void continueRotate(){
        double leftPower = - Math.min(rotationSpeed * (targetHeading * 1.5 - heading / targetHeading), rotationSpeed);
//        double rightPower = Math.min(Math.abs(targetHeading - heading) / rotationSpeed * 45, rotationSpeed);
        double rightPower = - leftPower;
        frontLeft.setPower(leftPower);
        frontRight.setPower(rightPower);
        backLeft.setPower(leftPower);
        jarmy.setPower(rightPower);
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
                jarmy.getCurrentPosition() != jarmy.getTargetPosition()
        ) {
            if(foward){
                continueFoward(target);
            }else{
                continueSide(target);
            }
        }
        resetEncoders();
    }

    public boolean waitForHeading(){
        return !(heading < targetHeading + rotationMargin && heading > targetHeading - rotationMargin);
    }
    public void resetEncoders(){
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        jarmy.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
