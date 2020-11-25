//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: (P10 SJF Scheduler)
// Files: (WaitingQueueADT.java, CustomProcess.java, CustomProcessQueue.java,
// ProcessScheduler.java, ProcessScheudlerTests.java)
//
// Course: (CS300, Fall Semester, 2018)
//
// Author: (Dante Pizzini)
// Email: (pizzini@wisc.edu)
// Lecturer's Name: (Gary Dahl)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (Joseph Cambio)
// Partner Email: (jcambio@wisc.edu)
// Partner Lecturer's Name: (Gary Dahl)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _X_ Write-up states that pair programming is allowed for this assignment.
// _X_ We have both read and understand the course Pair Programming Policy.
// _X_ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: (none)
// Online Sources: (none)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////


import java.util.Scanner;

/**
 * This class represents our scheduler for this program. The scheduler enqueues process commands via
 * a min-heap array implementation ensuring the root node will always contain the smallest burst
 * custom process. The scheduler also dequeues the stored custom processes in ascending order of
 * smallest burst time to largest burst time.
 * 
 * @author Dante Pizzini, Joseph Cambio
 *
 */
public class ProcessScheduler {
  private int currentTime; // stores the current time after the last run
  private int numProcessesRun; // stores the number of processes run so far
  private CustomProcessQueue queue; // this processing unit's custom process queue

  public static String[] subCommands; // string array to break apart the user input
                                      // read by the Scanner
  public static String command; // String variable to ensure the while loop in the main method
                                // does not exit the while loop early
  public static String inputCommand;// String which will take the index[0] of subCommands
  public static String secondaryCommand;// String which will take the index[1] of subCommands if a
                                        // string exists in that index

  /**
   * No argument constructor for the class, initializes an empty CustomProcessQueue
   */
  public ProcessScheduler() {
    queue = new CustomProcessQueue();
    currentTime = 0; // time intitially 0
    numProcessesRun = 0; // numProcessesRun intitially 0


  }


  /**
   * Void method which adds a CustomProcess to the current schedule
   * 
   * @param process the CustomProcess argument to be added to the schedule
   */
  public void scheduleProcess(CustomProcess process) {
    queue.enqueue(process); // call to the CustomProcessQueue enqueue method

  }


  /**
   * This method takes a queue of CustomProcess and "runs the the CustomProcesses the messages may
   * be different for different size queues
   * 
   * 
   * @return
   */
  public String run() {
    String log = ""; // Initializes the variable which contains the log of strings
    if (queue.size() == 0) { // For the case of a queue with zero CustomProcesses


      log += "Starting " + queue.size() + " processes\n\n"; // messages for starting the processes
      log += "\nTime " + currentTime + " : All scheduled processes completed.\n"; // message for
                                                                                  // ending the
                                                                                  // processes

    } else if (queue.size() == 1) { // case where the queue size is 1
      int time; // int the stores the burst time of the of the CustomProcess
      CustomProcess process; // initializes the custom process

      log += "Starting " + queue.size() + " process\n\n"; // string that describes the log
      process = queue.dequeue(); // process that describes the process just dequeued
      time = process.getBurstTime(); // burst time of the Process
      log += "Time " + currentTime + " : Process ID " + process.getProcessId() + " Starting.\n";
      currentTime += time; // message that is added to the log when the CustomProcess starts
      log += "Time " + currentTime + " : Process ID " + process.getProcessId() + " Completed.\n";
      numProcessesRun++; // the int that counts the number of process run
      log += "\nTime " + currentTime + " : All scheduled processes completed.\n";

    }

    else if (queue.size() > 1) { // case where the queue size is larger than one
      CustomProcess process; // Creates the process
      int time; // int that keeps track of burst time

      log += "Starting " + queue.size() + " processes\n\n"; // stores the strings
      while (!queue.isEmpty()) { // loop that runs until the queue is empty
        process = queue.dequeue(); // int that takes a process that is just dequeued
        time = process.getBurstTime();
        log += "Time " + currentTime + " : Process ID " + process.getProcessId() + " Starting.\n";
        currentTime += time; // adds the current time of of the process run
        log += "Time " + currentTime + " : Process ID " + process.getProcessId() + " Completed.\n";
        numProcessesRun++; // keeps track of the number of processes run
      }
      log += "\nTime " + currentTime + " : All scheduled processes completed.\n";

    }
    return log; // returns the log
  }

