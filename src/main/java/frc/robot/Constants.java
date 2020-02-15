/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    //motor Id's for spark max

    public static final int frontLeft = 1;
    public static final int midLeft =2;
    public static final int backLeft = 3;
    public static final int frontRight = 4;
    public static final int midRight = 5;
    public static final int backRight = 6;
    //shootermotor
    public static final int shooterMotor = 7;

    //Button layout
    //public JoystickButton deployClimbArm = new JoystickButton(leftJoystickPort, 1);

    public static final int leftJoystickPort = 0;
    public static final int rightJoystickPort = 1;
    public static final int intakeMotor = 0;


    public final class DriveConst {
        public static final int frontLeftMotor = 1;
        public static final int midLeftMotor = 2;
        public static final int backLeftMotor = 3;
        public static final int frontRightMotor = 4;
        public static final int midRightMotor = 5;
        public static final int backRightMotor = 6;

    }

    public final class PnemuaticConst{
        public static final int deployA = 1;
        public static final int deployB = 2;

        public static final int extandA = 3;
        public static final int extandB = 4;

        public static final int intakeA = 5;
        public static final int intakeB = 6;
    
    }
    
    
    
}

