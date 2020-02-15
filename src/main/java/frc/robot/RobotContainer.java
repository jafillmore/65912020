v/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ArcadeDriveSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

// Test from New Programming Laptop
// Test from Middle Programming Laptop
// Test from crappy Programming Laptop


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final IntakeSubsystem intakesubsystem = new IntakeSubsystem();
  private final ArcadeDriveSubsystem arcadedrivesubsystem = new ArcadeDriveSubsystem();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);




  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    Joystick leftJoystick = new Joystick(Constants.leftJoystickPort);
    JoystickButton intakeButton = new JoystickButton(leftJoystick, Constants.intakeNumber);

    Joystick rightJoystick = new Joystick(Constants.rightJoystickPort);
    JoystickButton deployIntakeButton = new JoystickButton(rightJoystick, Constants.intakeNumber);
  }
  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //ArcadeDriveSubsystem Joysticks  arcadeDriveSubsystem.setDefaultCommand(new RunCommand(

      () -> arcadeDriveSubsystem.arcadeDrive(leftJoystick.getY(), leftJoystick.getZ(), arcadeDriveSubsystem);

      //Intake stuffs
    new JoystickButton(leftJoystick, Constants.shootButton).whileHeld -> (IntakeSubsystem.shoot(leftJoystick())
    .whenReleased(() -> IntakeSubsystem.primeSpeed());

  Shuffleboard.getTab("Shooter value").add("rpm", intakeSubsystem.shooterButton.getAppliedOutput());

  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
