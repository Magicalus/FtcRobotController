package org.firstinspires.ftc.teamcode.auton;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.universalCode.*;

@TeleOp(name="value testing", group="Linear Opmode")

public class TESTER extends LinearOpMode{
    private DcMotor.ZeroPowerBehavior brake = DcMotor.ZeroPowerBehavior.BRAKE;
    private DcMotor.ZeroPowerBehavior floatt =DcMotor.ZeroPowerBehavior.FLOAT;
    private DcMotor frontLeft, fr6ontRight, backLeft, backRight;
    private Servo rightClawRotator, leftClawRotator, rightClawServo, leftClawServo;

    private DcMotor leftHanger, rightHanger, leftCraneArm, rightCraneArm;

    private IMUInterface imu;
    
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "jarmy");

        leftHanger = hardwareMap.get(DcMotor.class, "leftHanger");
        rightHanger = hardwareMap.get(DcMotor.class, "rightHanger");



        rightClawRotator = hardwareMap.get(Servo.class, "rightClawRotator");
        leftClawRotator = hardwareMap.get(Servo.class, "leftClawRotator");
        
        rightClawServo = hardwareMap.get(Servo.class, "rightClawServo");
        leftClawServo = hardwareMap.get(Servo.class, "leftClawServo");
        

        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setTargetPosition(0);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setTargetPosition(0);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setTargetPosition(0);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setTargetPosition(0);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);


        leftHanger.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftHanger.setTargetPosition(0);
        leftHanger.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftHanger.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        rightHanger.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightHanger.setTargetPosition(0);
        rightHanger.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightHanger.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        craneMotors crane = new craneMotors(hardwareMap, 0);

        imu = new IMUInterface(hardwareMap);
        
        // craneArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // craneArm.setTargetPosition(0);
        // craneArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        // craneArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        
//        telemetry.addData("right position:", rightClawRotator.getPosition());
//        telemetry.addData("left position:", leftClawRotator.getPosition());
//        telemetry.addData("right position:", rightHanger.getCurrentPosition());
//        telemetry.addData("left position:", leftHanger.getCurrentPosition());
//        telemetry.addData("backLeft position:", frontLeft.getCurrentPosition());

//        telemetry.addData("backRight position:", frontRight.getCurrentPosition());
//        telemetry.addData("Left Crane Motor Position", crane.getCurrentLeftPosition());
//        telemetry.addData("Right Crane Motor Position", crane.getCurrentRightPosition());
        imu.resetYaw();
        telemetry.addData("Yaw: ", imu.getYaw());
        telemetry.update();
        waitForStart();
//        telemetry.addData("right position:", rightHanger.getCurrentPosition());
//        telemetry.addData("left position:", leftHanger.getCurrentPosition());
//        telemetry.addData("backLeft position:", frontLeft.getCurrentPosition());
//
//        telemetry.addData("backRight position:", frontRight.getCurrentPosition());
//        telemetry.addData("Left Crane Motor Position", crane.getCurrentLeftPosition());
//        telemetry.addData("Right Crane Motor Position", crane.getCurrentRightPosition());
        telemetry.addData("Yaw: ", imu.getYaw());
        telemetry.update();
        sleep(100000);







    }

}