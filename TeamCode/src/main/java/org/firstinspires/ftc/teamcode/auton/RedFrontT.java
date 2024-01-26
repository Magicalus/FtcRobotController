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
@Autonomous(name="Red Front")
public class RedFrontT extends LinearOpMode {
    private VisionPortal portal;
    private RedPropThreshold red;
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

    @Override
    public void runOpMode() throws InterruptedException {

        red = new RedPropThreshold();

        portal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .setCameraResolution(new Size(1920, 1080))
                .addProcessor(red)
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
        // sleep(1000);+
        // openClaw();


        // clawRotator.setPosition(0.0);

        airplaneLauncher = hardwareMap.get(Servo.class, "airplaneLauncher");

        wheels = new driveTrain(hardwareMap);

        crane = new craneMotors(hardwareMap);

        closeClaw();
        airplaneLauncher.setPosition(0);


        waitForStart();
        crane.setPower(0.2);
        while(opModeIsActive() && !isStopRequested()){
            sleep(5000);
            wheels.resetEncoders();

            if(red.getPropPosition()=="center"){
                telemetry.addData("Prop","center");
                telemetry.update();

                foward(-860);

                pickupPixel();
                sleep(700);

                rotate(2200);

                foward(280);

                rightClawServo.setPosition(0.5);
                sleep(300);

                neutral();
                sleep(500);

                foward(-100);

                rotate(values.turn90DegreesCounterClockwise);

                foward(-1350);

                placePixelLow();
                sleep(5000);

                foward(-300);

               //side(-);

                leftClawServo.setPosition(1);
                sleep(300);

                neutral();
                sleep(2000);

                side(-1350);

                foward(-350);

                //why joe I love you <3 THIS WAS MIGUEL I PROMISE
                break;


            }
            if(red.getPropPosition()=="right"){
                telemetry.addData("Prop","right");
                telemetry.update();

                foward(-1300);

                rotate(values.turn90DegreesClockwise);

                pickupPixel();
                sleep(500);

//                rotate(1100);
//                pickupPixel();
//                sleep(2000);
//                resetEncoders();
//
//                rotate(1100);
//                sleep(2000);
//                resetEncoders();

                foward(-850);

                rightClawServo.setPosition(0.5);
                sleep(500);

                neutral();
                sleep(500);

                foward(-800);

                //add thingy that moves from pixel placement to board

                //side(150);
                //sleep(1000);
                //resetEncoders();

                placePixelLow();
                sleep(5000);

                //foward(-100);
                //sleep(1000);
                //resetEncoders();

                side(-400);
                foward(-100);

//                openClaw();
//                sleep(2000);
//                resetEncoders();

                leftClawServo.setPosition(1);
                sleep(300);

                neutral();
                sleep(2000);

                side(-1050);

                foward(-350);

                break;



            }
            if(red.getPropPosition()=="left"){
                telemetry.addData("Prop","left");
                telemetry.update();

                foward(-1300);

                rotate(values.turn90DegreesClockwise);

                pickupPixel();
                sleep(500);

                foward(100);

                rightClawServo.setPosition(0.5);
                sleep(500);

                neutral();
                //foward();
                sleep(500);

                foward(-1600);

                side(330);

                placePixelLow();
                sleep(5000);

                foward(-70);


                leftClawServo.setPosition(1);
                sleep(300);

                neutral();
                sleep(2000);

                foward(200);

                side(-1600);

                foward(-350);

                break;
            }
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