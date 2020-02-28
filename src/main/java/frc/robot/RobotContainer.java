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
<<<<<<< HEAD
import frc.robot.Constants.IntakeConst;
import frc.robot.Constants.JoystickConst;
import frc.robot.commands.DeployIntake;
=======
import frc.robot.Constants.JoystickConst;
>>>>>>> d183da81243675b01639dc4287fe1dbee512ffff
import frc.robot.subsystems.ArcadeDriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.PneumaticSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
<<<<<<< HEAD
=======
import frc.robot.subsystems.ClimberSubsystem;
>>>>>>> d183da81243675b01639dc4287fe1dbee512ffff
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


    // Deploy Intake while left trigger is held, retract when it is release
    new JoystickButton(leftJoystick, Constants.intakeNumber)
      .whileHeld(new InstantCommand(pneumaticSubsystem::deployIntake))
      .whenReleased(new InstantCommand(pneumaticSubsystem::stowIntake));

    // Turn on Intake motors
    /*new JoystickButton(rightJoystick, JoystickConst.intakeTrigger)
      .whileHeld(() -> intakeSubsystem.turnOnIntake())
      .whenReleased(() -> intakeSubsystem.turnOffIntake());
    */
    new JoystickButton(leftJoystick, JoystickConst.intakeTrigger)
      .whileHeld(new InstantCommand(intakeSubsystem::turnOnIntake))
      .whenReleased(new InstantCommand(intakeSubsystem::turnOffIntake));
    

    // Reverse Intake lift motor (to try to fix stuff ball)
    new JoystickButton(rightJoystick, JoystickConst.intakeReverse)
      .whileHeld(new InstantCommand(intakeSubsystem::reverseIntakeLift));

    new JoystickButton(joeStick, Constants.deployNumber)
      .whenPressed(new InstantCommand(pneumaticSubsystem::deployArms));

    new JoystickButton(leftJoystick, Constants.deployNumber)
      .whenPressed(new InstantCommand(pneumaticSubsystem::stowArms));
      
      new JoystickButton(joeStick, JoystickConst.fire)
      .whenHeld(new RunCommand(() -> shooterSubsystem.shootOn(/*shooterSubsystem.shooterSpeed*/)))
      //-> intakeSubsystem.liftSpeed(IntakeConst.liftShootSpeed)));
      .whenReleased (new RunCommand(() -> shooterSubsystem.shootMotorOff()));

    /*new JoystickButton(joeStick, JoystickConst.fire)
      .whileHeld(new RunCommand(ShooterSubsystem::shootOn));
      .whenReleased(new InstantCommand(ShooterSubsystem::shooterMotorOff));*/

    new JoystickButton(joeStick, JoystickConst.increaseSpeed)
      .whenPressed(new RunCommand(() -> shooterSubsystem.adjShooterSpeedUp()));

    new JoystickButton(joeStick, JoystickConst.decreaseSpeed)
      .whenPressed(new RunCommand(() -> shooterSubsystem.adjShooterSpeedDown()));
   
    shooterSubsystem.setDefaultCommand(
      new RunCommand(() -> shooterSubsystem .rotate(joeStick.getRawAxis(2)), shooterSubsystem));


    Shuffleboard.getTab("Shooter value").add("rpm", shooterSubsystem.shooterMotor.getAppliedOutput());

    shooterSubsystem.setDefaultCommand(
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
