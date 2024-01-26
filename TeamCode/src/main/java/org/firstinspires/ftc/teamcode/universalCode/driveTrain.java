package org.firstinspires.ftc.teamcode.universalCode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class driveTrain {
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor jarmy;

    private double speed = 0.7;

    private boolean auton = false;

    public driveTrain (HardwareMap hardwareMap){
        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        frontRight = hardwareMap.get(DcMotor.class, "frontRight");

        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        jarmy = hardwareMap.get(DcMotor.class, "jarmy");
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
        this.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
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
        this.moveByEncoder(frontLeft, target, 0.75);
        this.moveByEncoder(frontRight, target, 0.75);
        this.moveByEncoder(backLeft, target, 0.75);
        this.moveByEncoder(jarmy, target, 0.75);

        waitforwheels();
    }

    public void side(int target){
        this.moveByEncoder(frontLeft, target, 0.75);
        this.moveByEncoder(frontRight, -target, 0.75);
        this.moveByEncoder(backLeft, -target, 0.75);
        this.moveByEncoder(jarmy, target, 0.75);

        waitforwheels();
    }

    public void rotate(int target){
        this.moveByEncoder(frontLeft, target, 0.75);
        this.moveByEncoder(frontRight, -target, 0.75);
        this.moveByEncoder(backLeft, target, 0.75);
        this.moveByEncoder(jarmy, -target, 0.75);

        waitforwheels();
    }

    public void waitforwheels() {
        while(frontLeft.getCurrentPosition() != frontLeft.getTargetPosition() &&
                frontRight.getCurrentPosition() != frontRight.getTargetPosition() &&
                backLeft.getCurrentPosition() != backLeft.getTargetPosition() &&
                jarmy.getCurrentPosition() != jarmy.getTargetPosition());

        resetEncoders();
    }

    public void resetEncoders(){
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        jarmy.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
