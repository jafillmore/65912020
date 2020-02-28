/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ControlPanelSubsystem;

public class ControlPanel extends CommandBase {
  /**
   * Creates a new ControlPanel.
   */
  private ControlPanelSubsystem controlPanelSubsystem;
  private int rotationCount = 0;
  private Color detectedColor;


  public ControlPanel(ControlPanelSubsystem subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    controlPanelSubsystem = subsystem;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    detectedColor = null;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(controlPanelSubsystem.match.color == Constants.ControlPanelConst.yellowTarget){
    detectedColor = Constants.ControlPanelConst.yellowTarget;
  } else if (controlPanelSubsystem.match.color == Constants.ControlPanelConst.blueTarget){
    detectedColor = Constants.ControlPanelConst.blueTarget;
  } else if (controlPanelSubsystem.match.color == Constants.ControlPanelConst.greenTarget){
    detectedColor = Constants.ControlPanelConst.greenTarget;
  } else if (controlPanelSubsystem.match.color == Constants.ControlPanelConst.redTarget){
    detectedColor = Constants.ControlPanelConst.redTarget;
  }

    for(int i = 0; rotationCount < 3; i++){
     if(controlPanelSubsystem.match.color == detectedColor){
        if(i%2 == 0){
          i = 0;
          rotationCount++;
          i++;
        }
      }
    }


  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
