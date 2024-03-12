package org.firstinspires.ftc.teamcode.drivecode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.universalCode.universalOpMode;
import org.firstinspires.ftc.teamcode.universalCode.values;

@TeleOp(name="Reset", group="Linear Opmode")
//@Disabled
public class reset extends universalOpMode {

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        slides.resetEncoders();

        airplaneLauncher.setPosition(values.airplaneServoResting);
    }
}
