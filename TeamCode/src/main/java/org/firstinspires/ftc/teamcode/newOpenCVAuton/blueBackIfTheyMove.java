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
                telemetry.addData("Prop","left");
                telemetry.update();
//                    neutral();
                foward(-1300);
                side(-200);

//                    pickupPixel();
//                    sleep(500);

                rotate(values.turn90DegreesClockwise);
                foward(200);

                rightClawServo.setPosition(values.rightClawOpen);
                sleep(500);

                foward(-200);

//                    neutral();

                side(1100);

                while(System.currentTimeMillis() - startTime < 18000.0);

                foward(4000);

                side(-1700);

                placePixel();
                sleep(2000);

                wheels.setFowardSpeed(0.4);
                foward(100);

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
//                neutral();
                foward(-2160);

                pickupPixel();
                sleep(500);

                rightClawServo.setPosition(0.5);
                sleep(1000);

//                neutral();
                foward(-150);
                sleep(1000);

                rotate(values.turn90DegreesClockwise);

                while(System.currentTimeMillis() - startTime < 18000.0);

                foward(3600);

                side(-1550);

                placePixel();
                sleep(2000);

                wheels.setFowardSpeed(0.4);
                foward(350);

//                side(250);
//                sleep(1000);
//                resetEncoders();

                leftClawServo.setPosition(1);
                sleep(300);

                foward(-300);

                neutral();
                sleep(2500);
                break;
            case RIGHT:
                telemetry.addData("right","right");
                telemetry.update();
//                neutral();
                foward(-1350);

                rotate(values.turn90DegreesCounterClockwise);
//                pickupPixel();
                foward(100);

                rightClawServo.setPosition(0.5);
                sleep(500);

//                neutral();
                foward(-100);

                side(-1050);

                rotate(values.turn90DegreesCounterClockwise*2);

                while(System.currentTimeMillis() - startTime < 18000.0);

                foward(4000);

                side(-1400);

                placePixel();
                sleep(2000);

                wheels.setFowardSpeed(0.4);
                foward(100);

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