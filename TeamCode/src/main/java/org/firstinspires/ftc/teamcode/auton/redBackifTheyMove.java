package org.firstinspires.ftc.teamcode.auton;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.universalCode.craneMotors;
import org.firstinspires.ftc.teamcode.universalCode.driveTrain;
import org.firstinspires.ftc.vision.VisionPortal;

import org.firstinspires.ftc.teamcode.universalCode.values;

@Autonomous(name="Red Back IF THEY MOVE")
public class redBackifTheyMove extends LinearOpMode {

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

    private long startTime;

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

        // clawRotator.setPosition(0.0);

        airplaneLauncher = hardwareMap.get(Servo.class, "airplaneLauncher");

        wheels = new driveTrain(hardwareMap);
        wheels.isAuton();

        crane = new craneMotors(hardwareMap);

        closeClaw();
//        leftClawRotator.setPosition(1);
//        rightClawRotator.setPosition(0);
        airplaneLauncher.setPosition(values.airplaneServoResting);

        waitForStart();
        while(opModeIsActive() && !isStopRequested()){
            startTime = System.currentTimeMillis();
            sleep(5000);
            wheels.resetEncoders();

            if(red.getPropPosition() == "right") {
                telemetry.addData("Prop","right");
                telemetry.update();

                foward(-1300);

                rotate(values.turn90DegreesClockwise);
                pickupPixel();
                sleep(2000);

//                rotate(1100);
//                pickupPixel();
//                sleep(2000);
//                resetEncoders();
//
//                rotate(1100);
//                sleep(2000);
//                resetEncoders();

                foward(-850);
                sleep(2200);

                rightClawServo.setPosition(0.5);
                sleep(500);


                neutral();
                sleep(500);
                break;

            }else if(red.getPropPosition() == "center") {
                telemetry.addData("Prop","center");
                telemetry.update();

                foward(-2160);

                pickupPixel();
                sleep(500);

                rightClawServo.setPosition(0.5);
                sleep(1000);

                neutral();
                foward(-150);

                rotate(values.turn90DegreesClockwise);

                while(System.currentTimeMillis() - startTime < 18000.0);
//                side(-2700);
//                waitforwheels();

                foward(-3670);

                side(-1350);

                placePixelLow();
                sleep(3000);

                foward(-230);

//                side(250);
//                sleep(1000);
//                resetEncoders();

                leftClawServo.setPosition(1);
                sleep(300);

                neutral();
                sleep(2500);

                break;

            }else {
                telemetry.addData("Prop","left");
                telemetry.update();

                foward(-1300);

                rotate(values.turn90DegreesClockwise);
                pickupPixel();
                sleep(500);

                rightClawServo.setPosition(0.5);
                sleep(500);


                neutral();
                sleep(500);

                break;

            }
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

