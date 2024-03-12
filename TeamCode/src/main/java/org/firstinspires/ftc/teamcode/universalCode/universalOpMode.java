package org.firstinspires.ftc.teamcode.universalCode;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.supermanTESTER.Location;
import org.firstinspires.ftc.teamcode.supermanTESTER.PropPipeline;
import org.firstinspires.ftc.vision.VisionPortal;

public abstract class universalOpMode extends LinearOpMode {

    public crane slides;
    public driveTrain wheels;
    public Servo leftClawServo;
    public Servo rightClawServo;
    public Servo airplaneLauncher;
    public PropPipeline propPipeline;
    public VisionPortal portal;
    public long startTime;
    public Location randomization;

//    public Servo goose;


    public void setup(){
        setup(0.5);
    }
    public void setup(double cranePower){
        slides = new crane(hardwareMap, cranePower,true, false);
        wheels = new driveTrain(hardwareMap, this);
        leftClawServo = hardwareMap.get(Servo.class, "leftClawServo");
        rightClawServo = hardwareMap.get(Servo.class, "rightClawServo");
        airplaneLauncher = hardwareMap.get(Servo.class, "airplaneLauncher");
        propPipeline = new PropPipeline();
        portal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .setCameraResolution(new Size(1920, 1080))
                .addProcessor(propPipeline)
                .setStreamFormat(VisionPortal.StreamFormat.MJPEG)
                .enableLiveView(true)
                .setAutoStopLiveView(true)
                .build();
    }

    //0 is Front Auton, 1 is Back Auton, any other value is TeleOp
    //(Though with the new placement system, TeleOp isn't REALLY applicable for placePixel)
    private int opModeType = 2;
    public void setOpModeType(int type){
        opModeType = type;
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
    public void placePixel(){
        if(opModeType == 0){
            slides.setTargetPosition(values.cranePlaceLowAuton);
            //crane.setSpinniesPosition(600);
        }else if(opModeType == 1){
            slides.setTargetPosition(values.cranePlaceHighAuton);
            //crane.setSpinniesPosition(100);
        }else{
            slides.setTargetPosition(values.cranePlaceTeleop);
        }
    }
    public void neutral(){
        slides.setTargetPosition(values.craneNeutral);
    }
    public void pickupPixel(){
        slides.setTargetPosition(values.craneResting);
    }
    public void closeClaw() {
        leftClawServo.setPosition(values.leftClawClosed);
        rightClawServo.setPosition(values.rightClawClosed);
    }
}
