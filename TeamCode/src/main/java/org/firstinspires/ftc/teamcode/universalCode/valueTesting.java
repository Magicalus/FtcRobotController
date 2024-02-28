package org.firstinspires.ftc.teamcode.universalCode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Value Testing", group="Linear Opmode")
public class valueTesting extends universalOpMode{
    @Override
    public void runOpMode() {
        values.craneByPower = false;
        setup(0);
        crane.clawAintBack();
        crane.resetClawSpinnies();
        wheels.setPower(0);
        waitForStart();
        crane.move(1000);
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