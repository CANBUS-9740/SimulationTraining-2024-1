# frc-robot-template
Template for an FRC Command-Based Robot Project

## Arm PID Exercise 

Finish implementing the Double-Jointed Arm subsystem. 

Following this, implement a PID control command for both joints, using the default command design we used in the 

Create a command `ArmCommand`. It must follow these directives 
- Use 2 PID controllers (one per joint) to control the arm.
- Each joint should be able to move to a different angle (e.g. move the bottom joint to 10 degrees and the top to 45 degrees).
- The command must be a default command which runs forever to keep the joints in place and prevent motion. Use the default command pattern from [last meeting](https://github.com/CANBUS-9740/Training-2024/blob/main/companion-material/meeting15/code/src/main/java/frc/robot/commands/ArmCommandForever.java). Make to sure to allow the command to receive two angles (we did 1 last meeting) as to provide an angle for each joint.
- Tune the PID controllers. It is enough to tune one of them and use the constants for both. This is a tough system to tune, use what we learned about each component of PID to find the right combination. Utilize the shuffleboard (or simulation GUI) to help tune. 

The exercise is complete when you are able to command the arm such that it can form an up-side down V (bottom joint at 45 degrees, top joint at -45 degrees). 

![image](https://github.com/CANBUS-9740/SimulationTraining-2024-1/assets/17641355/fcaee4ca-0a80-4f13-8e14-592947f2bf82)

You will have to run the code actually in the simulation actually, make sure to consult with the simulation debug information and mechanism graphical view to check the function of the system. It is recommended to check the system step by step, so first finish writing the subsystem and then check it manually from `teleopPeriodic` and see that it properly responds. Note that the arm can be a bit heavy to lift and will require motor output of more then 0.3 to actually be able to move.
