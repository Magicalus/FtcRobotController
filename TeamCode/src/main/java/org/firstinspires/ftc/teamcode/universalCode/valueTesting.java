package org.firstinspires.ftc.teamcode.universalCode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Value Testing", group="Linear Opmode")
public class valueTesting extends universalOpMode{
    @Override
    public void runOpMode() {
        setup();
        wheels.setPower(0);
        waitForStart();
        while(opModeIsActive()){
            telemetry.addData("Left Crane: ", crane.getCurrentLeftPosition());
            telemetry.addData("Right Crane: ", crane.getCurrentRightPosition());
            telemetry.addData("Is Claw Back? ", crane.clawIsBack);
            telemetry.addData("Spinnies position: ", crane.getCurrentSpinniesPosition());
            telemetry.update();
            crane.craneMaintenance();
        }
    }
}