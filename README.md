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

Floor.java -  The Floor class used to read in inputs from a file and send it to the Elevator class through the Scheduler class and receive Elevator status from Elevator class

Scheduler.java - The Scheduler class used for communication channel between Elevator and Floor. Keeps track of all previous and current request from Floor and Elevator.

-----------------------------------------------------------------------------------------------------------
SET UP INSTRUCTIONS

- Run Elevator System

if Classpath does not match local eclipse setup, change .classpath in the project folder to follow local eclipse settings
