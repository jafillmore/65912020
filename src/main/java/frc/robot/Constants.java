/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {


//climb arm motor
public static final int intakeNumber = 1;
public static final int deployNumber = 1;

//Joystick Stuff
public final class JoystickConst {
    
    //JoystickPort #s
    public static final int leftJoystickPort = 0;
    public static final int rightJoystickPort = 1;
    public static final int joeStickPort = 2;

    //JoystickButton #s for Intake
    public static final int toggleIntake = 1;  // Left Joystick
    public static final int intakeTrigger = 1; // Right Joystick
    public static final int intakeReverse = 2; // Right Joystick

    //JoystickButton #s for ControlPannel
    public static final int toggleColorArm = 2;
    public static final int spin3 = 3;
    public static final int spinToColor = 4;


    //Joystick Button #s for Climbing
    public static final int deployClimbArm = 5;
    public static final int stowClimbArm = 6;
    public static final int extendClimbArm = 7;
    public static final int retractClimbArm = 8;
    
    //Joystick Button #s for Shooting
    public static final int toggleAutoShoot = 9;
    public static final int fire = 10;
    public static final int rotateLeft = 11;
    public static final int rotateRight = 12;
    public static final int increaseSpeed = 26;
    public static final int decreaseSpeed = 27;
}


// Drive Constants CAN IDs
public final class DriveConst {
    public static final int frontLeftMotor = 4;
    public static final int midLeftMotor = 5;
    public static final int frontRightMotor = 1;
    public static final int midRightMotor = 2;

}


public final class ClimbConst {
    public static final int leftArmMotor = 13;
    public static final int rightArmMotor = 14;
}

// Intake Constants
public final class IntakeConst {
    //Motor CAN IDs
    public static final int liftMotor = 10;
    public static final int intakeMotor = 9;
    
    
    //Motor Speeds for Intake
    public static final double intakeSpeed = 0.1;
    public static final double liftSpeed = -0.80;


}

 public static final class ControlPanelConst{
    // Wheel Spinning Motor CAN ID
    public static final int spinningMotor = 12;

    // Color Sensor's
    public static final double yellowRVal = .300;
    public static final double yellowGVal = .600;
    public static final double yellowBVal = .100;
    public static final double redRVal = .500;
    public static final double redGVal = .300;
    public static final double redBVal = .100;
    public static final double greenRVal = .100;
    public static final double greenGVal = .600;
    public static final double greenBVal = .300;
    public static final double blueRVal = .100;
    public static final double blueGVal = .400;
    public static final double blueBVal = .500;
    public static final Color blueTarget = ColorMatch.makeColor(blueRVal, blueGVal, blueBVal);
    public static final Color greenTarget = ColorMatch.makeColor(greenRVal, greenGVal, greenBVal);
    public static final Color redTarget = ColorMatch.makeColor(redRVal, redGVal, redBVal);
    public static final Color yellowTarget = ColorMatch.makeColor(yellowRVal, yellowGVal, yellowBVal);
    public static final int blueTargetButNum = 1;
    public static final int greenTargetButtonNum = 2;
    public static final int redTargetButtonNum = 3;
    public static final int yellowTargetButtonNum = 4;
    public static final int spinPanelButtonNum = 5;
    public static final int detectColorButtonNum = 6;
    public static I2C.Port colorSensorPort = I2C.Port.kOnboard;
 }

//Pnematic's
public static final class PnemuaticConst{

    //Deploying Climb Arms
    public static final int deployA = 2;
    public static final int deployB = 3;


    //Extend Climb Arms
    public static final int extandA = 4;
    public static final int extandB = 5;


    //Deploy Intake
    public static final int intakeA = 1;
    public static final int intakeB = 0 ;
   
   public static final int deployIntakeTrigger = 1;     
}
   //Shooter Motor
   public static final class ShooterConst{

    public static final double primeMotorSpeed = 1.0;
    public static final double primeShootSpeed = 0.50;
    
    //Motor CAN IDs
    public static final int Shooter = 8;
    public static final int Targeting = 7;
    public static final int primeMotor = 11;
}

    public static final class PIDConst{

        public static final double P = 6e-5; 
        public static final double I = 0;
        public static final double D = 0; 
        public static final double Iz = 0; 
        public static final double FF = 0.000015; 
        public static final double MaxOutput = 5700; 
        public static final double MinOutput = 0;
        public static final double StartingSpeed = 2500;
    }    

    public static final class AutoConst{
        public static final double AutoDriveSpeed = .25;
        public static final double AutoDriveDistanceInches = 40;
    }


}