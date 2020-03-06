/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.DigitalInput;
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
  public CANSparkMax primeMotor = new CANSparkMax(ShooterConst.primeMotor, MotorType.kBrushless);
  public CANEncoder encoder = new CANEncoder(shooterMotor);
  public CANPIDController PID = new CANPIDController(shooterMotor);

  public double shooterSpeed = PIDConst.SlowStartingSpeed;
  public double fastShooterSpeed = PIDConst.FastStartingSpeed;

  public DigitalInput limitSwitch = new DigitalInput(1);
  private boolean isBallPrimed = false;

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
    targetMotor.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    printLimitSwitchStatus();


  }
  
  public void printLimitSwitchStatus() {
    SmartDashboard.putBoolean("Primer Limit Status", limitSwitch.get());
  }
  
  public void primeBall(){
    primeMotor.setInverted(false);


        primeMotor.set(ShooterConst.primeMotorPrimeSpeed);

    }

    public void primerOff(){
      primeMotor.setInverted(false);  
      primeMotor.set(0);
  
      }

  public void shootOn(){
    shooterMotor.setInverted(false);
    
    primeMotor.setInverted(false);

    PID.setReference(shooterSpeed, ControlType.kVelocity);

    SmartDashboard.putNumber("Velocity from Encoder", encoder.getVelocity());
    SmartDashboard.putNumber("ShooterSpeed from ShootOn Command", shooterSpeed);
    
      //Change shooterMotorRequiredSpeed when the required speed is determined
      if(encoder.getVelocity() >= (shooterSpeed/3 -500)){
        primeMotor.set(ShooterConst.primeMotorShootSpeed);
      } else if(encoder.getVelocity() <= shooterSpeed/3-500) {
        primeMotor.set(0);
      }
  }
  public void fastShoot(){
    shooterMotor.setInverted(false);
    
    primeMotor.setInverted(false);

    PID.setReference(fastShooterSpeed, ControlType.kVelocity);

    SmartDashboard.putNumber("Velocity from Encoder", encoder.getVelocity());
    SmartDashboard.putNumber("ShooterSpeed from ShootOn Command", fastShooterSpeed);
    
      //Change shooterMotorRequiredSpeed when the required speed is determined
    if(encoder.getVelocity() >= (fastShooterSpeed/3 -500)){
      primeMotor.set(ShooterConst.primeMotorShootSpeed);
    } else if(encoder.getVelocity() <= fastShooterSpeed/3-500) {
      primeMotor.set(0);
    }
    
  }

  public void shootMotorOff(){
    shooterMotor.set(0);
    primeMotor.set(0);
  }


  public void adjShooterSpeedUp(){
    shooterSpeed += 500;
    SmartDashboard.putNumber("Shooter Motor Power", shooterSpeed );
  }


  
  {
    if (shooterSpeed >= 6000*3){shooterSpeed=6000*3;}
    SmartDashboard.putNumber("Target Motor RPM", shooterSpeed);
  }

  public void adjShooterSpeedDown(){
    shooterSpeed -= 500;
    SmartDashboard.putNumber("Target Motor RPM", shooterSpeed);
  } 

  public void rotate(double chubby) {
    targetMotor.set(.2*chubby);
  }

  
}