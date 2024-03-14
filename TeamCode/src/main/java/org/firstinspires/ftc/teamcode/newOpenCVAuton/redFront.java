package org.firstinspires.ftc.teamcode.newOpenCVAuton;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.universalCode.crane;
import org.firstinspires.ftc.teamcode.universalCode.driveTrain;
import org.firstinspires.ftc.teamcode.universalCode.universalOpMode;
import org.firstinspires.ftc.teamcode.universalCode.values;

import org.firstinspires.ftc.vision.VisionPortal;

import org.firstinspires.ftc.teamcode.supermanTESTER.Globals;
import org.firstinspires.ftc.teamcode.supermanTESTER.Location;
import org.firstinspires.ftc.teamcode.supermanTESTER.PropPipeline;


//@Disabled
@Autonomous(name="redFront \uD83D\uDD34")
public class redFront extends universalOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        setup();
        Globals.IS_AUTO = true;
        Globals.ALLIANCE = Location.RED;
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

                    foward(-1300);

                    side(-100);

                    rotate(values.turn90DegreesClockwise);
                    //foward(100);

                    rightClawServo.setPosition(values.rightClawOpen);
                    sleep(100);

                    foward(-1200);
                    placePixel();

                    rotate(values.turn90DegreesCounterClockwise);
                    sleep(50);
                    rotate(values.turn90DegreesCounterClockwise);

                    side(-240);
                    foward(470);

                    leftClawServo.setPosition(values.leftClawOpen);
                    sleep(100);

                    pickupPixel();

                    foward(-200);

                    side(1800);

                    break;
                case CENTER:
                    telemetry.addData("Prop","center");
                    telemetry.update();
                    foward(-1120);

                    //rotate(values.turn90DegreesClockwise);
                    //pickupPixel();

                    //foward(300);

                    rotate(values.turn90DegreesCounterClockwise);
                    sleep(50);
                    rotate(values.turn90DegreesCounterClockwise);

                    rightClawServo.setPosition(values.rightClawOpen);
                    sleep(300);

                    //neutral();


                    foward(-200);

                    rotate(values.turn90DegreesClockwise);

                    placePixel();
                    foward(1350);

//                    pickupPixel();
//                    sleep(500);
                    //?


                    //side(300);

                    foward(200);
                    side(-200);

                    //side(-);

                    leftClawServo.setPosition(values.leftClawOpen);
                    sleep(300);

                    foward(-250);
                    pickupPixel();

                    side(1250);

                    //why joe I love you <3 THIS WAS MIGUEL I PROMISE
                    break;
                case RIGHT:
                    telemetry.addData("Prop","right");
                    telemetry.update();

                    foward(-1050);

                    rotate(values.turn90DegreesClockwise);

                    foward(-1000);
                    side(400);

                    rightClawServo.setPosition(values.rightClawOpen);
                    sleep(500);

                    foward(-200);

                    placePixel();
                    rotate(values.turn90DegreesCounterClockwise);
                    sleep(50);
                    rotate(values.turn90DegreesCounterClockwise);


                    side(800);
                    foward(350);

                    leftClawServo.setPosition(values.leftClawOpen);
                    sleep(500);

                    pickupPixel();
                    sleep(1000);

                    foward(-200);

                    side(1050);

                    break;
            }
            break;
        }


    }
}