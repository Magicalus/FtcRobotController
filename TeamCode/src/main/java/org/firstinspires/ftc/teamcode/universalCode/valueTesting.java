package org.firstinspires.ftc.teamcode.universalCode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class valueTesting extends LinearOpMode{
    private driveTrain wheels;
    private crane crane;
    Servo leftClawServo = hardwareMap.get(Servo.class, "leftClawServo");
    Servo rightClawServo = hardwareMap.get(Servo.class, "rightClawServo");
    @Override
    public void runOpMode() {
        crane = new crane(hardwareMap, false);
        wheels = new driveTrain(hardwareMap, this, crane);

        waitForStart();
        while(opModeIsActive()){
            telemetry.addData("Left Crane: ", crane.getCurrentLeftPosition());
            telemetry.addData("Right Crane: ", crane.getCurrentRightPosition());
            telemetry.update();
        }
    }
}