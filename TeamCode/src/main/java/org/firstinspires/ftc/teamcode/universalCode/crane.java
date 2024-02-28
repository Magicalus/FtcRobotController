package org.firstinspires.ftc.teamcode.universalCode;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;

public class crane {
    private DcMotor leftDrawerSlide, rightDrawerSlide, clawSpinnies;
    public int targetPosition;

    public boolean clawIsBack = false;

    public crane(HardwareMap hardwareMap, boolean clawIsBack){ this(hardwareMap, 0.5, clawIsBack); }

    public crane(HardwareMap hardwareMap, double power, boolean clawIsBack){
        leftDrawerSlide = hardwareMap.get(DcMotor.class, "leftDrawerSlide");
        leftDrawerSlide.setTargetPosition(0);
        leftDrawerSlide.setPower(power);
        leftDrawerSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftDrawerSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        rightDrawerSlide = hardwareMap.get(DcMotor.class, "rightDrawerSlide");
        rightDrawerSlide.setDirection(DcMotorSimple.Direction.REVERSE);
        rightDrawerSlide.setTargetPosition(0);
        rightDrawerSlide.setPower(power);
        rightDrawerSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrawerSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        clawSpinnies = hardwareMap.get(DcMotor.class, "clawSpinnies");
        clawSpinnies.setTargetPosition(0);
        clawSpinnies.setPower(0.3);
        clawSpinnies.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        clawSpinnies.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        clawSpinnies.setDirection(DcMotorSimple.Direction.REVERSE);
        this.clawIsBack = clawIsBack;
        targetPosition = 0;

        //THIS IS VERY BAD
        //Implement it properly during the next meeting ya bozo
        //-Zack, to Zack
        if(values.craneByPower){
            setPower(0);
            leftDrawerSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rightDrawerSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    public void setTargetPosition(int target){
        leftDrawerSlide.setTargetPosition(target);
        rightDrawerSlide.setTargetPosition(target);
        targetPosition = target;
    }

    public void resetEncoders(){
        leftDrawerSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrawerSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        setTargetPosition(0);

        if(values.craneByPower){
            leftDrawerSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rightDrawerSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }else{
            leftDrawerSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightDrawerSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
    }

    public void resetClawSpinnies(){
        clawSpinnies.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        clawSpinnies.setTargetPosition(0);
        clawSpinnies.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void setSpinniesPosition(int target){
        clawSpinnies.setTargetPosition(target);
    }

    public void craneMaintenance(){
        if(offCheck() && targetPosition == 0){
            resetEncoders();
        }
        if(clawSpinnies.getCurrentPosition() < -2574){
            clawIsBack = false;
            resetClawSpinnies();
        }else if(clawIsBack){
            //TEST VALUE
            clawSpinnies.setTargetPosition(-2575);
        }else if(leftDrawerSlide.getCurrentPosition() > 500){
            //TEST VALUES
            clawSpinnies.setTargetPosition(500);
        }else{
            clawSpinnies.setTargetPosition(0);
        }
    }

    public void move(double movement){
        if(values.craneByPower &&
                (getCurrentLeftPosition() + getCurrentRightPosition()) / 2 < values.craneMax &&
                (getCurrentLeftPosition() + getCurrentRightPosition()) / 2 > -10){
            setPower(movement);
        }else if(values.craneByPower){
            setPower(0);
        }else if(movement > values.craneMax){
            setTargetPosition(values.craneMax);
        }else{
            setTargetPosition((int)movement);
        }
    }
    public void setPower(double power){
        leftDrawerSlide.setPower(power);
        rightDrawerSlide.setPower(power);
    }

    public int getCurrentLeftPosition() { return leftDrawerSlide.getCurrentPosition(); }

    public int getCurrentRightPosition() { return rightDrawerSlide.getCurrentPosition(); }
    public int getCurrentSpinniesPosition(){return clawSpinnies.getCurrentPosition();}

    //I'll properly implement this when the values coming from the motors actually work lol
    public boolean offCheck(){
        return false;
    }

    public void pickupPixel(){
    }

    public void clawAintBack(){
        clawIsBack = false;
    }
}