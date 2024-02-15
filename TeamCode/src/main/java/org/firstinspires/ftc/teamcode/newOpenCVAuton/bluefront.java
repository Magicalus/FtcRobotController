package org.firstinspires.ftc.teamcode.newOpenCVAuton;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.universalCode.craneMotors;
import org.firstinspires.ftc.teamcode.universalCode.driveTrain;
import org.firstinspires.ftc.teamcode.universalCode.values;

import org.firstinspires.ftc.teamcode.universalCode.craneMotors;
import org.firstinspires.ftc.teamcode.universalCode.driveTrain;

import org.firstinspires.ftc.vision.VisionPortal;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.supermanTESTER.Globals;
import org.firstinspires.ftc.teamcode.supermanTESTER.Location;
import org.firstinspires.ftc.teamcode.supermanTESTER.PropPipeline;
import org.firstinspires.ftc.vision.VisionPortal;



//@Disabled
@Autonomous(name="bluefront \uD83D\uDD35")
public class bluefront extends LinearOpMode {
    private VisionPortal portal;
    private DcMotor.ZeroPowerBehavior brake = DcMotor.ZeroPowerBehavior.BRAKE;
    private DcMotor.ZeroPowerBehavior floatt =DcMotor.ZeroPowerBehavior.FLOAT;
    private Servo leftClawRotator;
    private Servo rightClawRotator;
    private Servo airplaneLauncher;

    private Servo leftClawServo;
    private Servo rightClawServo;

    private driveTrain wheels;
    private craneMotors crane;
    // private Servo goodServo;
    //private Servo badServo;

    private int mid = 80;
    private int turn = 65;

    private long startTime;

    private PropPipeline propPipeline;
    private Location randomization;

    @Override
    public void runOpMode() throws InterruptedException {


        Globals.IS_AUTO = true;
        Globals.ALLIANCE = Location.BLUE;
        Globals.SIDE = Location.CLOSE;

        propPipeline = new PropPipeline();
        portal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .setCameraResolution(new Size(1920, 1080))
                .addProcessor(propPipeline)
                .setStreamFormat(VisionPortal.StreamFormat.MJPEG)
                .enableLiveView(true)
                .setAutoStopLiveView(true)
                .build();

        rightClawRotator = hardwareMap.get(Servo.class, "rightClawRotator");
        leftClawRotator = hardwareMap.get(Servo.class, "leftClawRotator");

        rightClawServo = hardwareMap.get(Servo.class, "rightClawServo");
        leftClawServo = hardwareMap.get(Servo.class, "leftClawServo");

        airplaneLauncher = hardwareMap.get(Servo.class, "airplaneLauncher");

        wheels = new driveTrain(hardwareMap, this);
        wheels.isAuton();

        crane = new craneMotors(hardwareMap);

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

                    pickupPixel();
                    sleep(500);

                    rotate(3*values.turn90DegreesClockwise);
//                    rotate(values.turn90DegreesCounterClockwise);
//                    rotate(values.turn90DegreesCounterClockwise);
//                    rotate(values.turn90DegreesCounterClockwise);
//                    rotate(values.turn90DegreesCounterClockwise);



                    foward(-900);

                    rightClawServo.setPosition(values.rightClawOpen);
                    sleep(500);

                    neutral();
                    sleep(500);
                    rightClawServo.setPosition(values.rightClawClosed);

                    placePixelLow();

                    foward(-800);

                    //side(150);
                    //sleep(1000);
                    //resetEncoders();

                    //foward(-100);
                    //sleep(1000);
                    //resetEncoders();

                    side(800);

                    leftClawServo.setPosition(values.leftClawOpen);
                    sleep(500);

                    neutral();
                    sleep(2500);

                    foward(200);

                    side(1050);

                    foward(-350);

                    break;
                case CENTER:
                    telemetry.addData("Prop","center");
                    telemetry.update();

                    neutral();
                    foward(-920);

                    rotate(values.turn90DegreesCounterClockwise);

                    pickupPixel();

                    foward(-500);

                    side(-700);

                    rightClawServo.setPosition(values.rightClawOpen);
                    sleep(300);

                    neutral();

//                side(-100);
//
//                rotate(values.turn90DegreesClockwise);

                    placePixelLow();
                    foward(-1100);

                    side(600);
//                wheels.resetEncoders();
//                side(-500);

                    foward(-200);

                    leftClawServo.setPosition(values.leftClawOpen);
                    sleep(300);

                    neutral();
//                sleep(2000);
                    foward(250);

                    side(1300);

                    foward(-450);

                    //why joe I love you <3 THIS WAS MIGUEL I PROMISE
                    break;
                case RIGHT:
                    telemetry.addData("Prop","right");
                    telemetry.update();

                    foward(-1300);
                    side(200);

                    pickupPixel();
                    sleep(500);

                    rotate(values.turn90DegreesCounterClockwise);
                    foward(100);


//
//                    foward(150);
                    rightClawServo.setPosition(values.rightClawOpen);
                    sleep(700);

                    neutral();
                    sleep(500);


                    placePixelLow();
                    foward(-1800);

                    //side(-470);
                    foward(-300);
                    leftClawServo.setPosition(values.leftClawOpen);
                    sleep(500);

                    neutral();
                    sleep(2500);

                    foward(200);

                    side(1600);

                    foward(-350);

                    break;
            }
            break;
        }


    }

    public void foward(int distance){
        wheels.foward(distance);
    }

    public void side(int distance){
        wheels.side(distance);
    }

    public void rotate(int distance){
        wheels.rotate(distance);
    }

    public void placePixelLow(){
        crane.setTargetPosition(values.cranePlaceLowAuton);
        leftClawRotator.setPosition(0.1);
        rightClawRotator.setPosition(0.85);
    }
    public void neutral(){
        crane.setTargetPosition(values.craneResting);
        leftClawRotator.setPosition(0.1);
        rightClawRotator.setPosition(0.85);
    }
    public void pickupPixel(){
        crane.setTargetPosition(values.craneResting);
        leftClawRotator.setPosition(0.53);
        rightClawRotator.setPosition(0.42);
    }
    public void closeClaw() {
        leftClawServo.setPosition(0);
        rightClawServo.setPosition(1);
    }
}