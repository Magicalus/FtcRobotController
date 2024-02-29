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
                    telemetry.addData("left","left");
                    telemetry.update();

//                    neutral();
                    foward(-1350);

                    pickupPixel();
                    rotate(values.turn90DegreesClockwise);

//                    sleep(500);

                    rightClawServo.setPosition(0.5);
                    sleep(500);

//                    neutral();
                    foward(-50);
//                    sleep(2500);

                    side(1000);

                    while(System.currentTimeMillis() - startTime < 23000.0);

                    foward(-4100);

                    pickupPixel();

                    sleep(750);

                    leftClawServo.setPosition(values.leftClawOpen);

                    sleep(500);

                    neutral();

                    sleep(500);
                    break;
                case CENTER:
                    telemetry.addData("Center","center");
                    telemetry.update();
//                    neutral();
                    foward(-2160);

                    pickupPixel();
                    sleep(500);

                    rightClawServo.setPosition(values.rightClawOpen);
                    sleep(1000);

//                    neutral();
                    foward(-150);
                    sleep(1000);

                    rotate(values.turn90DegreesClockwise);

                    while(System.currentTimeMillis() - startTime < 23000.0);

                    foward(-4100);

                    pickupPixel();

                    sleep(500);

                    leftClawServo.setPosition(values.leftClawOpen);

                    sleep(500);

                    neutral();

                    sleep(500);
                    break;
                case RIGHT:
                    telemetry.addData("Prop","Right");
                    telemetry.update();
//                    neutral();
                    foward(-1300);
                    side(200);

                    pickupPixel();
                    sleep(500);

                    rotate(values.turn90DegreesCounterClockwise);
                    foward(100);

                    rightClawServo.setPosition(values.rightClawOpen);
                    sleep(500);

                    foward(-100);

//                    neutral();

                    side(-1100);

                    while(System.currentTimeMillis() - startTime < 23000.0);

                    foward(4000);

                    pickupPixel();

                    sleep(750);

                    leftClawServo.setPosition(values.leftClawOpen);

                    sleep(500);

                    neutral();

                    sleep(500);

                    break;
            }
            break;
        }


    }
}