/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConst;


public class ShooterSubsystem extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */

   //Create Shooter Motor
  public static CANSparkMax shooterMotor = new CANSparkMax(ShooterConst.Shooter, MotorType.kBrushless);
  public static CANSparkMax targetMotor = new CANSparkMax(ShooterConst.Targeting, MotorType.kBrushless);
  public static VictorSPX primeMotor = new VictorSPX(ShooterConst.primeMotor);
  public static double shooterSpeed = 0.2;
  


  public ShooterSubsystem() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void shoot(double shootPower){
    primeMotor.set(ControlMode.PercentOutput, -ShooterConst.primeMotorSpeed);
    shooterMotor.set(-shooterSpeed);
    
  }

  public void shootOn(){
    primeMotor.set(ControlMode.PercentOutput, -ShooterConst.primeMotorSpeed);
    shooterMotor.set(-shooterSpeed);
  }
  
  public void shootMotorOff(){
    shooterMotor.set(0);
    primeMotor.set(ControlMode.PercentOutput, 0);
  }

  public void adjShooterSpeedUp(){
    shooterSpeed = shooterSpeed + 0.1;
    SmartDashboard.putNumber("Shooter Motor Power", shooterSpeed );
  }

  public void adjShooterSpeedDown(){
    shooterSpeed = shooterSpeed - 0.1;
    SmartDashboard.putNumber("Shooter Motor Power", shooterSpeed );
  }

  public void rotate(double chubby) {
    targetMotor.set(-.2*chubby);

  }
  
}