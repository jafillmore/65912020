/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DeployIntake;
import frc.robot.subsystems.ArcadeDriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.PneumaticSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.Constants.JoystickConst;
import frc.robot.Constants.IntakeConst;
/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private static final Command m_autoCommand = null;
  // The robot's subsystems and commands are defined here...
  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  private final ArcadeDriveSubsystem arcadeDriveSubsystem = new ArcadeDriveSubsystem();
  private final PneumaticSubsystem pneumaticSubsystem = new PneumaticSubsystem();
  private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
  

  Joystick leftJoystick = new Joystick(JoystickConst.leftJoystickPort);
  Joystick rightJoystick = new Joystick(JoystickConst.rightJoystickPort);
  Joystick joeStick = new Joystick(JoystickConst.joeStickPort);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
   configureButtonBindings();
  }
  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //ArcadeDriveSubsystem Joysticks 
    
    arcadeDriveSubsystem.setDefaultCommand(
      new RunCommand(() -> arcadeDriveSubsystem
          .arcadeDrive(leftJoystick.getY(), 
                       rightJoystick.getZ()), arcadeDriveSubsystem));


    new JoystickButton(leftJoystick, Constants.intakeNumber)
      .whenPressed(new DeployIntake(pneumaticSubsystem));

    new JoystickButton(leftJoystick, Constants.deployNumber)
      .whenPressed(new InstantCommand(pneumaticSubsystem::deployArms));

    new JoystickButton(leftJoystick, Constants.deployNumber)
      .whenPressed(new InstantCommand(pneumaticSubsystem::stowArms));
      
      new JoystickButton(joeStick, JoystickConst.fire)
      .whenPressed(new RunCommand(() -> shooterSubsystem.shoot(shooterSubsystem.shooterSpeed)))
      //-> intakeSubsystem.liftSpeed(IntakeConst.liftShootSpeed)));
      .whenReleased (new RunCommand(() -> shooterSubsystem.shoot(0.0)));

    new JoystickButton(joeStick, JoystickConst.increaseSpeed)
      .whenPressed(new RunCommand(() -> shooterSubsystem.adjShooterSpeedUp()));

    new JoystickButton(joeStick, JoystickConst.decreaseSpeed)
      .whenPressed(new RunCommand(() -> shooterSubsystem.adjShooterSpeedDown()));
   
    new RunCommand(() -> shooterSubsystem.rotate(joeStick.getZ()));

      //Intake stuffs
    new JoystickButton(rightJoystick, JoystickConst.intakeTrigger).whileHeld(() -> intakeSubsystem.intakeSpeed(IntakeConst.intakeSpeed))
    .whenReleased(() -> intakeSubsystem.intakeSpeed(0.0));

    Shuffleboard.getTab("Shooter value").add("rpm", shooterSubsystem.shooterMotor.getAppliedOutput());
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
