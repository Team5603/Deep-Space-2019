/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

//import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.ElbowJoystick;



/**
 * Add your docs here.
 */
public class Elbow extends Subsystem {
  private TalonSRX elbowMotorTalon;

  private boolean m_BuckMaintain = false;
  private boolean m_maintain = false;

  //private DigitalInput elbowEndUp;
  //private DigitalInput elbowEndDown;

  private static final double RAISE_MULTIPLIER = .30;
	private static final double LOWER_MULTIPLIER = .2; // was .07 3/8/19 12:49pm
	private static final double MAINTAIN_POWER = .09;
	
  private static final double HIGH_POW = 1.0;
  private static final double LOW_POW = -1.0;

  //private static final double raiselowerPower = 0;

  
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public Elbow(){  
     // Set the default command for a subsystem here.
    
    elbowMotorTalon = new TalonSRX(RobotMap.ELBOW_MOTOR_1);

		elbowMotorTalon.configPeakOutputForward(HIGH_POW, 0);
		elbowMotorTalon.configPeakOutputReverse(LOW_POW, 0);
		elbowMotorTalon.configNominalOutputForward(0.0, 0);
		elbowMotorTalon.configNominalOutputReverse(0.0, 0);
    
    //Break Mode
    elbowMotorTalon.setNeutralMode(NeutralMode.Brake); 

     //Inverted or Not...
		elbowMotorTalon.setInverted(true);
		
    //Encoder
    elbowMotorTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
    elbowMotorTalon.setSelectedSensorPosition(0, 0, 0);

  }
   
  public void Raise_Lower(Double Speed){
    if (Speed == 0) {
      if (m_BuckMaintain)
        elbowMotorTalon.set(ControlMode.PercentOutput, MAINTAIN_POWER);
      else
        elbowMotorTalon.set(ControlMode.PercentOutput, 0);
    }else{
      SmartDashboard.putNumber("ELbow Speed", Speed);
      if (Speed>0)
        elbowMotorTalon.set(ControlMode.PercentOutput, -Speed*LOWER_MULTIPLIER);
      else
        elbowMotorTalon.set(ControlMode.PercentOutput, -Speed*RAISE_MULTIPLIER);
    }
  }
  public void SetMaintain(boolean DoMaintain){
     m_BuckMaintain = DoMaintain;
  }
  
  
  
 

  protected void initDefaultCommand() {
    setDefaultCommand(new ElbowJoystick());
  }

  public void Stop() {
  }

  public boolean GetMaintain() {
	  return m_maintain;
  }

}
