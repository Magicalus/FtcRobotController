package org.firstinspires.ftc.teamcode.drivecode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.universalCode.craneMotors;
import org.firstinspires.ftc.teamcode.universalCode.driveTrain;
import org.firstinspires.ftc.teamcode.universalCode.values;

@TeleOp(name="Reset", group="Linear Opmode")
//@Disabled
public class reset extends LinearOpMode {

    //    private DcMotor leftCraneArm, rightCraneArm;
    private Servo leftClawRotator;
    private Servo rightClawRotator;

    private craneMotors crane;

    private driveTrain wheels;


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        wheels = new driveTrain(hardwareMap, this);
        wheels.setPower(1);

        crane = new craneMotors(hardwareMap);
        crane.resetEncoders();

        Servo airplaneLauncher = hardwareMap.get(Servo.class, "airplaneLauncher");
        airplaneLauncher.setPosition(values.airplaneServoResting);


        Servo leftClawServo = hardwareMap.get(Servo.class, "leftClawServo");
        Servo rightClawServo = hardwareMap.get(Servo.class, "rightClawServo");

        leftClawRotator = hardwareMap.get(Servo.class, "leftClawRotator");
        rightClawRotator = hardwareMap.get(Servo.class, "rightClawRotator");
        leftClawRotator.setPosition(values.pickupPixelLeftClawRotator);
        rightClawRotator.setPosition(values.pickupPixelRightClawRotator);

        //resets the zero position of the drawer slide motor
        DcMotor leftHanger = hardwareMap.get(DcMotor.class, "leftHanger");
        leftHanger.setDirection(DcMotorSimple.Direction.REVERSE);
        leftHanger.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftHanger.setTargetPosition(values.hangerResting);
        leftHanger.setPower(1);
        leftHanger.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        // Baller bro that's absolutely crazy
        // On god bro frfr

        //resets the zero position of the drawer slide motor
        DcMotor rightHanger = hardwareMap.get(DcMotor.class, "rightHanger");
        rightHanger.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightHanger.setTargetPosition(values.hangerResting);
        rightHanger.setPower(1);
        rightHanger.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
