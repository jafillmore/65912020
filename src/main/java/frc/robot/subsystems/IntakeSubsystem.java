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
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConst;


// Intake System
public class IntakeSubsystem extends SubsystemBase {
  /**
   * Creates a new Intakesubsystem.
   */

   //Intake Motors 
  private VictorSPX liftMotor = new VictorSPX(IntakeConst.liftMotor);
  private CANSparkMax intakeMotor = new CANSparkMax(IntakeConst.intakeMotor, MotorType.kBrushless);

  //Intake Settings
  public void intakeSpeed(double intSP) {
    intakeMotor.set(intSP);
  }
  public void liftSpeed(double intliftSP)  {
    liftMotor.set(ControlMode.PercentOutput, intliftSP);
  }

  public IntakeSubsystem() {


  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
