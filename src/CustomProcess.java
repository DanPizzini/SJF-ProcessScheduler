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


/**
 * This class represents the objects which are to be stored and called upon by the program scheduler
 * 
 * @author Dante Pizzini, Joseph Cambio
 */
public class CustomProcess implements Comparable<CustomProcess> {

  private static int nextProcessId = 1; // stores the id to be assigned to the next process
                                        // to be created
  private final int PROCESS_ID; // unique identifier for this process
  private int burstTime; // time required by this process for CPU execution


  /**
   * The constructor for the CustomProcess class, set the burstTime to the passed in burstTime and
   * increments the nextProcessId by 1
   * 
   * @param burstTime desired burstTime for the CustomProcess to be added
   */
  public CustomProcess(int burstTime) {
    this.burstTime = burstTime; // the "run time" for the process
    PROCESS_ID = nextProcessId; // the processId for the current CustomProcess be initiated
    ++nextProcessId; // increment the nextProcessId by 1
  }

  @Override
  public int compareTo(CustomProcess other) { // Because CustomProcess implements comparable this
                                              // override method is required
    if (this.burstTime - other.burstTime < 0) {// this indicates the current CustomProcess has a
                                               // smaller burstTime than the one it is being
                                               // compared to, returns -1
      return -1;
    } else if (this.burstTime - other.burstTime > 0) { // this indicates the current CustomProcess
                                                       // has a larger burstTime than the one it is
                                                       // being compared to, returns 1
      return 1;
    } else { // The burstTimes are equal for the two customProcesses, their ProcessIds are compared
             // in this case
      if (this.PROCESS_ID < other.getProcessId()) {
        return -1; // indicates the current CustomProcess has a smaller process id
      } else {
        return 1; // indicates the other CustomProcess has a smaller process id
      }
    }

  }



  public int getProcessId() {
    return this.PROCESS_ID;
  }

  public int getBurstTime() {
    return this.burstTime;
  }
}