  /**
   * The main driver of the program is implemented in the main method of the ProcessScheduler class
   * 
   * @param args
   */
  public static void main(String[] args) {
    command = ""; // initialize our command to be empty
    secondaryCommand = ""; // initialize our secondary command to be empty

    Scanner scnr = new Scanner(System.in); // create an instance of a scanner
    ProcessScheduler schedule = new ProcessScheduler(); // upon program startup, a new empty
                                                        // schedule is initiated

    // The string header of the program as provided by the assignment write up
    System.out.println("==========   Welcome to the SJF Process Scheduler App   ========\n");

    while (!command.equalsIgnoreCase("quit")) { // While statement to cycle until the program user
                                                // wishes to quit
      System.out.println("Enter Command:");
      System.out.println("[schedule <burstTime>] or [s <burstTime>]");
      System.out.println("[run] or [r]");
      System.out.println("[quit] or [q]"); // Lines 142 to 145 represent the user input choices for
                                           // the program, a user can add to the schedule, run the
                                           // current schedule, or quit the program

      String newCommand = scnr.nextLine(); // Reads the next line of user input
      subCommands = newCommand.split(" "); // splits the user input from the scanner into a String[]
                                           // array

      inputCommand = subCommands[0]; // sets the inputCommand to represent the user's choice of
                                     // schedule, run, or quit
      if (subCommands.length == 2) { // if the user wishes to add a schedule, the subCommands array
                                     // must be length 2
        secondaryCommand = subCommands[1]; // sets the secondaryCommand to represent the subCommmand
                                           // at index 1
      }
      if (subCommands.length > 2) { // This indicates the user enter more than necessary in the
                                    // input menu
        System.out.println("\nWARNING: Please enter a valid command!"); // Warning to the user that
                                                                        // their input can only be a
                                                                        // single command possibly
                                                                        // followed by a burstTime
      }

      switch (inputCommand.toLowerCase()) {// Beginning of a switch statement to handle user input
                                           // accordingly

        case "s": // The user input "s" and wishes to add a schedule

          if (subCommands.length > 2) { // The user correctly input the primary command, but there
                                        // is an error in the burstTime entry
            break;
          }

          if (secondaryCommand != "") { // This is to ensure the user did not simply input "s"
                                        // without a burstTIme

            try {// Initiated try catch block for exception handling

              if (Integer.parseInt(secondaryCommand) > 0) { // The user has input a valid burstTime,
                                                            // this schedule can be added

                int burstTime = Integer.parseInt(secondaryCommand); // set the int burstTime of the
                                                                    // schedule to be added
                CustomProcess process = new CustomProcess(burstTime); // creates a new CustomProcess
                                                                      // with desired burstTime
                schedule.scheduleProcess(process); // call to the scheduleProcess method
                System.out.println("Process ID " + process.getProcessId() // String output to the
                                                                          // terminal to indicate
                                                                          // the process was
                                                                          // successfully added
                    + " scheduled. Burst Time = " + process.getBurstTime() + "\n");
              } else {
                System.out.println("\nWARNING: burst time MUST be greater than 0!\n"); // In the
                                                                                       // case the
                                                                                       // if
                                                                                       // statement
                                                                                       // above does
                                                                                       // not
                                                                                       // execute,
                                                                                       // the
                                                                                       // desired
                                                                                       // processTime
                                                                                       // is less
                                                                                       // than 0
                                                                                       // which is
                                                                                       // an invalid
                                                                                       // command
              }
            } catch (Exception e) { // This exception is thrown from the Interger.parseInt method
              System.out.println("\nWARNING: burst time MUST be an integer!\n"); // message to the
                                                                                 // terminal to
                                                                                 // inform the
                                                                                 // program user
                                                                                 // their burstTime
                                                                                 // entry was an
                                                                                 // invalid command
            }
          } else {
            System.out.println("\nWARNING: please enter a burst time to schedule\n"); // message to
                                                                                      // the user to
                                                                                      // indicate
                                                                                      // that only
                                                                                      // inputing s
                                                                                      // in the
                                                                                      // terminal
                                                                                      // input is
                                                                                      // not a valid
                                                                                      // command
          }
          break;

        case "schedule": // The user input "schedule" and wishes to add a schedule

          if (subCommands.length > 2) {// The user correctly input the primary command, but there
            // is an error in the burstTime entry
            break;
          }

          if (secondaryCommand != "") {// This is to ensure the user did not simply input "schedule"
                                       // without a burstTIme

            try {// Initiated try catch block for exception handling

              if (Integer.parseInt(secondaryCommand) > 0) {// The user has input a valid burstTime,
                                                           // this schedule can be added

                int burstTime = Integer.parseInt(secondaryCommand);// set the int burstTime of the
                                                                   // schedule to be added

                CustomProcess process = new CustomProcess(burstTime); // creates a new CustomProcess
                                                                      // with desired burstTime
                schedule.scheduleProcess(process); // call to the scheduleProcess method within this
                                                   // class
                System.out.println("Process ID " + process.getProcessId()
                    + " scheduled. Burst Time = " + process.getBurstTime() + "\n"); // String output
                                                                                    // to the
                                                                                    // terminal to
                                                                                    // indicate the
                                                                                    // process was
                                                                                    // successfully
                                                                                    // added
              } else {
                System.out.println("\nWARNING: burst time MUST be greater than 0!\n");// In the case
                                                                                      // the if
                                                                                      // statement
                                                                                      // above does
                                                                                      // not
                                                                                      // execute,
                                                                                      // the desired
                                                                                      // processTime
                                                                                      // is less
                                                                                      // than 0
                                                                                      // which is
                                                                                      // an invalid
                                                                                      // command
              }
            } catch (Exception e) {
              System.out.println("\nWARNING: burst time MUST be and integer!\n");// message to
                                                                                 // the user to
                                                                                 // indicate
                                                                                 // that only
                                                                                 // inputing s
                                                                                 // in the
                                                                                 // terminal
                                                                                 // input is
                                                                                 // not a valid
                                                                                 // command
            }
          }
          break;

        case "r": // the user input "r" and wishes to run the current schedule of processes
          System.out.println(schedule.run()); // call to the ProcessScheduler run method
          break;

        case "run": // the user input "run" and wishes to run the current schedule of processes
          System.out.println(schedule.run()); // call to the ProcessScheduler run method
          break;

        case "q": // the user input "q" and wishes to terminate the program
          command = "quit"; // sets command to be "quit" so the while loop will exit upon the next
                            // iteration
          break;

        case "quit": // the user input "quit" and wishes to terminate the program
          command = "quit"; // sets command to be "quit" so the while loop will exit upon the next
                            // iteration
          break;

        default: // all other input cases
          System.out.println("\nWARNING: please enter a valid command\n");// warning to the user to
                                                                          // input a valid command
                                                                          // in the user menu
      }
    }
    System.out.println(
        schedule.numProcessesRun + " processes run in " + schedule.currentTime + " units of time!");
    System.out.println("Thank you for using our scheduler!");
    System.out.println("Goodbye!");// Final messages to the user, indicates the processes run and
                                   // the time taken

    scnr.close(); // close the Scanner instance just before program termination

  }
}
