/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType; 

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConst;

public class ArcadeDriveSubsystem extends SubsystemBase {
  public static final String robotDrive = null;
/**
   * Creates a new ArcadeDriveSubsystem.
   */
  // Motor Types
  public CANSparkMax frontLeft = new CANSparkMax(DriveConst.frontLeftMotor, MotorType.kBrushless);
  public CANSparkMax midLeft = new CANSparkMax(DriveConst.midLeftMotor, MotorType.kBrushless);
  public CANSparkMax frontRight = new CANSparkMax(DriveConst.frontRightMotor, MotorType.kBrushless);
  public CANSparkMax midRight = new CANSparkMax(DriveConst.midRightMotor, MotorType.kBrushless);

  public CANEncoder frontLeftEncoder = new CANEncoder(frontLeft);
  public CANEncoder midLeftEncoder = new CANEncoder(midLeft);
  public CANEncoder frontRightEncoder = new CANEncoder(frontRight);
  public CANEncoder midRightEncoder = new CANEncoder(midRight);

  private double averageEncoderDistance;
  
 

  // Speed Controller Group's 
  public SpeedControllerGroup leftMotors = new SpeedControllerGroup(frontLeft, midLeft);
  public SpeedControllerGroup rightMotors = new SpeedControllerGroup(frontRight, midRight);

  //DifferentialGroup 
  public DifferentialDrive robotdrive = new DifferentialDrive(leftMotors, rightMotors);

  //Joystick
  //public Joystick mainJoystick = new Joystick(0);

  public void arcadeDrive(double fwd, double rot) {
  robotdrive.arcadeDrive(-fwd, rot);
  }

  public void resetEncoders(){

  }

  public void averageEncoderDistance(){
    
  }
  public double getAverageEncoderDistance(){
    return averageEncoderDistance;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    robotdrive.setDeadband(0.06);
  }

  
}
