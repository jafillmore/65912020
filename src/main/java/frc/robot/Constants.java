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
public static final int strafeLeftRight = 8;
public static final int leftJoystickPort = 0;
public static final int rightJoystickPort = 0;
public static final int intakeNumber = 0;

//Joystick Stuff
public final class JoystickConst {
    
    //JoystickPort #s
    public static final int leftJoystickPort = 0;
    public static final int rightJoystickPort = 1;
    public static final int shooterJoystickPort = 2;

    //JoystickButton #s for Intake
    public static final int toggleIntake = 1;
    public static final int intakeTrigger = 1;

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


}


// Drive Constants CAN IDs
public final class DriveConst {
    public static final int frontLeftMotor = 4;
    public static final int midLeftMotor = 5;
    public static final int backLeftMotor = 6;
    public static final int frontRightMotor = 1;
    public static final int midRightMotor = 2;
    public static final int backRightMotor = 3; 

}


// Intake Constants
public final class IntakeConst {

    public static final int liftMotor = 8;
    public static final int intakeMotor = 9;
    public static final double intakeSpeed = 0.5;
    public static final double liftPrimeSpeed = 0.15;
    public static final double liftShootSpeed = 0.50;

}

 public static final class ControlPannelConst{
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
    public static final int deployA = 1;
    public static final int deployB = 2;


    //Extend Climb Arms
    public static final int extandA = 3;
    public static final int extandB = 4;


    //Deploy Intake
    public static final int intakeA = 1;
    public static final int intakeB = 0 ;
   
   public static final int deployIntakeTrigger = 1;     
}
   //Shooter Motor
   public static final class ShooterConst{
    public static final int Shooter = 0;
    public static final int Targeting = 1;
}



}