package org.firstinspires.ftc.teamcode.universalCode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.hardware.TouchSensor;

public class craneMotors {
    private DcMotor leftCraneArm, rightCraneArm;

    private TouchSensor craneZero;

    public craneMotors(HardwareMap hardwareMap){
        leftCraneArm = hardwareMap.get(DcMotor.class, "leftCraneArm");
        leftCraneArm.setTargetPosition(0);
        leftCraneArm.setPower(0.5);
        leftCraneArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftCraneArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        rightCraneArm = hardwareMap.get(DcMotor.class, "rightCraneArm");
        rightCraneArm.setTargetPosition(0);
        rightCraneArm.setPower(0.5);
        rightCraneArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightCraneArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        //craneZero = hardwareMap.get(TouchSensor.class,"craneZero");
    }

    public craneMotors(HardwareMap hardwareMap, double power){
        leftCraneArm = hardwareMap.get(DcMotor.class, "leftCraneArm");
        leftCraneArm.setTargetPosition(0);
        leftCraneArm.setPower(power);
        leftCraneArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftCraneArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        rightCraneArm = hardwareMap.get(DcMotor.class, "rightCraneArm");
        rightCraneArm.setTargetPosition(0);
        rightCraneArm.setPower(power);
        rightCraneArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightCraneArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void setTargetPosition(int target){
        leftCraneArm.setTargetPosition(0 - target);
        rightCraneArm.setTargetPosition(target);
    }

    public void resetEncoders(){
        leftCraneArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightCraneArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftCraneArm.setTargetPosition(0);
        rightCraneArm.setTargetPosition(0);

        leftCraneArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightCraneArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void setPower(double power){
        leftCraneArm.setPower(power);
        rightCraneArm.setPower(power);
    }

    public int getCurrentLeftPosition() { return leftCraneArm.getCurrentPosition(); }

    public int getCurrentRightPosition() { return rightCraneArm.getCurrentPosition(); }

    public void zeroCheck(){}
}