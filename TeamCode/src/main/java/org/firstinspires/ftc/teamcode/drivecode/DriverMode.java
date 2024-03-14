package org.firstinspires.ftc.teamcode.drivecode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.universalCode.universalOpMode;
import org.firstinspires.ftc.teamcode.universalCode.values;

@TeleOp(name="Driver Mode", group="Linear Opmode")
//@Disabled
public class DriverMode extends universalOpMode {

    @Override
    public void runOpMode() {
        setup();
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        wheels.setPower(1);

        setOpModeType(2);
        slides.clawAintBack();

        telemetry.update();

        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // Pushing the left stick forward MUST make robot go forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flip

        // Wait for the game to start (driver presses PLAY)
        telemetry.update();
        waitForStart();
        airplaneLauncher.setPosition(values.airplaneServoResting);
        slides.resetEncoders();
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
            if((gamepad2.right_trigger > 0.05 || gamepad2.left_trigger > 0.05) && false) {
                slides.move(-gamepad2.right_trigger + gamepad2.left_trigger, true);
            }else if(gamepad2.a) {
                slides.move(0, false);
            }else if(gamepad2.x){
                slides.move(750, false);
            }else if(gamepad2.y) {
                slides.move(1700, false);
            }else if(gamepad2.b){
                slides.move(values.craneMax, false);
            }else{
                slides.move(slides.getCurrentLeftPosition(), false);
            }

            slides.craneMaintenance();

            telemetry.addData("Left Crane Motor Position", slides.getCurrentLeftPosition());
            telemetry.addData("Right Crane Motor Position", slides.getCurrentRightPosition());
            telemetry.update();

        }
    }
}
