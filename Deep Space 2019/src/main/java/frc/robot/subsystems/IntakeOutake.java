/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.nio.charset.Charset;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.IntakeOutakeJoystick;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
/**
 * Add your docs here.
 */
public class IntakeOutake extends Subsystem 
{
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private static final double INTAKE_POWER = .8;
  private VictorSPX m_innout;
  private boolean m_limitLastState;
              
     
    @Override
  public void initDefaultCommand(){   
  // Set the default command for a subsystem here.
  setDefaultCommand(new IntakeOutakeJoystick());
  m_innout = new VictorSPX(RobotMap.AntiOutTake);
    
  
        

  }

  public void In(double Speed){
    m_innout.set(ControlMode.PercentOutput, Speed);
  }
  public void Out(Double Speed) {
    m_innout.set(ControlMode.PercentOutput, -Speed);
  }

public void In(JoystickButton outtakeButton) {
}

public void In(Button intakeButton) {
}

public void Out(JoystickButton outtakeButton) {
}

   
}

