package org.firstinspires.ftc.teamcode.drivecode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.universalCode.driveTrain;
import org.firstinspires.ftc.teamcode.universalCode.relayDriveTrain;
import org.firstinspires.ftc.teamcode.universalCode.universalOpMode;
import org.firstinspires.ftc.teamcode.universalCode.values;

@TeleOp(name="RELAY MODE", group="Linear Opmode")
//@Disabled
public class RelayMode extends LinearOpMode {

    @Override
    public void runOpMode() {
        relayDriveTrain wheels = new relayDriveTrain(hardwareMap, this);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        wheels.setPower(1);

        telemetry.update();

        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // Pushing the left stick forward MUST make robot go forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flip

        // Wait for the game to start (driver presses PLAY)
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");

            wheels.manualDrive(-gamepad1.left_stick_y + gamepad1.right_stick_x,
                    -gamepad1.left_stick_y - gamepad1.right_stick_x,
                    -gamepad1.left_stick_y + gamepad1.right_stick_x,
                    -gamepad1.left_stick_y - gamepad1.right_stick_x);

        }
    }
}
