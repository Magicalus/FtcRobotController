package org.firstinspires.ftc.teamcode.newOpenCVAuton;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.supermanTESTER.Globals;
import org.firstinspires.ftc.teamcode.supermanTESTER.Location;
import org.firstinspires.ftc.teamcode.supermanTESTER.PropPipeline;
import org.firstinspires.ftc.teamcode.universalCode.crane;
import org.firstinspires.ftc.teamcode.universalCode.driveTrain;
import org.firstinspires.ftc.teamcode.universalCode.universalOpMode;
import org.firstinspires.ftc.teamcode.universalCode.values;
import org.firstinspires.ftc.vision.VisionPortal;


//@Disabled
@Autonomous(name="red Back\uD83D\uDD34")
public class redBack extends universalOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        setup();
        Globals.IS_AUTO = true;
        Globals.ALLIANCE = Location.RED;
        Globals.SIDE = Location.FAR;

        setOpModeType(1);
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

                    foward(-1820);
                    side(750);

                    rightClawServo.setPosition(values.rightClawOpen);
                    sleep(100);

                    //neutral();


                    foward(-400);

                    rotate(values.turn90DegreesClockwise);

                    while(System.currentTimeMillis() - startTime < 18000.0);

                    foward(-3350);
                    side(-100);
                    rotate(values.turn90DegreesCounterClockwise);
                    sleep(50);
                    rotate(values.turn90DegreesCounterClockwise);
                    foward(900);

                    leftClawServo.setPosition(values.leftClawOpen);
                    sleep(100);
                    foward(-200);



                    //why joe I love you <3 THIS WAS MIGUEL I PROMISE
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
                    foward(600);
                    side(1500);

                    while(System.currentTimeMillis() - startTime < 16000.0);

                    foward(-3350);
                    side(-400);
                    rotate(values.turn90DegreesCounterClockwise);
                    sleep(50);
                    rotate(values.turn90DegreesCounterClockwise);
                    foward(900);

                    leftClawServo.setPosition(values.leftClawOpen);
                    sleep(100);
                    foward(-200);



                    //why joe I love you <3 THIS WAS MIGUEL I PROMISE
                    break;
                case RIGHT:
                    telemetry.addData("Prop","Right");
                    telemetry.update();
                foward(-1300);
                side(200);

                rotate(values.turn90DegreesCounterClockwise);
                foward(100);

                rightClawServo.setPosition(values.rightClawOpen);
                sleep(100);

                foward(-200);

                side(-1100);

                while(System.currentTimeMillis() - startTime < 16000.0);

                foward(3350);
                side(-100);
                foward(900);

                leftClawServo.setPosition(values.leftClawOpen);
                sleep(100);
                foward(-200);



                //why joe I love you <3 THIS WAS MIGUEL I PROMISE
                break;
            }
            break;
        }


    }
}