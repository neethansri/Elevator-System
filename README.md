# Sysc3303_Group1

author Solan Siva 101067491
author Ben Bagg 101122318
author Vijay Ramalingom 101073072
author Mohammad Issa 101065045
author Neethan Sriranganathan 101082581


Elevator.java - The Elevator class used to get events from floor through the scheduler class and send back current Elevator status

ElevatorDirection.java - ENUM class used for the direction of the Elevator class

ElevatorMessage.java - A class made to hold the inputs from the text file

ElevatorSystem.java - A class used to create the threads and run them

ElevtorTest.java - JUnit for testing the Elevator, Floor and Scheduler class

ElevatorReceiver.java - A class used to receive events from the scheduler and updates elevator list

ElevatorState.java - Enum class for states of the elevator class

ElevatorUpdate.java - A message class used by the elevator subsystem to send updates about its location and direction to the scheduler

ElevatorSubsystem.java - A class used to create the elevator threads and run the threads

ElevatorReceiver.java - A receiver class used to receive from scheduler and sends ack to scheduler
 
Floor.java -  The Floor class used to read in inputs from a file and send it to the Elevator class through the Scheduler class and receive Elevator status from Elevator class

FloorSubsystem.java - A class used to create the Floor threads and run the threads

FloorReceiver.java - A receiver class used to Receive from scheduler and sends ack to scheduler

Scheduler.java - The Scheduler class used for communication channel between Elevator and Floor. Keeps track of all previous and current request from Floor and Elevator.

SchedulerReciever.java -  Thread in the Scheduler subsystem that recieves updates from the elevators and notifies the scheduler

SchedulerState.java - Enum used to represent scheduler states

SchedulerSubsystem.java - A class used to create the Scheduler threads and run the threads

SchedulerReceiver.java - A receiver class used to receive from scheduler and sends ack to scheduler

Door.java - ChInit Door class to start door thread

DoorTimer.java - Creates a timer if the door is in the correct position

Fault.java - enum class for fault states

Motor.java -  Init motor class to start motor thread

MotorTimer.java - If it doesnt reach fo



-----------------------------------------------------------------------------------------------------------
SET UP INSTRUCTIONS

- Run SchedulerSubsystem, ElevatorSubsystem, and FloorSubsystem

if Classpath does not match local eclipse setup, change .classpath in the project folder to follow local eclipse settings
