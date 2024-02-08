package org.firstinspires.ftc.teamcode.auton;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.universalCode.craneMotors;
import org.firstinspires.ftc.teamcode.universalCode.driveTrain;
import org.firstinspires.ftc.vision.VisionPortal;

import org.firstinspires.ftc.teamcode.universalCode.values;


//@Disabled
@Autonomous(name="Blue Back IF THEY MOVE")
public class blueBackIfTheyMove extends LinearOpMode {
    private VisionPortal portal;
    private BluePropThreshold blue;
    private driveTrain wheels;
    private craneMotors crane;
    private Servo leftClawRotator;
    private Servo rightClawRotator;
    private Servo airplaneLauncher;

    private Servo leftClawServo;
    private Servo rightClawServo;
    private int mid = 80;
    private int turn = 65;

    private long startTime;

    @Override
    public void runOpMode() throws InterruptedException {

        blue = new BluePropThreshold();

        portal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .setCameraResolution(new Size(1920, 1080))
                .addProcessor(blue)
                .build();

        rightClawRotator = hardwareMap.get(Servo.class, "rightClawRotator");
        leftClawRotator = hardwareMap.get(Servo.class, "leftClawRotator");

        rightClawServo = hardwareMap.get(Servo.class, "rightClawServo");
        leftClawServo = hardwareMap.get(Servo.class, "leftClawServo");

        // goodServo = hardwareMap.get(Servo.class, "goodServo");
        // badServo = hardwareMap.get(Servo.class, "badServo");

        // clawRotator = hardwareMap.get(Servo.class, "clawRotator");



        // rotate(0.5,turn);
        //clawRotator.setPosition(0);
        //starting posistions

        // pickupPixel();
        //
        // openClaw();





        airplaneLauncher = hardwareMap.get(Servo.class, "airplaneLauncher");

        wheels = new driveTrain(hardwareMap, this);
        wheels.isAuton();

        crane = new craneMotors(hardwareMap);


        closeClaw();
        airplaneLauncher.setPosition(values.airplaneServoResting);


        waitForStart();
        while(opModeIsActive() && !isStopRequested()){
            startTime = System.currentTimeMillis();
            wheels.resetEncoders();
            sleep(5000);
            if(blue.getPropPosition()=="center"){
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

                side(1463);

                placePixelLow();
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
            if(blue.getPropPosition()=="left"){
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

                placePixelLow();
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
            if(blue.getPropPosition()=="right"){
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

                placePixelLow();
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
        crane.setTargetPosition(values.cranePlaceHighAuton);
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