/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;



/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  
  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
  
  //motors and stuff
  
  
  public static final int leftmotorfront = 11;
  public static final int leftmotorback = 12;
  public static final int rightmotorfront = 13;
  public static final int rightmotorback = 14;
  public static final int SlideMotor = 20;
  public static final int WheelyBar = 21;
  public static final int OtherWheelyBar = 22;
  public static final int LiftyBar = 23;
  public static final int AntiOutTake = 24;
  public static final int Elbow = 25;


  
  //joystick stuf
  public static int joystick = 0;
  public static int lYstick = 1;
  public static int rYstick = 5;
  public static int slideaxisL = 2;
  public static int slideaxisR = 3;
  public static int updawg = 1;
  public static int downdawg = 2;

  //sensors
  public static final int linesensorFL = 0;
  public static final int linesensorFLM = 1;
  public static final int linesensorFM = 2;
  public static final int linesensorFRM = 3;
  public static final int linesensorFR = 4;
  public static final int linesensorBL = 5;
  public static final int linesensorBLM = 6;
  public static final int linesensorBM = 7;
  public static final int linesensorBRM = 8;
  public static final int linesensorBR = 9;

  //Pnematic Stuf
  public static final int Compressor = 19;
  






}

