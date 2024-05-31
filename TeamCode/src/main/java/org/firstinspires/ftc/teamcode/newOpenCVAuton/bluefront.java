package org.firstinspires.ftc.teamcode.newOpenCVAuton;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.universalCode.universalOpMode;
import org.firstinspires.ftc.teamcode.universalCode.values;

import org.firstinspires.ftc.teamcode.supermanTESTER.Globals;
import org.firstinspires.ftc.teamcode.supermanTESTER.Location;


//@Disabled
@Autonomous(name="bluefront \uD83D\uDD35")
public class bluefront extends universalOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        setup();
        Globals.IS_AUTO = true;
        Globals.ALLIANCE = Location.BLUE;
        Globals.SIDE = Location.CLOSE;

        setOpModeType(0);
        wheels.isAuton();

        closeClaw();
        airplaneLauncher.setPosition(0);



        while (opModeInInit()) {
            telemetry.addLine("ready");
            telemetry.addData("position", propPipeline.getLocation());
            telemetry.update();
        }

        randomization = propPipeline.getLocation();
        portal.close();

        waitForStart();
        while(opModeIsActive() && !isStopRequested()){
            startTime = System.currentTimeMillis();
            wheels.resetEncoders();

            switch (randomization) {
                case LEFT:
                    telemetry.addData("Prop","left");
                    telemetry.update();

                    foward(-1050);

                    rotate(values.turn90DegreesCounterClockwise);

                    foward(-900);
                    side(-400);

                    rightClawServo.setPosition(values.rightClawOpen);
                    sleep(500);

                    foward(-300);

                    placePixel();
                    rotate(values.turn90DegreesClockwise);
                    sleep(50);
                    rotate(values.turn90DegreesClockwise);


                    side(-600);
                    foward(400);

                    leftClawServo.setPosition(values.leftClawOpen);
                    sleep(100);

                    foward(-200);
                    pickupPixel();

                    side(-1050);

                    break;
                case CENTER:
                    telemetry.addData("Prop","center");
                    telemetry.update();
                    foward(-1120);

                    rotate(values.turn90DegreesClockwise);
                    sleep(50);
                    rotate(values.turn90DegreesClockwise);

                    rightClawServo.setPosition(values.rightClawOpen);
                    sleep(100);


                    foward(-200);

                    rotate(values.turn90DegreesCounterClockwise);

                    placePixel();
                    foward(1350);

                    side(500);
                    foward(250);

                    leftClawServo.setPosition(values.leftClawOpen);
                    sleep(300);

                    foward(-250);
                    pickupPixel();

                    side(-1450);

                    //why joe I love you <3 THIS WAS MIGUEL I PROMISE
                    break;
                case RIGHT:
                    telemetry.addData("Prop","right");
                    telemetry.update();

                    foward(-1220);

                    side(100);

                    rotate(values.turn90DegreesCounterClockwise);

                    rightClawServo.setPosition(values.rightClawOpen);
                    sleep(100);

                    foward(-1250);
                    placePixel();

                    rotate(values.turn90DegreesCounterClockwise);
                    sleep(50);
                    rotate(values.turn90DegreesCounterClockwise);

                    side(650);
                    foward(330);

                    leftClawServo.setPosition(values.leftClawOpen);
                    sleep(100);

                    foward(-200);

                    neutral();
                    sleep(2500);

                    side(-1800);

                    foward(100);

                    break;
            }
            break;
        }


    }

}