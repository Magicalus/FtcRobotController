package org.firstinspires.ftc.teamcode.drivecode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="EXPERIMENTAL", group="Linear Opmode")
//@Disabled
public class EXPERIMENTAL extends LinearOpMode {

    // Declare OpMode members
    private DcMotor fruntLeft, fruntRight, backLeft, jarmy;
    private DcMotor craneArm;
    private Servo airplaneLauncher;
    private Servo leftClawServo;
    private Servo rightClawServo;
    private Servo leftClawRotator;
    private Servo rightClawRotator;
    private DcMotor hookLifter;
    private DcMotor robotLifter;

    //These variables make the Servo slowdown work
    private double leftClawRotatorTargetPosition;
    private double leftClawRotatorOldPosition;
    private double rightClawRotatorTargetPosition;
    private double rightClawRotatorOldPosition;
    private double startTime = System.nanoTime();
    //How long (in seconds) you want the servo to take to move
    private double speed = 3.0;
    double timeElapsed;

    public enum State{
        LIFT,
        DOWN
    }
    
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        fruntRight = hardwareMap.get(DcMotor.class, "fruntRight");
        //fruntRight.setDirection(DcMotorSimple.Direction.REVERSE);
        
        fruntLeft = hardwareMap.get(DcMotor.class, "fruntLeft");
        fruntLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        
        jarmy = hardwareMap.get(DcMotor.class, "jarmy");
        //jarmy.setDirection(DcMotorSimple.Direction.REVERSE);
        
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        
        //reset the zero position of the arm, and set it to not use power when not receiving power.
        craneArm = hardwareMap.get(DcMotor.class, "craneArm");
        craneArm.setDirection(DcMotor.Direction.REVERSE);
        craneArm.setTargetPosition(0);
        craneArm.setPower(0.2);
        craneArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        craneArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);


        airplaneLauncher = hardwareMap.get(Servo.class, "airplaneLauncher");
        
        
        leftClawServo = hardwareMap.get(Servo.class, "leftClawServo");
        rightClawServo = hardwareMap.get(Servo.class, "rightClawServo");
        
        leftClawRotator = hardwareMap.get(Servo.class, "leftClawRotator");
        rightClawRotator = hardwareMap.get(Servo.class, "rightClawRotator");
        
        //resets the zero position of the drawer slide motor
        hookLifter = hardwareMap.get(DcMotor.class, "hookLifter");
        hookLifter.setDirection(DcMotor.Direction.REVERSE);
        hookLifter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hookLifter.setTargetPosition(0);
        hookLifter.setPower(0.5);
        hookLifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        //resets the zero position of the drawer slide motor
        robotLifter = hardwareMap.get(DcMotor.class, "robotLifter");
        robotLifter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robotLifter.setTargetPosition(0);
        robotLifter.setPower(1);
        robotLifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        telemetry.update();
        
        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // Pushing the left stick forward MUST make robot go forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
        
        State curState = State.LIFT;
        
        // Wait for the game to start (driver presses PLAY)
        telemetry.update();
        waitForStart();
        airplaneLauncher.setPosition(0);
        neutral();
            while (opModeIsActive()){
                telemetry.addData("Status", "Running");

                fruntRight.setPower(((-gamepad1.left_stick_y - gamepad1.left_stick_x) - gamepad1.right_stick_x) *0.7);
                fruntLeft.setPower(((-gamepad1.left_stick_y + gamepad1.left_stick_x) + gamepad1.right_stick_x) * 0.7);
                jarmy.setPower(((-gamepad1.left_stick_y + gamepad1.left_stick_x) - gamepad1.right_stick_x) *0.7);
                backLeft.setPower(((-gamepad1.left_stick_y - gamepad1.left_stick_x) + gamepad1.right_stick_x) *0.7);

                telemetry.addData("hookLifter position:", hookLifter.getCurrentPosition());

                switch(curState){
                    case LIFT:
                        if(gamepad1.dpad_up){
                            moveVertically(hookLifter, 1700, 0.3);
                            robotLifter.setTargetPosition(7000);
                            curState = EXPERIMENTAL.State.DOWN;
                        }
                        break;
                    case DOWN:
                        if(gamepad1.dpad_left){
                            robotLifter.setTargetPosition(-7000);
                            curState = EXPERIMENTAL.State.LIFT;
                        }
                        else if(gamepad1.dpad_down){
                            moveVertically(hookLifter, 0, 0.3);
                            //curState = State.LIFT;
                        }
                        break;
                    default:
                        curState = EXPERIMENTAL.State.LIFT;
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
                    startTime = System.nanoTime();
                }else if(gamepad2.x){
                    placePixelHigh();
                    startTime = System.nanoTime();
                }else if(gamepad2.b){
                    pickupPixel();
                    startTime = System.nanoTime();
                }else if(gamepad2.y){
                    placePixelLow();
                    startTime = System.nanoTime();
                }else if(gamepad2.dpad_up){
                    craneCounterWeight();
                    startTime = System.nanoTime();
                }

                //Gets the time in nano seconds and caps it to 1 second, which is then adjusted to the speed
                timeElapsed = Math.min(1000000000 * speed, System.nanoTime() - startTime) / 1000000000 * speed;

                leftClawRotator.setPosition(leftClawRotatorOldPosition + (leftClawRotatorOldPosition - leftClawRotatorTargetPosition) * timeElapsed);
                rightClawRotator.setPosition(rightClawRotatorOldPosition + (rightClawRotatorOldPosition - rightClawRotatorTargetPosition) * timeElapsed);






                if(gamepad2.left_bumper){
                    leftClawServo.setPosition(0);
                }else if(gamepad2.left_trigger>0.4){
                    leftClawServo.setPosition(0.6);
                }
                if(gamepad2.right_bumper){
                    rightClawServo.setPosition(1);
                }else if(gamepad2.right_trigger>0.4){
                    rightClawServo.setPosition(0.3);
                }

                if(gamepad2.dpad_right){
                    airplaneLauncher.setPosition(1);
                }else if(gamepad2.dpad_left){
                    craneArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    sleep(1000);
                    craneArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    craneArm.setTargetPosition(0);
                    craneArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                }else if(gamepad2.dpad_down){
                    craneArm.setTargetPosition(-3000);
                }
                telemetry.update();
            }
        }

    public void craneCounterWeight(){
        craneArm.setTargetPosition(1000);
        leftClawRotatorOldPosition = leftClawRotator.getPosition();
        rightClawRotatorOldPosition = rightClawRotator.getPosition();
        leftClawRotatorTargetPosition = 0.1;
        rightClawRotatorTargetPosition = 0.85;
    }
    public void placePixelLow(){
        craneArm.setTargetPosition(1440);
        leftClawRotatorOldPosition = leftClawRotator.getPosition();
        rightClawRotatorOldPosition = rightClawRotator.getPosition();
        leftClawRotatorTargetPosition = 0.1;
        rightClawRotatorTargetPosition = 0.85;
    }
    public void placePixelHigh(){
        craneArm.setTargetPosition(1200);
        leftClawRotatorOldPosition = leftClawRotator.getPosition();
        rightClawRotatorOldPosition = rightClawRotator.getPosition();
        leftClawRotatorTargetPosition = 0.1;
        rightClawRotatorTargetPosition = 0.85;
    }
    public void neutral(){
        craneArm.setTargetPosition(0);
        leftClawRotatorOldPosition = leftClawRotator.getPosition();
        rightClawRotatorOldPosition = rightClawRotator.getPosition();
        leftClawRotatorTargetPosition = 0.1;
        rightClawRotatorTargetPosition = 0.85;
    }
    public void pickupPixel(){
        craneArm.setTargetPosition(0);
        leftClawRotatorOldPosition = leftClawRotator.getPosition();
        rightClawRotatorOldPosition = rightClawRotator.getPosition();
        leftClawRotatorTargetPosition = 0.53;
        rightClawRotatorTargetPosition = 0.42;
    }
    
    public void moveVertically(DcMotor mot, int position, double power){
        mot.setPower(0);
        mot.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mot.setTargetPosition(0);
        mot.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mot.setPower(0);
        mot.setTargetPosition(position);
        mot.setPower(power);
    }
}
