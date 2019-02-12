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

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ElbowJoystick;
import frc.robot.commands.ElbowMaintainON;


/**
 * Add your docs here.
 */
public class Elbow extends Subsystem {
  private TalonSRX elbowMotorTalon;

  private DigitalInput elbowEndUp;
  private DigitalInput elbowEndDown;

  private static final double RAISE_MULTIPLIER = .25;
	private static final double LOWER_MULTIPLIER = .13;
	private static final double MAINTAIN_POWER = .08;
	
  private static final double HIGH_POW = 0.7;
  private static final double LOW_POW = -0.25;

  private static final double raiselowerPower = 0;

  private boolean m_maintain=false;
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

    m_maintain = false;

  }
   
  public void TurnOffMaintain() {
    m_maintain = false;
  }
   
  public void TurnOnMaintain() {
    m_maintain = true;
  }
   
  public boolean GetMaintain() {
    return m_maintain;
  }
  
  public void Raise_Lower(double raiselowerPower) {
    // Positive value is UP, negative Value is down
    boolean goingUp = (raiselowerPower>0);

    if (goingUp) 
    {
      if (!elbowEndUp())
        elbowMotorTalon.set(ControlMode.PercentOutput, raiselowerPower*RAISE_MULTIPLIER);
      else 
      {
        if (m_maintain)
          elbowMotorTalon.set(ControlMode.PercentOutput, MAINTAIN_POWER);
        else
          elbowMotorTalon.set(ControlMode.PercentOutput, 0);
      }
    }
    else 
    {
      if (!elbowEndDown())
        elbowMotorTalon.set(ControlMode.PercentOutput, raiselowerPower*LOWER_MULTIPLIER);
      else
        elbowMotorTalon.set(ControlMode.PercentOutput, 0);
    }
  }
 
  public void Maintain() 
  {

    if (!elbowEndDown())
      if (m_maintain)
        elbowMotorTalon.set(ControlMode.PercentOutput, MAINTAIN_POWER);
      else 
        elbowMotorTalon.set(ControlMode.PercentOutput, 0);
  
    else
      elbowMotorTalon.set(ControlMode.PercentOutput, 0);
  }
  

  public void stop() { 
    elbowMotorTalon.set(ControlMode.PercentOutput, 0);
  }

  public boolean elbowEndUp() {
    return (this.elbowEndUp.get()!=RobotMap.ELBOW_END_OF_TRAVEL_UP_DS);
  }

  public boolean elbowEndDown() {
    return (this.elbowEndDown.get()!=RobotMap.ELBOW_END_OF_TRAVEL_DOWN_DS);
  }

  protected void initDefaultCommand() {
    setDefaultCommand(new ElbowJoystick());
  }


}
