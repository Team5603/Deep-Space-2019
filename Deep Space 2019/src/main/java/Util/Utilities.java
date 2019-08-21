/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package Util;

/**
 * Add your docs here.
 */
public class Utilities {

    public static final double toPower(double value, int power) {
        boolean positive;
        double newValue;

        if (value==0.0)
            return 0;
        else if(value>0)
            positive = true;
        else
            positive = false;

        newValue = Math.pow(Math.abs(value), power);

        if (positive)
            return newValue;
        else
            return -1*newValue;
    }

    public static final double prunePower(double value, double minAmount) {
        /* There's a lot of deadband in the motor controller
        it actually doesn't start moving till around .2 power output.
        This will add a minimum value, i.e. +.15 so that when they are outputting
        .1 it will actually be .25, etc.  This should hopefully allow us to get
        more out of the lower stick values.
        
        */

        boolean positive=true;

        if (value == 0.0)
            return 0.0;
        else {
            if (value<0) positive = false;
            
            value = (Math.abs(value)*(1-minAmount)) + minAmount;

            if (!positive) {
                value = -1*value;
            }
        }
        return value;
    }
    
    public static final double scalePower(double value, double joystickLowTol, double motorLowTol) {
        // joystickLowTol = what joystick value do you want to scale low end of travel (more sensitive), i.e. .75 joystick value will equate to motorLowTol
        // motorLowTol = what joystickLowTol will equate to what motor power, so if .75 joystickLowTol and .5 motorLowTol then .75 joystick will equal .5 motor
        //      output, porportionally, after the low tolerance, then it will be scaled by a factor to get you back to 1 and 1 joystick/motor

        double lowRatio = motorLowTol/joystickLowTol; //s hould check for div by 0 here
        double highRatio = (1-motorLowTol)/(1-joystickLowTol);
        if (value<=joystickLowTol) {
            value = value*lowRatio;
        }
        else {
            value = (joystickLowTol*lowRatio) + ((value-joystickLowTol)*highRatio);
        }
        
        return value;

    }    
}
