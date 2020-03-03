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
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.vision.VisionThread;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PIDConst;
import frc.robot.Constants.ShooterConst;
import frc.robot.StripPipeline;


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

  private DigitalInput limitSwitch = new DigitalInput(1);
  private boolean isBallPrimed = false;

  private static final int IMG_WIDTH = 320;
  private static final int IMG_HEIGHT = 240;

  private VisionThread visionThread;
  private double centerX = 0.0;

  private final Object imgLock = new Object();

  public UsbCamera frontCam;
  public UsbCamera targetCam;
  


  //Change the value when motor speed we are trying to reach is discovered
  private double shooterMotorRequiredSpeed = 5450;

  


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

    frontCam = CameraServer.getInstance().startAutomaticCapture(0);
    targetCam = CameraServer.getInstance().startAutomaticCapture(1);
    frontCam.setResolution(1280,720);
    targetCam.setResolution(1280, 720);
    
    

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  
  public void primeBall(){
    primeMotor.setInverted(false);

    primeMotor.set(.5);
    if(limitSwitch.get()){
      primeMotor.set(0);
      isBallPrimed = true;
    } else {
      isBallPrimed = false;
    }
    

  }

  public void shootOn(){
    shooterMotor.setInverted(false);
    
    primeMotor.setInverted(false);

    PID.setReference(shooterSpeed, ControlType.kVelocity);

    SmartDashboard.putNumber("Velocity from Encoder", encoder.getVelocity());
    SmartDashboard.putNumber("ShooterSpeed from ShootOn Command", shooterSpeed);
    
    if(!isBallPrimed){
      primeBall();
    } else {
     

      //Change shooterMotorRequiredSpeed when the required speed is determined
      if(encoder.getVelocity() >= (shooterSpeed/3 -500)){
        primeMotor.set(ShooterConst.primeMotorSpeed);
      } else if(encoder.getVelocity() <= shooterSpeed/3-500) {
        primeMotor.set(0);
      }
    }
  }
  public void fastShoot(){
    shooterMotor.setInverted(false);
    
    primeMotor.setInverted(false);

    PID.setReference(fastShooterSpeed, ControlType.kVelocity);

    SmartDashboard.putNumber("Velocity from Encoder", encoder.getVelocity());
    SmartDashboard.putNumber("ShooterSpeed from ShootOn Command", fastShooterSpeed);
    
    if(!isBallPrimed){
      primeBall();
    } else {
      //Change shooterMotorRequiredSpeed when the required speed is determined
      if(encoder.getVelocity() >= (fastShooterSpeed/3 -500)){
        primeMotor.set(ShooterConst.primeMotorSpeed);
      } else if(encoder.getVelocity() <= fastShooterSpeed/3-500) {
        primeMotor.set(0);
      }
    }
  }
  public void adjShooterSpeedUp(){
    shooterSpeed = shooterSpeed + 0.10;
    SmartDashboard.putNumber("Shooter Motor Power", shooterSpeed );
  }

  public void shootMotorOff(){
    shooterMotor.set(0);
    primeMotor.set(0);
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
    targetMotor.set(-.2*chubby);
  }


  public void method(){

    CvSource outputStream = CameraServer.getInstance().putVideo("Processed in Main", 1280, 720);
    visionThread = new VisionThread(targetCam, new StripPipeline(), stripPipeline -> {
			SmartDashboard.putNumber("Number of Contours Found", stripPipeline.findContoursOutput().size());
                                     
			if (stripPipeline.filterContoursOutput().isEmpty())
			  {SmartDashboard.putString("Filterd Contour Status:", "No Contours Found");
			};
	  
			
			if (!stripPipeline.filterContoursOutput().isEmpty()) {
			  
			  SmartDashboard.putNumber("Number of Contours Found", stripPipeline.filterContoursOutput().size());
	  
				Rect r = Imgproc.boundingRect(stripPipeline.filterContoursOutput().get(0));
				synchronized (imgLock) {
					//centerX = r.x + (r.width / 2);

					Object arrayList = stripPipeline.filterContoursOutputArray();

					double[] array = (double[]) arrayList;

					//SmartDashboard.putNumberArray("Filter Contours Output", stripPipeline.filterContoursOutput.toArray());
					//SmartDashboard.putNumber("Center X from Subsys VisionThread", centerX);
					SmartDashboard.putNumberArray("Array", array);
				}

				outputStream.putFrame(stripPipeline.cvAbsdiffOutput);
			}
	  
		});
		visionThread.start();

    // xxxxxxx

   // drive = new RobotDrive(1, 2);     //This needs to be changed to drive our shooter motor
  }
  
}