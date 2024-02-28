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
                    telemetry.addData("Prop","left");
                    telemetry.update();
                    neutral();
                    foward(-1100);

                    pickupPixel();
                    rotate(values.turn90DegreesCounterClockwise);

                    foward(100);
                    rightClawServo.setPosition(0.5);
                    sleep(500);

                    neutral();
                    foward(-100);
                    sleep(1000);

                    rotate(values.turn90DegreesClockwise);
                    wheels.setFowardSpeed(0.4);
                    foward(-1200);
                    rotate(values.turn90DegreesClockwise);

                    while(System.currentTimeMillis() - startTime < 16000.0);

                    foward(-4000);

                    side(-1700);

                    placePixel();
                    sleep(3000);

                    wheels.setFowardSpeed(0.4);
                    foward(-50);

//                side(250);
//                sleep(1000);
//                resetEncoders();

                    leftClawServo.setPosition(1);
                    sleep(300);

                    neutral();
                    sleep(2500);

                    break;
                case CENTER:
                    telemetry.addData("Prop","center");
                    telemetry.update();
                    neutral();
                    foward(-2160);

                    pickupPixel();
                    sleep(500);

                    rightClawServo.setPosition(0.5);
                    sleep(1000);

                    neutral();
                    foward(-150);
                    sleep(1000);

                    rotate(values.turn90DegreesClockwise);

                    while(System.currentTimeMillis() - startTime < 18000.0);

                    foward(-4000);

                    side(-1463);

                    placePixel();
                    sleep(3000);

                    wheels.setFowardSpeed(0.4);
                    foward(-50);

                    leftClawServo.setPosition(1);
                    sleep(300);

                    neutral();
                    sleep(2500);

                    break;
                case RIGHT:
                    telemetry.addData("Prop","right");
                    telemetry.update();
                    neutral();
                    foward(-1160);

                    pickupPixel();
                    rotate(values.turn90DegreesCounterClockwise);

                    foward(100);
                    rightClawServo.setPosition(0.5);
                    sleep(500);

                    neutral();
                    foward(-100);
                    sleep(1000);

                    rotate(values.turn90DegreesClockwise);
                    wheels.setFowardSpeed(0.4);
                    foward(-1200);
                    rotate(values.turn90DegreesClockwise);

                    while(System.currentTimeMillis() - startTime < 16000.0);

                    foward(-4000);

                    side(-1750);

                    placePixel();
                    sleep(3000);

                    wheels.setFowardSpeed(0.4);
                    foward(-50);

//                side(250);
//                sleep(1000);
//                resetEncoders();

                    leftClawServo.setPosition(1);
                    sleep(300);

                    neutral();
                    sleep(2500);

                    break;
            }
            break;
        }


    }
}