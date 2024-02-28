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
@Autonomous(name="blue back IF THEY MOVE\uD83D\uDD35")
public class blueBackIfTheyMove extends universalOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        setup();
        Globals.IS_AUTO = true;
        Globals.ALLIANCE = Location.BLUE;
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
                neutral();
                foward(-1100);

                pickupPixel();
                rotate(values.turn90DegreesClockwise);

                foward(100);
                rightClawServo.setPosition(0.5);
                sleep(500);

                neutral();
                foward(-100);
                sleep(1000);

                rotate(values.turn90DegreesCounterClockwise);
                wheels.setFowardSpeed(0.4);
                foward(-1200);
                rotate(values.turn90DegreesCounterClockwise);

                while(System.currentTimeMillis() - startTime < 18000.0);

                foward(-4000);

                side(1700);

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
                telemetry.addData("Center","center");
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

                rotate(values.turn90DegreesCounterClockwise);

                while(System.currentTimeMillis() - startTime < 18000.0);

                foward(-4000);

                side(1050);

                placePixel();
                sleep(3000);

                wheels.setFowardSpeed(0.4);
                foward(-505);

//                side(250);
//                sleep(1000);
//                resetEncoders();

                leftClawServo.setPosition(1);
                sleep(300);

                neutral();
                sleep(2500);
                break;
            case RIGHT:
                telemetry.addData("right","right");
                telemetry.update();
                neutral();
                foward(-1350);

                rotate(values.turn90DegreesCounterClockwise);
                pickupPixel();

                rightClawServo.setPosition(0.5);
                sleep(500);

                neutral();
                foward(-50);
                sleep(2500);

                side(-1050);

                while(System.currentTimeMillis() - startTime < 18000.0);

                foward(-4000);

                side(1400);

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



            telemetry.update();

            sleep(50);
        }


    }

}