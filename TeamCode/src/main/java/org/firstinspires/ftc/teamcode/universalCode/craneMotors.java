package org.firstinspires.ftc.teamcode.universalCode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.hardware.TouchSensor;

public class craneMotors {
    private DcMotor leftDrawerSlide, rightDrawerSlide;

    private TouchSensor craneZero;

    public craneMotors(HardwareMap hardwareMap){
        leftDrawerSlide = hardwareMap.get(DcMotor.class, "leftCraneArm");
        leftDrawerSlide.setTargetPosition(0);
        leftDrawerSlide.setPower(0.5);
        leftDrawerSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftDrawerSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        rightDrawerSlide = hardwareMap.get(DcMotor.class, "rightCraneArm");
        rightDrawerSlide.setTargetPosition(0);
        rightDrawerSlide.setPower(0.5);
        rightDrawerSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrawerSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        //craneZero = hardwareMap.get(TouchSensor.class,"craneZero");
    }

    public craneMotors(HardwareMap hardwareMap, double power){
        leftDrawerSlide = hardwareMap.get(DcMotor.class, "leftCraneArm");
        leftDrawerSlide.setTargetPosition(0);
        leftDrawerSlide.setPower(power);
        leftDrawerSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftDrawerSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        rightDrawerSlide = hardwareMap.get(DcMotor.class, "rightCraneArm");
        rightDrawerSlide.setTargetPosition(0);
        rightDrawerSlide.setPower(power);
        rightDrawerSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrawerSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void setTargetPosition(int target){
        leftDrawerSlide.setTargetPosition(target);
        rightDrawerSlide.setTargetPosition(target);
    }

    public void resetEncoders(){
        leftDrawerSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrawerSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftDrawerSlide.setTargetPosition(0);
        rightDrawerSlide.setTargetPosition(0);

        leftDrawerSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrawerSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void craneRegular(){

    }
    public void setPower(double power){
        leftDrawerSlide.setPower(power);
        rightDrawerSlide.setPower(power);
    }

    public int getCurrentLeftPosition() { return leftDrawerSlide.getCurrentPosition(); }

    public int getCurrentRightPosition() { return rightDrawerSlide.getCurrentPosition(); }

    public void zeroCheck(){}
}