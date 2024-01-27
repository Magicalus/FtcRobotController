package org.firstinspires.ftc.teamcode.auton;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.universalCode.craneMotors;
import org.firstinspires.ftc.teamcode.universalCode.driveTrain;
import org.firstinspires.ftc.teamcode.universalCode.values;
import org.firstinspires.ftc.vision.VisionPortal;


//@Disabled
@Autonomous(name="Blue Front")
public class BlueFront extends LinearOpMode {
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
        // sleep(1000);
        // openClaw();

        // clawRotator.setPosition(0.0);

        airplaneLauncher = hardwareMap.get(Servo.class, "airplaneLauncher");

        wheels = new driveTrain(hardwareMap);
        wheels.isAuton();

        crane = new craneMotors(hardwareMap);

        closeClaw();
        airplaneLauncher.setPosition(0);


        waitForStart();
        while(opModeIsActive() && !isStopRequested()){
            sleep(5000);
            wheels.resetEncoders();
            if(blue.getPropPosition()=="center"){
                telemetry.addData("Prop","center");
                telemetry.update();

                foward(-820);

                sleep(700);

                rotate(2100);

                pickupPixel();

                foward(280);



                rightClawServo.setPosition(0.5);
                sleep(300);

                neutral();
                sleep(500);

                foward(-100);

                rotate(values.turn90DegreesClockwise-70);

                foward(-1350);

                placePixelLow();
                sleep(4000);
                wheels.resetEncoders();
                side(-370);

                foward(-400);

                //side(-);

                leftClawServo.setPosition(1);
                sleep(300);

                neutral();
                sleep(2000);
                foward(250);

                side(1350);

                foward(-450);

                //why joe I love you <3 THIS WAS MIGUEL I PROMISE
                break;
            }
            if(blue.getPropPosition()=="left"){
                telemetry.addData("Prop","left");
                telemetry.update();

                foward(-1300);

                rotate(values.turn90DegreesCounterClockwise);

                pickupPixel();
                sleep(500);

                foward(-900);

                rightClawServo.setPosition(0.5);
                sleep(500);

                neutral();
                sleep(500);

                foward(-800);

                //side(150);
                //sleep(1000);
                //resetEncoders();

                placePixelLow();
                sleep(5000);

                //foward(-100);
                //sleep(1000);
                //resetEncoders();

                side(350);

                leftClawServo.setPosition(1);
                sleep(500);

                neutral();
                sleep(2500);

                foward(200);

                side(1050);

                foward(-350);

                break;
            }
            if(blue.getPropPosition()=="right"){
                telemetry.addData("Prop","right");
                telemetry.update();

                foward(-1300);

                rotate(values.turn90DegreesCounterClockwise);

                pickupPixel();
                sleep(500);

                foward(150);

                rightClawServo.setPosition(0.5);
                sleep(500);

                neutral();
                sleep(500);

                foward(-1600);

                //side(-470);
                placePixelLow();
                sleep(2000);
                foward(-200);
                leftClawServo.setPosition(1);
                sleep(500);

                neutral();
                sleep(2500);

                foward(200);

                side(1600);

                foward(-350);

                break;
            }
            //The line below can't be reached in the current state; the code always hits a break before hitting here
            //Good place for error detection, actually
            telemetry.update();
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