package org.firstinspires.ftc.teamcode.drivecode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.universalCode.craneMotors;
import org.firstinspires.ftc.teamcode.universalCode.driveTrain;
import org.firstinspires.ftc.teamcode.universalCode.values;

@TeleOp(name="Driver Mode", group="Linear Opmode")
//@Disabled
public class DriverMode extends LinearOpMode {

//    private DcMotor leftCraneArm, rightCraneArm;
    private Servo leftClawRotator;
    private Servo rightClawRotator;

    private craneMotors crane;

    private driveTrain wheels;


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        wheels = new driveTrain(hardwareMap);
        wheels.setPower(1);

        crane = new craneMotors(hardwareMap);

        Servo airplaneLauncher = hardwareMap.get(Servo.class, "airplaneLauncher");


        Servo leftClawServo = hardwareMap.get(Servo.class, "leftClawServo");
        Servo rightClawServo = hardwareMap.get(Servo.class, "rightClawServo");
        
        leftClawRotator = hardwareMap.get(Servo.class, "leftClawRotator");
        rightClawRotator = hardwareMap.get(Servo.class, "rightClawRotator");
        
        //resets the zero position of the drawer slide motor
        DcMotor leftHanger = hardwareMap.get(DcMotor.class, "leftHanger");
        leftHanger.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftHanger.setTargetPosition(values.hangerResting);
        leftHanger.setPower(1);
        leftHanger.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        // Baller bro that's absolutely crazy
        // On god bro frfr
        
        //resets the zero position of the drawer slide motor
        DcMotor rightHanger = hardwareMap.get(DcMotor.class, "rightHanger");
        rightHanger.setDirection(DcMotorSimple.Direction.REVERSE);
        rightHanger.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightHanger.setTargetPosition(values.hangerResting);
        rightHanger.setPower(1);
        rightHanger.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        telemetry.update();
        
        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // Pushing the left stick forward MUST make robot go forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flip
        
        // Wait for the game to start (driver presses PLAY)
        telemetry.update();
        waitForStart();
        airplaneLauncher.setPosition(values.airplaneServoResting);
        crane.resetEncoders();
            while (opModeIsActive()){
                telemetry.addData("Status", "Running");

                wheels.manualDrive(-gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x,
                        -gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x,
                        -gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x,
                        -gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x);


                if(gamepad1.left_bumper){
                    leftClawServo.setPosition(values.leftClawClosed);
                }else if(gamepad1.left_trigger>0.4){
                    leftClawServo.setPosition(values.leftClawOpen);
                }

                if(gamepad1.right_bumper){
                    rightClawServo.setPosition(values.rightClawClosed);
                }else if(gamepad1.right_trigger>0.4){
                    rightClawServo.setPosition(values.rightClawOpen);
                }

                if(gamepad1.a) {
                    airplaneLauncher.setPosition(values.airplaneServoFired);
                }
                if(gamepad1.dpad_down){
                    wheels.setPower(0.25);
                } else if (gamepad1.dpad_up) {
                    wheels.setPower(1);
                }
                
                
                /*
                Controller buttons use different labels, so
                A is the Blue Cross
                X is the Pink Square
                B is the Red Circle
                Y is the Green Triangle
                */
                if(gamepad2.a){
                    neutral();
                }else if(gamepad2.b){
                    pickupPixel();
                }else if(gamepad2.y){
                    placePixel();
                }

                if(gamepad2.right_bumper){
                    leftHanger.setTargetPosition(values.hangerRaised);
                    rightHanger.setTargetPosition(values.hangerRaised);
                }else if(gamepad2.left_bumper){
                    leftHanger.setTargetPosition(values.hangerHanging);
                    rightHanger.setTargetPosition(values.hangerHanging);
                }else if(gamepad2.left_trigger > 0.4 || gamepad2.right_trigger > 0.4){
                    leftHanger.setTargetPosition(values.hangerResting);
                    rightHanger.setTargetPosition(values.hangerResting);
                }

                if(gamepad2.dpad_left){
                    crane.resetEncoders();
                }else if(gamepad2.dpad_down){
                    crane.setTargetPosition(-3000);
                }

                //new cranes have INSANE value drift, this is here so they don't rip the belts apart
                if(crane.getCurrentRightPosition() < -10 || (crane.getCurrentLeftPosition() > 15 && crane.getCurrentRightPosition() < 5)){
                    crane.resetEncoders();
                }

                telemetry.addData("Left Crane Motor Position", crane.getCurrentLeftPosition());
                telemetry.addData("Right Crane Motor Position", crane.getCurrentRightPosition());
                telemetry.update();

        }
    }
    public void placePixel(){
        crane.setTargetPosition(values.cranePlaceTeleop);
        leftClawRotator.setPosition(values.defaultLeftClawRotator);
        rightClawRotator.setPosition(values.defaultRightClawRotator);
    }
    public void neutral(){
        crane.setTargetPosition(values.craneResting);
        leftClawRotator.setPosition(values.defaultLeftClawRotator);
        rightClawRotator.setPosition(values.defaultRightClawRotator);
    }
    private void pickupPixel(){
        crane.setTargetPosition(values.craneResting);
        leftClawRotator.setPosition(values.pickupPixelLeftClawRotator);
        rightClawRotator.setPosition(values.pickupPixelRightClawRotator);
    }
}
