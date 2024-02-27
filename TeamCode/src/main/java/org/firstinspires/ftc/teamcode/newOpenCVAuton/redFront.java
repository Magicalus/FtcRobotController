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

                    telemetry.addData("Prop","right");
                    telemetry.update();

                    foward(-1300);
                    side(-200);

                    pickupPixel();
                    sleep(500);

                    rotate(values.turn90DegreesClockwise);
                    foward(100);


//
//                    foward(150);
                    rightClawServo.setPosition(values.rightClawOpen);
                    sleep(700);

                    neutral();
                    sleep(500);


                    placePixel();
                    foward(-1600);

                    side(300);
                    foward(-300);
                    leftClawServo.setPosition(values.leftClawOpen);
                    sleep(500);

                    neutral();
                    sleep(2500);

                    foward(200);

                    side(-1800);

                    foward(-350);

                    break;
                case CENTER:
                    telemetry.addData("Prop","center");
                    telemetry.update();

                    neutral();
                    foward(-820);

                    rotate(values.turn90DegreesClockwise);

                    pickupPixel();

                    foward(-500);

                    side(900);

                    rightClawServo.setPosition(values.rightClawOpen);
                    sleep(300);

                    neutral();

//                side(-100);
//
//                rotate(values.turn90DegreesClockwise);

                    placePixel();
                    foward(-1100);

                    side(-600);
//                wheels.resetEncoders();
//                side(-500);

                    foward(-200);

                    leftClawServo.setPosition(values.leftClawOpen);
                    sleep(300);

                    neutral();
//                sleep(2000);
                    foward(250);

                    side(-1350);

                    foward(-450);

                    //why joe I love you <3 THIS WAS MIGUEL I PROMISE
                    break;
                case RIGHT:
                    telemetry.addData("Prop","right");
                    telemetry.update();
                    foward(-1250);

                    pickupPixel();
                    sleep(500);

                    rotate(3*values.turn90DegreesCounterClockwise);

                    foward(-900);

                    rightClawServo.setPosition(values.rightClawOpen);
                    sleep(500);

                    neutral();
                    sleep(500);

                    placePixel();

                    foward(-800);

                    //side(150);
                    //sleep(1000);
                    //resetEncoders();

                    //foward(-100);
                    //sleep(1000);
                    //resetEncoders();

                    side(-300);

                    leftClawServo.setPosition(values.leftClawOpen);
                    sleep(500);

                    neutral();
                    sleep(2500);

                    foward(200);

                    side(-1050);

                    foward(-450);

                    break;
            }
            break;
        }


    }
}