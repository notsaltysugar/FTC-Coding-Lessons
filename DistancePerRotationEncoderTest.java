/* THIS CODE IS DONE BY ANDREW CHAO FROM FTC TEAM 15305 (updated 1/21/2024)

Andrew is a team executive of the Cerritos Dons 15305, and was the president of the robotics club
based in Cerritos High School, CA, for two years. During his high school career, he has adept
experience in 3D modeling, hardware, and programming. He has been on the team for 3 years, starting
from Freight Frenzy to Centerstage, but has been a part of the club since his freshman year.

To learn more about Andrew, check him out on:
Instagram: https://www.instagram.com/notsaltysugar/
Youtube: https://youtube.com/@notsaltysugar?si=7hgzPQbUmWZCoM8L

This java file outlines the forwards and backwards movement of a 4 wheel drivetrain, regardless of
its wheel type. It shows how to identify motor types and how to program motors using motor encoders.
Each section of the code should have comments detailing the information.


Copyright 2023 FIRST Tech Challenge Team 15305

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

// linking the libraries to be recognized by the program
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

// linking the name and type of the file to the program
@Autonomous(name = "DistancePerRotationEncoderTest")

// this is for assigning electronics to the program and adding universal variables that can be used
throughout the entire program
public class DistancePerRotationEncoderTest extends LinearOpMode {

    // assigning drivetrain motors
    // “F” means front, “R” means rear
    private DcMotor leftF;
    private DcMotor leftR;
    private DcMotor rightF;
    private DcMotor rightR;

    // defining variables to use as values for our encoders
    private int leftFVal;
    private int leftRVal;
    private int rightFVal;
    private int rightRVal;


    @Override
    public void runOpMode() {
    

    // hardware mapping each motor
    leftF = hardwareMap.get(DcMotor.class, "leftF"); 
    leftR = hardwareMap.get(DcMotor.class, "leftR");
    rightF = hardwareMap.get(DcMotor.class, "rightF");
    rightR = hardwareMap.get(DcMotor.class, "rightR");

    // setting each motor to use encoders (VERY IMPORTANT)
    leftF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    leftR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    rightF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    rightR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    // assigning the variables defined above to initialize at 0
    leftFVal = 0;
    leftRVal = 0;
    rightFVal = 0;
    rightRVal = 0;
    
    // telemetry to read out that the initialization is running (not necessary)
    telemetry.addData("Status", "Initialized");
    telemetry.update();
        
    // wait for the user to press the start button
    waitForStart();

    /* goBILDA 5203-series Yellow Jacket 312 RPM motors have a total of 537.7
    ticks per rotation. This value varies from motor to motor, so you have to
    refer to the motor's website for more information. Also, use integer values,
    so round how you see fit. For example, a full rotation from a 312 RPM motor
    would be around 538 ticks. */

    // There are two ways to approach this test. The code here simply covers the first method.

    // 1) Set the motor encoders to run at a full rotation, then measure the distance.

    drive(-538, -538, 538, 538, 0.1);
    sleep(10000);
    
    /* In this example, the motors that are in use are goBILDA 5203-series Yellow Jacket 312 RPM motors.
       Other motors, such as goBILDA 5203-series Yellow Jacket 6000 RPM motors, have
       a total of 91 ticks per rotation.
       A goBILDA 5203-series Yellow Jacket 60 RPM motor would have a total of 5281.1
       (round to 5281) ticks per rotation.
       You would have to check with your motor’s specifications. For goBilda, the total
       ticks per revolution is labeled as “Encoder Resolution”
    */

    // 2) Figure out the radius (r)/diameter (d) of the wheel used and multiply it by π to get the circumference.
    
    /* In this test, we are using goBilda 96 mm mecanum wheels. As the title suggests,
       they have a total diameter of 96 mm.
       Imagine a circle that has been cut and rolled flat into a straight line. The entire
       length of that line is the circumference of the circle,
       and the total distance that the circle can roll in one rotation. The formula for the
       circumference of a circle is “C = πd” or “C = 2πr”
       Since we are using 96 mm diameter wheels, the total distance that a wheel can run in
       one rotation is 96π mm, or about 301.593 mm.
       This also translates to around 11.874 in. This should be around the same distance as
       the test that was run previously.
    */

    /* Now that you have the values, you can use this formula to calculate the necessary ticks
       needed to travel a certain distance.

       “T = DR/πd”, where:
       “t = number of ticks”, “D = desired distance traveled in units”,
       “d = wheel diameter”, & “R = the encoder resolution for the motor”

       Note that the units can be whatever you want them to be, whether that’d be in in or mm,
       but remember to keep it consistent. Don’t mix units together.
       When using the formula, figure out your encoder resolution and your wheel diameter, as
       those values should remain constant the entire time.
       The only thing left is to input the desired distance into the formula to calculate
       the necessary ticks needed, and make sure to round the result to the nearest integer.
       
       Example: We want to go 15ft forward with a wheel diameter of 96mm and a 312 RPM goBilda motor.
       
       We need to convert the units so that they all align. Since we want to go 15ft, we’ll change the
       96mm diameter to around 3.78 in.

       Using “T = DR/πd” and the given units, we’ll get (15ft x 538 encoder resolution)/(3.78π)

       This gets us 679.65, which rounds to 680 ticks needed to travel 15ft. It won’t be pinpoint perfect,
       but it will be good enough.
       
       @param leftFPos: 
       
    */

    }
    

    private void drive(int leftFPos, int leftRPos, int rightFPos, int rightRPos, double speed) {
                
        // adding the defined variables specific to this private function to the variables defined during public class
        leftFVal += leftFPos;
        leftRVal += leftRPos;
        rightFVal += rightFPos;
        rightRVal += rightRPos;
        
        // setting the desired encoder value (in variables) to the desired target position
        leftF.setTargetPosition(leftFVal);
        leftR.setTargetPosition(leftRVal);
        rightF.setTargetPosition(rightFVal);
        rightR.setTargetPosition(rightRVal);
        
        // calling each motor to run to position defined by the setTargetPosition
        leftF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Telling the motor to run at the speed that will be assigned to the speed variable
        leftF.setPower(speed);
        leftR.setPower(speed);
        rightF.setPower(speed);
        rightR.setPower(speed);
        
        // making sure that nothing else runs in the background during auto
        while(opModeIsActive() && leftF.isBusy() && leftR.isBusy() && rightF.isBusy() && rightR.isBusy()) {
            
            idle();
            
        }
        
    }
    
    
}
