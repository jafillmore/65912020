/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConst;
import frc.robot.Constants.ShooterConst;;


public class ShooterSubsystem extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */

   //Create Shooter Motor
  public CANSparkMax shooterMotor = new CANSparkMax(ShooterConst.Shooter, MotorType.kBrushless);
  public CANSparkMax targetMotor = new CANSparkMax(ShooterConst.Targeting, MotorType.kBrushless);
  public VictorSPX prime = new VictorSPX(IntakeConst.primeMotor);
  public Double shooterSpeed = 0.5;


  public ShooterSubsystem() {  
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void shoot(double shootPower){
    shooterMotor.set(shootPower);
    
  }
  
  public void adjShooterSpeedUp(){
    shooterSpeed = shooterSpeed + 0.10;
    SmartDashboard.putNumber("Shooter Motor Power", shooterSpeed );
  }

  public void adjShooterSpeedDown(){
    shooterSpeed = shooterSpeed - 0.10;
    SmartDashboard.putNumber("Shooter Motor Power", shooterSpeed );
  }

  public double rotate(double chubby) {
    shooterMotor.set(0.1 * chubby);

    return chubby;
  }
  
}