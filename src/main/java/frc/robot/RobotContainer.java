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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.ArcadeDriveSubsystem;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.PneumaticSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.Constants.JoystickConst;
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
  private final ClimberSubsystem climberSubsystem = new ClimberSubsystem();
  

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

                       
    new JoystickButton(leftJoystick, JoystickConst.toggleIntake)
      .whenHeld(new InstantCommand(pneumaticSubsystem:: deployIntake))
      .whenReleased(new InstantCommand(pneumaticSubsystem::stowIntake));

    // Turn on Intake motors

    new JoystickButton(rightJoystick, JoystickConst.intakeTrigger)
      .whileHeld(new InstantCommand(intakeSubsystem:: turnOnIntake))
      .whenReleased(new InstantCommand(intakeSubsystem:: turnOffIntake));

    // Reverse Intake Lift Motor

    new JoystickButton(rightJoystick, JoystickConst.intakeReverse)
    .whenHeld(new InstantCommand(intakeSubsystem::reverseIntakeLift))
    .whenReleased(new InstantCommand(intakeSubsystem::turnOffIntake));

    new JoystickButton(joeStick, JoystickConst.extendClimbArm)
    .whenPressed(new InstantCommand(pneumaticSubsystem::extendClimbArms));

    new JoystickButton(joeStick, JoystickConst.retractClimbArm)
    .whenPressed(new InstantCommand(pneumaticSubsystem::retractClimbArms));


    new JoystickButton(joeStick, JoystickConst.deployClimbArm)
    .whenPressed(new InstantCommand(pneumaticSubsystem::deployClimbArms));

    new JoystickButton(joeStick, JoystickConst.stowClimbArm)
    .whenPressed(new InstantCommand(pneumaticSubsystem::stowClimbArms));
    
    
 
      new JoystickButton(joeStick, JoystickConst.fire)
      .whileHeld(new RunCommand(() -> shooterSubsystem.shootOn()))
      //-> intakeSubsystem.liftSpeed(IntakeConst.liftShootSpeed)));
      .whenReleased (new InstantCommand(() -> shooterSubsystem.shootMotorOff()));

    new JoystickButton(joeStick, JoystickConst.fastFire)
      .whileHeld(new RunCommand(() -> shooterSubsystem.fastShoot()))
      .whenReleased( new InstantCommand(() -> shooterSubsystem.shootMotorOff()));


    new JoystickButton(joeStick, JoystickConst.increaseSpeed)
      .whenPressed(new InstantCommand(() -> shooterSubsystem.adjShooterSpeedUp()));

    new JoystickButton(joeStick, JoystickConst.decreaseSpeed)
      .whenPressed(new InstantCommand(() -> shooterSubsystem.adjShooterSpeedDown()));
   
    shooterSubsystem.setDefaultCommand(
      new RunCommand(() -> shooterSubsystem .rotate(joeStick.getRawAxis(2)), shooterSubsystem));


    Shuffleboard.getTab("Shooter value").add("rpm", shooterSubsystem.encoder.getVelocity());
    SmartDashboard.putNumber("Target Motor RPM", shooterSubsystem.shooterSpeed);


    climberSubsystem.setDefaultCommand(
      new RunCommand(() -> climberSubsystem.turnOnBalanceMotors(joeStick.getRawAxis(0)), climberSubsystem));
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
