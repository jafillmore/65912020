/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PIDConst;
import frc.robot.Constants.ShooterConst;


public class ShooterSubsystem extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */

   //Create Shooter Motor
  public CANSparkMax shooterMotor = new CANSparkMax(ShooterConst.Shooter, MotorType.kBrushless);
  public CANSparkMax targetMotor = new CANSparkMax(ShooterConst.Targeting, MotorType.kBrushless);
  public VictorSPX primeMotor = new VictorSPX(ShooterConst.primeMotor);
  public double shooterSpeed = PIDConst.StartingSpeed;
  public CANEncoder encoder = new CANEncoder(shooterMotor);
  public CANPIDController PID = new CANPIDController(shooterMotor);

  

  //Change the value when motor speed we are trying to reach is discovered
  private double shooterMotorRequiredSpeed = -1;

  


  public ShooterSubsystem() {
    PID.setP(PIDConst.P);
    PID.setI(PIDConst.I);
    PID.setD(PIDConst.D);
    PID.setIZone(PIDConst.Iz);
    PID.setFF(PIDConst.FF);
    PID.setOutputRange(PIDConst.MinOutput, PIDConst.MaxOutput);
    if (!shooterMotor.getInverted()){
      shooterMotor.setInverted(true);
    }
    if (!primeMotor.getInverted()){
      primeMotor.setInverted(true);
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  

  public void shootOn(){
    PID.setReference(shooterSpeed, ControlType.kVelocity);

    SmartDashboard.putNumber("Velocity for Encoder", encoder.getVelocity());

    //Change shooterMotorRequiredSpeed when the required speed is determined
    if(encoder.getVelocity() >= shooterSpeed){
      primeMotor.set(ControlMode.PercentOutput, ShooterConst.primeMotorSpeed);
    }
  }
  
  public void shootMotorOff(){
    shooterMotor.set(0);
    primeMotor.set(ControlMode.PercentOutput, 0);
  }

  public void adjShooterSpeedUp(){
    shooterSpeed += 500;
    SmartDashboard.putNumber("Shooter Motor RPM", shooterSpeed );
  }

  public void adjShooterSpeedDown(){
    shooterSpeed -= 500;
    SmartDashboard.putNumber("Shooter Motor RPM", shooterSpeed );
  } 

  public void rotate(double chubby) {
    targetMotor.set(-.2*chubby);
  }
  
}