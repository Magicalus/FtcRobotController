package org.firstinspires.ftc.teamcode.universalCode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Value Testing", group="Linear Opmode")
public class valueTesting extends universalOpMode{
    @Override
    public void runOpMode() {
        setup();
        wheels.setPower(0);
        slides.clawAintBack();
        waitForStart();
        while(opModeIsActive()){
            leftClawServo.setPosition(values.leftClawClosed);
            rightClawServo.setPosition(values.rightClawClosed);
            sleep(5000);
            leftClawServo.setPosition(values.leftClawOpen);
            rightClawServo.setPosition(values.rightClawOpen);
            sleep(5000);
        }
    }
}