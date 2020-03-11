/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.command;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunCommandTime extends CommandBase {

  private Command cmd;
  private double runTime, startTime;

  /**
   * Creates a new RunCommandTime.
   */
  
  // Constructor that takes in a command and the runtime of the command in seconds
  public RunCommandTime(Command cmd, double runTime) {
    this.cmd = cmd;
    this.runTime = runTime;
    
    // Adds the requirements for the RunCommandTime based off of the requirements for cmd
    for(var sys : cmd.getRequirements()){
      addRequirements(sys);
    }
  }

  // Called when the command is initially scheduled.
  
  // Sets the startTime to how long the robot has been running in a match and initializes cmd
  @Override
  public void initialize() {
    startTime = Timer.getFPGATimestamp();
    cmd.initialize();
  }

  // Called every time the scheduler runs while the command is scheduled.

  // Executes cmd
  @Override
  public void execute() {
    cmd.execute();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    cmd.end(interrupted);
  }

  // Returns true when the command should end.

  // Returns true if the robot has been running for the desired time we want it to run or if cmd is finished
  @Override
  public boolean isFinished() {
    return (((Timer.getFPGATimestamp() - startTime) >= runTime) || cmd.isFinished());
  }
}
