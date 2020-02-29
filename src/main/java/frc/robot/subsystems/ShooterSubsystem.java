/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANAnalog;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.vision.VisionThread;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.StripPipeline;
import frc.robot.Constants.PIDConst;
import frc.robot.Constants.ShooterConst;


public class ShooterSubsystem extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */

   //Create Shooter Motor
  public CANSparkMax shooterMotor = new CANSparkMax(ShooterConst.Shooter, MotorType.kBrushless);
  public CANSparkMax targetMotor = new CANSparkMax(ShooterConst.Targeting, MotorType.kBrushless);
  public VictorSPX primeMotor = new VictorSPX(ShooterConst.primeMotor);
  public double shooterSpeed = 0.2;
  public CANAnalog analog = new CANAnalog(shooterMotor, AnalogMode.kAbsolute);
  public CANEncoder encoder = new CANEncoder(shooterMotor);
  public CANPIDController PID = new CANPIDController(shooterMotor);

  private static final int IMG_WIDTH = 320;
  private static final int IMG_HEIGHT = 240;

  private VisionThread visionThread;
  private double centerX = 0.0;
  private RobotDrive drive;

  private final Object imgLock = new Object();

  

  //Change the value when motor speed we are trying to reach is discovered
  private double shooterMotorRequiredSpeed = -1;

  


  public ShooterSubsystem() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void shoot(double shootPower){
    primeMotor.set(ControlMode.PercentOutput, -ShooterConst.primeMotorSpeed);
    shooterMotor.set(-shooterSpeed);
    
  }

  public void shootOn(){
    shooterMotor.set(-shooterSpeed);
    
    SmartDashboard.putNumber("Velocity of SparkMax", analog.getVelocity());
    SmartDashboard.putNumber("Position", analog.getPosition());
    SmartDashboard.putNumber("Velocity for Encoder", encoder.getVelocity());

    //Change shooterMotorRequiredSpeed when the required speed is determined
    if(analog.getVelocity() == shooterMotorRequiredSpeed){
      primeMotor.set(ControlMode.PercentOutput, -ShooterConst.primeMotorSpeed);
    }
  }
  
  public void shootMotorOff(){
    shooterMotor.set(0);
    primeMotor.set(ControlMode.PercentOutput, 0);
  }

  public void adjShooterSpeedUp(){
    shooterSpeed = shooterSpeed + 0.1;
    SmartDashboard.putNumber("Shooter Motor Power", shooterSpeed );
  }

  public void adjShooterSpeedDown(){
    shooterSpeed = shooterSpeed - 0.1;
    SmartDashboard.putNumber("Shooter Motor Power", shooterSpeed );
  }

  public void rotate(double chubby) {
    targetMotor.set(-.2*chubby);
  }


  public void method(){
    UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
    camera.setResolution(IMG_WIDTH, IMG_HEIGHT);

    visionThread = new VisionThread(camera, new StripPipeline(), pipeline -> {
        if (!pipeline.filterContoursOutput().isEmpty()) {
            Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
            synchronized (imgLock) {
                centerX = r.x + (r.width / 2);
            }
        }
    });
    visionThread.start();


    drive = new RobotDrive(1, 2);     //This needs to be changed to drive our shooter motor
  }
  
}