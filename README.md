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

### Simulation Use

The WPILib simulator basically allows running robot code on any platform, without needing the actual hardware of the robot. It does this by _simulating_ functionalities of on robot hardware (like IO ports and devices), instead of having real ones. However, it is not so straightforward to use, since in order to have a simulated robot, we must configure it (with code) to properly simulate the different systems and devices that compose the robot.
For the purpose of this exercise, code was already made to simulate functionality of each of the systems. Your only job will be to use it. For each system a class was already made with a few code lines related to the simulation. Most of all you will find a commented-out line in the constructor which initializes the simulation related class. Un-comment it and supply it with instances of devices in the system (motors mostly). Do not touch it any further, just focus on implementing your code. This class will simulate behavior of motors and sensors to make it appear as if there is a functioning robot.

Please note that the simulation is limited, and the robot may not function as a real one is expected to. However, it should be good enough. Additionaly, note that only specific parts are simulated, so do not stray from the tasks, as attempting to do things that aren't requested or specified may not function as not simulation code was made for it.

To run the simulation, open the terminal and run `.\gradlew.bat simulateJava` instead of deploying. This will start the code and launch a glass window from which you can control the robot. Read [here](https://docs.wpilib.org/en/stable/docs/software/wpilib-tools/robot-simulation/simulation-gui.html) to see about the simulation gui. You should notice that you can control the robot mode and add gamepads/hid devices to operate your system.

In the simulation gui, you will find the _networktables_ window which allows you to access and control entries. These entries reflect values from the robot. You will be able to see all values displayed to the shuffleboard. Other than your own values, you will find the `SimDebug` root table. The values in this table are managed from the simulation control to see more about what's going on.

![image](https://github.com/CANBUS-9740/Training-2024/assets/17641355/16638dcd-a4a3-4711-9cca-9b3490d66da9)

For your assigned system, read the instructions regarding how to test it in the simulation.

Note that most functionalities of motor controllers will not be available. You can be sure that the following will work
- `setInverted`
- `set`
- `get`
- `stopMotor`
- `getAlternateEncoder`
  - `getPosition`
  - `setPosition`
  - `getVelocity`


### Testing

Display values from your sensors to the dashboard and view them on the simulation gui networktables window. Run your commands and see how the values change. Make sure the values match the way the command should be affecting them. For this system the data of intereset will be the motor output and encoder position.

You can also open a graphical view of the system from the `NetworkTable` tab under `SmartDashboard`->`Arm-Mechanism`. This will show a graphical representation of the arm.

![image](https://github.com/CANBUS-9740/SimulationTraining-2024-1/assets/17641355/72ab8d84-ba22-4df4-8654-9a95848edc90)

#### Simulation GUI

The simulation GUI which is launched with the simulation to control it and receive feedback provides similar capabilities to shuffleboard, and can thus be used instead of it. However, it works quite differently, so read more about it if you wish to use it:
- [Glass](https://docs.wpilib.org/en/stable/docs/software/dashboards/glass/index.html)
- [Sim GUI](https://docs.wpilib.org/en/stable/docs/software/wpilib-tools/robot-simulation/simulation-gui.html)
- [Sim GUI Widgets](https://docs.wpilib.org/en/stable/docs/software/dashboards/glass/widgets.html)
- [Sim GUI Plots](https://docs.wpilib.org/en/stable/docs/software/dashboards/glass/plots.html)

#### Shuffleboard

To use shuffleboard with the simulation, make sure to install it local first from [here](https://github.com/wpilibsuite/allwpilib/releases/tag/v2024.3.2) (before installing, check if it is installed by searching for `shuffleboard` on the machine). With the shuffleboard installed, lauch it and access the preferences

![image](https://github.com/CANBUS-9740/SimulationTraining-2024-1/assets/17641355/1df6fec7-92f1-4340-85f4-4493dce11fcb)

Enter the `NetworkTables` plugin settings and change `Server` to `localhost`.

![image](https://github.com/CANBUS-9740/SimulationTraining-2024-1/assets/17641355/11856c85-928b-4daa-92cf-d3322f514d61)

After running the simulation, check at the bottom right of the shuffleboard to see if it has connected to the shuffleboard.

![image](https://github.com/CANBUS-9740/SimulationTraining-2024-1/assets/17641355/0af61216-54d3-442e-90d1-4b65c4fdb9ba)



