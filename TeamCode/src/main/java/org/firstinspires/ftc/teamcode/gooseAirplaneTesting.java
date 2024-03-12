package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Goose Plane Testing", group="Linear Opmode")
@Disabled
public class gooseAirplaneTesting extends LinearOpMode {
    @Override
    public void runOpMode(){
        Servo goosePlane = hardwareMap.get(Servo.class, "goosePlane");
        goosePlane.setPosition(0.6);
        waitForStart();
        goosePlane.setPosition(0);
        sleep(3000);
    }
}
