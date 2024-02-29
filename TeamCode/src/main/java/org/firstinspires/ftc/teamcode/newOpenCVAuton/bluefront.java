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

                    foward(-1250);

//                    pickupPixel();
//                    sleep(500);

                    rotate(values.turn90DegreesClockwise);

                    foward(900);

                    rightClawServo.setPosition(values.rightClawOpen);
                    sleep(500);

//                    neutral();
//                    sleep(500);
//                    rightClawServo.setPosition(values.rightClawClosed);

                    placePixel();

                    foward(800);
                    //this may or may not just fully bump the purple out

                    //side(150);
                    //sleep(1000);
                    //resetEncoders();

                    //foward(-100);
                    //sleep(1000);
                    //resetEncoders();

                    side(-800);

                    leftClawServo.setPosition(values.leftClawOpen);
                    sleep(500);

                    neutral();
                    sleep(1000);

                    foward(-200);

                    side(-1050);

                    foward(350);

                    break;
                case CENTER:
                    telemetry.addData("Prop","center");
                    telemetry.update();

                    foward(-820);

                    rotate(values.turn90DegreesClockwise * 2);

                    //pickupPixel();

                    foward(280);

                    rightClawServo.setPosition(values.rightClawOpen);
                    sleep(300);

//                    neutral();
//                    sleep(500);

                    foward(-100);

                    rotate(values.turn90DegreesCounterClockwise);

                    foward(1750);

                    placePixel();
                    sleep(1300);

//                    side(-500);

//                    foward(400);

                    //side(-);

                    leftClawServo.setPosition(1);
                    sleep(300);

                    neutral();
                    sleep(1300);
                    foward(-250);

                    side(-1850);
                    //originally side(-1350);

                    foward(450);

                    //why joe I love you <3 THIS WAS MIGUEL I PROMISE
                    break;
                case RIGHT:
                    telemetry.addData("Prop","right");
                    telemetry.update();

                    foward(-1300);
//                    side(200);

//                    pickupPixel();
//                    sleep(500);

                    rotate(values.turn90DegreesCounterClockwise);
                    foward(100);


//
//                    foward(150);
                    rightClawServo.setPosition(values.rightClawOpen);
                    sleep(700);

//                    neutral();
//                    sleep(500);

                    foward(-1800);
                    placePixel();

                    rotate(values.turn90DegreesCounterClockwise*2);

                    //side(-470);
                    foward(300);
                    leftClawServo.setPosition(values.leftClawOpen);
                    sleep(500);

//                    neutral();
//                    sleep(2500);

                    foward(-200);

                    side(-1600);

                    foward(350);

                    break;
            }
            break;
        }


    }

}