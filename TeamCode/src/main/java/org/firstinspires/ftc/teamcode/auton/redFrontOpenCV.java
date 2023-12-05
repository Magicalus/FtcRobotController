package org.firstinspires.ftc.teamcode.auton;
import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;

@Autonomous(name="redFrontOpenCV")
public class redFrontOpenCV extends LinearOpMode {

    private VisionPortal portal;
    private RedPropThreshold red;

    @Override
    public void runOpMode() throws InterruptedException {

        red = new RedPropThreshold();

        portal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .setCameraResolution(new Size(1920, 1080))
                .addProcessor(red)
                .build();


        waitForStart();

        while(opModeIsActive() && !isStopRequested()){
            telemetry.addData("Prop Position", red.getPropPosition());
            telemetry.update();

            sleep(50);

            if(red.getPropPosition() == "Left") {
                // add something ig
                sleep(50);

            }else if(red.getPropPosition() == "Center") {
                // add something ig
                sleep(50);

            }else {
                //add something ig
                sleep(50);

            }
        }
    }
}

