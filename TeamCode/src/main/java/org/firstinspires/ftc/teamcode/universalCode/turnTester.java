package org.firstinspires.ftc.teamcode.universalCode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Turn Testing", group="Linear Opmode")
public class turnTester extends universalOpMode {
    @Override
    public void runOpMode(){
        setup();
        waitForStart();
        slides.clawAintBack();
        while(opModeIsActive()){
            wheels.turnTo(-90);
            wheels.turnTo(-90);
            sleep(3000);
        }
    }
}
