package org.firstinspires.ftc.teamcode.drivecode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.universalCode.universalOpMode;
import org.firstinspires.ftc.teamcode.universalCode.values;

@TeleOp(name="Driver Mode", group="Linear Opmode")
//@Disabled
public class DriverMode extends universalOpMode {

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        wheels.setPower(1);
        values.craneByPower = true;

        setOpModeType(2);
        crane.clawAintBack();

        telemetry.update();

        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // Pushing the left stick forward MUST make robot go forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flip

        // Wait for the game to start (driver presses PLAY)
        telemetry.update();
        waitForStart();
        airplaneLauncher.setPosition(values.airplaneServoResting);
        crane.resetEncoders();
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");

            wheels.manualDrive(-gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x,
                    -gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x,
                    -gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x,
                    -gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x);


            if (gamepad1.left_bumper) {
                leftClawServo.setPosition(values.leftClawClosed);
            } else if (gamepad1.left_trigger > 0.4) {
                leftClawServo.setPosition(values.leftClawOpen);
            }

            if (gamepad1.right_bumper) {
                rightClawServo.setPosition(values.rightClawClosed);
            } else if (gamepad1.right_trigger > 0.4) {
                rightClawServo.setPosition(values.rightClawOpen);
            }

            if (gamepad1.a) {
                airplaneLauncher.setPosition(values.airplaneServoFired);
            } else if (gamepad1.b) {
                airplaneLauncher.setPosition((values.airplaneServoResting));
            }
            if (gamepad1.dpad_down) {
                wheels.setPower(0.25);
            } else if (gamepad1.dpad_up) {
                wheels.setPower(1);
            }
                
                
            /*
            Controller buttons use different labels, so
            A is the Blue Cross
            X is the Pink Square
            B is the Red Circle
            Y is the Green Triangle
            */
            if(gamepad1.right_trigger > 0.1 && values.craneByPower) {
                crane.move(-gamepad1.right_trigger);
            }else if(gamepad1.left_trigger > 0.1 && values.craneByPower){
                crane.move(gamepad1.left_trigger);
            }else if(values.craneByPower){
                crane.move(0);
            }

            telemetry.addData("Left Crane Motor Position", crane.getCurrentLeftPosition());
            telemetry.addData("Right Crane Motor Position", crane.getCurrentRightPosition());
            telemetry.update();

        }
    }
}
