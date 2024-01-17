package org.firstinspires.ftc.teamcode.auton;
import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.hardware.DcMotorSimple;



//@Disabled
@Autonomous(name="Blue Front")
public class BlueFront extends LinearOpMode {
    private VisionPortal portal;
    private BluePropThreshold blue;
    private DcMotor.ZeroPowerBehavior brake = DcMotor.ZeroPowerBehavior.BRAKE;
    private DcMotor.ZeroPowerBehavior floatt =DcMotor.ZeroPowerBehavior.FLOAT;
    private DcMotor frontLeft, frontRight, backLeft, backRight;
    private Servo leftClawRotator;
    private Servo rightClawRotator;
    private Servo airplaneLauncher;

    private Servo leftClawServo;
    private Servo rightClawServo;

    private DcMotor craneArm;
    // private Servo goodServo;
    //private Servo badServo;

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

        frontLeft = hardwareMap.get(DcMotor.class, "fruntLeft");
        frontRight = hardwareMap.get(DcMotor.class, "fruntRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "jarmy");
        craneArm = hardwareMap.get(DcMotor.class, "craneArm");

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

        craneArm = hardwareMap.get(DcMotor.class, "craneArm");
        craneArm.setDirection(DcMotor.Direction.REVERSE);
        craneArm.setTargetPosition(0);
        craneArm.setPower(0);
        craneArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        craneArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setTargetPosition(0);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setTargetPosition(0);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setTargetPosition(0);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setTargetPosition(0);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        // clawRotator.setPosition(0.0);

        airplaneLauncher = hardwareMap.get(Servo.class, "airplaneLauncher");


        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        closeClaw();
        airplaneLauncher.setPosition(0);


        waitForStart();
        craneArm.setPower(0.2);
        while(opModeIsActive() && !isStopRequested()){
            sleep(5000);
            resetEncoders();
            if(blue.getPropPosition()=="center"){
                telemetry.addData("Prop","center");
                telemetry.update();

                foward(-860);

                rotate(-2200);

                foward(390);

                pickupPixel();
                sleep(500);

                rightClawServo.setPosition(0.5);
                sleep(500);

                neutral();
                sleep(500);

                foward(-200);

                rotate(1100);

                foward(-1350);

                placePixelLow();
                sleep(5000);

                foward(-300);

                side(-300);

                leftClawServo.setPosition(1);
                sleep(300);

                craneArm.setTargetPosition(0);
                neutral();
                sleep(2500);

                foward(200);

                side(1350);

                foward(-350);

                break;
            }
            if(blue.getPropPosition()=="left"){
                telemetry.addData("Prop","left");
                telemetry.update();

                foward(-1300);

                rotate(-1100);

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

                craneArm.setTargetPosition(0);
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

                rotate(-1150);

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
                sleep(5000);

                leftClawServo.setPosition(1);
                sleep(500);

                craneArm.setTargetPosition(0);
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

    public void resetEncoders(){
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


    }

    public void foward(int distance){
        moveVertically(frontLeft, distance, 0.5);
        moveVertically(frontRight, distance, 0.5);
        moveVertically(backRight, distance, 0.5);
        moveVertically(backLeft, distance, 0.5);

        waitforwheels();
    }

    public void side(int distance){
        moveVertically(frontLeft, distance, 0.5);
        moveVertically(frontRight, -distance, 0.5);
        moveVertically(backRight, distance, 0.5);
        moveVertically(backLeft, -distance, 0.5);

        waitforwheels();
    }

    public void rotate(int distance){
        moveVertically(frontLeft, distance, 0.5);
        moveVertically(frontRight, -distance, 0.5);
        moveVertically(backRight, -distance, 0.5);
        moveVertically(backLeft, distance, 0.5);

        waitforwheels();
    }

    public void middlePush(){


    }
    /*

    public void placePixelLow(){
        craneArm.setTargetPosition(720);
        clawRotator.setPosition(0);
    }
    public void neutral(){
        craneArm.setTargetPosition(100);
        clawRotator.setPosition(0.15);
    }
    public void pickupPixel(){
        craneArm.setTargetPosition(5);
        clawRotator.setPosition(0.75);
    }
    public void openClaw(){
        goodServo.setPosition(0.35);
        badServo.setPosition(0.6);
    }

    public void closeClaw() {
        goodServo.setPosition(0.6);
        badServo.setPosition(0.3);
    }
    */

    public void moveVertically(DcMotor mot, int position, double power){
        mot.setPower(0);
        mot.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mot.setTargetPosition(0);
        mot.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mot.setPower(0);

        mot.setTargetPosition(position);
        mot.setPower(power);
    }

    public void placePixelLow(){
        craneArm.setTargetPosition(1550);
        leftClawRotator.setPosition(0.1);
        rightClawRotator.setPosition(0.85);
    }
    public void neutral(){
        craneArm.setTargetPosition(000);
        leftClawRotator.setPosition(0.1);
        rightClawRotator.setPosition(0.85);
    }
    public void pickupPixel(){
        craneArm.setTargetPosition(0);
        leftClawRotator.setPosition(0.53);
        rightClawRotator.setPosition(0.42);
    }
    public void openClaw(){
        leftClawServo.setPosition(1);
        rightClawServo.setPosition(0);
    }

    public void closeClaw() {
        leftClawServo.setPosition(0);
        rightClawServo.setPosition(1);
    }

    public void waitforwheels() {
        while (frontLeft.getCurrentPosition() != frontLeft.getTargetPosition() && frontRight.getCurrentPosition() != frontRight.getTargetPosition()
                && backLeft.getCurrentPosition() != backLeft.getTargetPosition() && backRight.getCurrentPosition() != backRight.getTargetPosition())
            ;
        resetEncoders();
    }
}