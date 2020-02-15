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


// Drive Motor's 
public static final int frontLeft = 1;
public static final int midLeft = 2; 
public static final int backLeft = 3;
public static final int frontRight = 4;
public static final int midRight = 5;
public static final int backRight = 6;

//Shooter Motor
public static final int shootMotor = 7; 

//Joystick Stuff
public static final int leftJoystickPort = 0;
public static final int rightJoystickPort = 1;
public static final int intakeButton = 0;
public static final int intakeNumber = 1;

// Drive Constants 
public final class DriveConst {
public static final int frontLeftMotor = 1;
public static final int midLeftMotor = 2;
public static final int backLeftMotor = 3;
public static final int frontRightMotor = 4;
public static final int midRightMotor = 5;
public static final int backRightMotor = 6; 

}
// Intake Constants
public final class IntakeConst {
public static final int liftMotor = 8;
public static final int intakeMotor = 9;
public static final int intakeTrigger = 1;
public static final double intakeSpeed = 0.5;
public static final double liftPrimeSpeed = 0.15;
public static final double liftShootSpeed = 0.50;
}
// Color Sensor's
private static final double yellowRVal = .300;
private static final double yellowGVal = .600;
private static final double yellowBVal = .100;
private static final double redRVal = .500;
private static final double redGVal = .300;
private static final double redBVal = .100;
private static final double greenRVal = .100;
private static final double greenGVal = .600;
private static final double greenBVal = .300;
private static final double blueRVal = .100;
private static final double blueGVal = .400;
private static final double blueBVal = .500;
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


//Pnematic's
public final class PnemuaticConst{
    public static final int deployA = 1;
    public static final int deployB = 2;

    public static final int extandA = 3;
    public static final int extandB = 4;

    public static final int intakeA = 5;
    public static final int intakeB = 6;

}

}