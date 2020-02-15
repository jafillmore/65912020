/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PnemuaticConst;

public class PneumaticSubsystem extends SubsystemBase {
  public static Object deployClimbArmsSolenoid;




/**
   * Creates a new PneumaticSubsystem.
   */
  Compressor c = new Compressor(0);


public DoubleSolenoid extendArmSolenoid = new DoubleSolenoid(7, 0);

  private DoubleSolenoid deployArmsDouble = new DoubleSolenoid(PnemuaticConst.deployA, PnemuaticConst.deployB);
  private DoubleSolenoid extendArmsDouble = new DoubleSolenoid(PnemuaticConst.extandA, PnemuaticConst.extandB);
  private DoubleSolenoid intakeDouble = new DoubleSolenoid(PnemuaticConst.intakeA, PnemuaticConst.intakeB);




  public void deployArms() {
    deployArmsDouble.set(Value.kForward);
    deployArmsDouble.set(Value.kOff);   
  } 
  public void extandArms(){
    
  }
  

/*
  deployDouble.set(kOff);
  deployDouble.set(kFoward);
  deployDouble.set(kReverse);
*/
  public PneumaticSubsystem() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}