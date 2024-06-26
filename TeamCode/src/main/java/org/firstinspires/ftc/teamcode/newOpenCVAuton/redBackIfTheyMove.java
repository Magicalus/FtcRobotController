package org.firstinspires.ftc.teamcode.newOpenCVAuton;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
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


@Disabled
@Autonomous(name="red Back If They Move\uD83D\uDD34")
public class redBackIfTheyMove extends universalOpMode {
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
                    telemetry.addData("Prop","Right");
                    telemetry.update();
                    foward(-1920);
                    side(750);

                    rightClawServo.setPosition(values.rightClawOpen);
                    sleep(100);

                    foward(-400);

                    rotate(values.turn90DegreesClockwise);

                    while(System.currentTimeMillis() - startTime < 18000.0);

                    foward(-3350);
                    side(-1200);
                    placePixel();
                    rotate(values.turn90DegreesClockwise);
                    sleep(50);
                    rotate(values.turn90DegreesClockwise);
                    foward(1150);

                    leftClawServo.setPosition(values.leftClawOpen);
                    sleep(100);

                    foward(-100);

                    pickupPixel();

                    //why joe I love you <3 THIS WAS MIGUEL I PROMISE
                    break;
                case CENTER:
                    telemetry.addData("Prop","center");
                    telemetry.update();
                    foward(-1100);
                    sleep(50);
                    rotate(values.turn90DegreesClockwise);
                    sleep(50);
                    rotate(values.turn90DegreesClockwise);

                    rightClawServo.setPosition(values.rightClawOpen);
                    sleep(100);
                    foward(-200);
                    rotate(values.turn90DegreesCounterClockwise);
                    foward(600);
                    side(1520);

                    while(System.currentTimeMillis() - startTime < 16000.0);

                    foward(-3750);
                    rotate(values.turn90DegreesClockwise);
                    sleep(50);
                    foward(-1275);
                    placePixel();
                    sleep(50);
                    rotate(values.turn90DegreesClockwise);
                    wheels.setPower(0.4);
                    foward(435);

                    leftClawServo.setPosition(values.leftClawOpen);
                    sleep(100);

                    pickupPixel();
                    sleep(2000);

                    //why joe I love you <3 THIS WAS MIGUEL I PROMISE
                    break;
                case RIGHT:
                    telemetry.addData("Prop","right");
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

                    placePixel();
                    side(1600);
                    foward(500);

                    leftClawServo.setPosition(values.leftClawOpen);
                    sleep(100);

                    foward(-100);
                    pickupPixel();
                    sleep(2000);

                    break;
            }
            break;
        }


    }
}