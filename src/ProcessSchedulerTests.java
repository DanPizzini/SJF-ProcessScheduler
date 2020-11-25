//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: (P10 SJF PROCESS SCHEDULER)
// Files: (WaitingQueueADT.java, CustomProcess.java,
// CustomProcessQueue.java, ProcessSchedulerTets.java,
// ProcessScheduler.java)
// Course: (300, Fall, 2018-2019)
//
// Author: (Joe Cambio)
// Email: (Jcambio@wisc.edu)
// Lecturer's Name: (Gary)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (Dante Pizzini)
// Partner Email: (Pizzini@wisc.edu)
// Partner Lecturer's Name: (Gary)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _X__ Write-up states that pair programming is allowed for this assignment.
// _X__ We have both read and understand the course Pair Programming Policy.
// _X__ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: (N/A)
// Online Sources: (N/A)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * This class runs test to check and see if the other classes used in this assignment are correct
 * 
 * @author Joseph Cambio, Dante Pizzini
 *
 */
public class ProcessSchedulerTests {

  /**
   * Tests the enqueue methods for customProssesQueue mainly size(), peek(), and enqueue()
   * 
   * @return
   */
  public static boolean testEnqueueCustomProcessQueue() {
    CustomProcess test1 = new CustomProcess(10); // creating new CustomProcess() with a specific run
                                                 // time
    CustomProcess test2 = new CustomProcess(15);
    CustomProcess test3 = new CustomProcess(20);
    CustomProcess test4 = new CustomProcess(15);

    CustomProcessQueue testQueue = new CustomProcessQueue(); // creates a queue to store them in

    testQueue.enqueue(test2);// enqueues the CustomProcesses
    testQueue.enqueue(test3);
    testQueue.enqueue(test1);
    testQueue.enqueue(test4);

    if (testQueue.size() != 4) { // Checks to see if the size of the queue is expected
      System.out.println("size expected to be 3, but Queue size is currently" + testQueue);
      return false; // if its not the expected size this method returns false
    }
    if (testQueue.peek() != test1) { // Checks to see if the top element in the correctly sorted
                                     // queue is expected
      System.out.println("expected root node to be have burst time of 10, but returned "
          + testQueue.peek().getBurstTime());
      return false; // if not as expected return false
    } else {
      return true;// checks the correctness of the enqueue
    }
  }// operation implemented in the CustomProcessQueue class


  /**
   * This method checks the methods related to dequeue()
   * 
   * @return
   */
  public static boolean testDequeueCustomProcessQueue() {
    CustomProcess test1 = new CustomProcess(10);// creates 3 customProcess() with different run
                                                // times
    CustomProcess test2 = new CustomProcess(15);
    CustomProcess test3 = new CustomProcess(20);
    CustomProcessQueue testQueue = new CustomProcessQueue(); // creates a queue for the
                                                             // CustomProcesses

    testQueue.enqueue(test2); // Enqueues the custom processes
    testQueue.enqueue(test3);
    testQueue.enqueue(test1);
    testQueue.dequeue(); // dequeues one custom process

    if (testQueue.peek() != test2) { // peek() used on the queue after dequeue should return the
                                     // next lowest run time custom Process
      System.out.println("Error: Dequeue failed after one iteration");
    }
    testQueue.dequeue(); // dequeues another CustomProcess in the queue
    if (testQueue.peek() != test3) { // checks to see if the final process remaining is the one
                                     // expected
      System.out.println("Error: Dequeue failed after two iterations");
    }

    testQueue.dequeue(); // dequeue's the last CustomProcess in the testQueue

    if (testQueue.peek() == null) { // verifies that the queue is empty
      return true; // returns true if queue is empty
    }
    System.out.println(
        "Error: Dequeue failure, examine methods in CustomProcessQueue Dequeue and minHeapPercolateDown");
    return false; // if queue is not empty returns false
  } // checks the correctness of the dequeue
  // operation implemented in the CustomProcessQueue class

  /**
   * this method tests the campareTo() method in CustomProcess class
   * 
   * @return
   */
  public static boolean testCustomProcessCompareTo() {
    CustomProcess test1 = new CustomProcess(15); // creates two CustomProcesses
    CustomProcess test2 = new CustomProcess(16);

    if (test1.compareTo(test2) == -1) // Verifies that the result returned is the one expeceted
      // ie test1 is faster than test2
      return true; // returns true if test is correct
    return false; // returns false if test is incorrect
  }


  /**
   * This method tests the constructor of the ProcessScheduler Class
   * 
   * @return
   */
  public static boolean testProcessScheduler() {
    CustomProcess test1 = new CustomProcess(10); // creates a CustomProcess


    ProcessScheduler tSchedule = new ProcessScheduler(); // Creates a ProcessScheduler
    tSchedule.scheduleProcess(test1); // schedules test1



    if (tSchedule != null) // Makes sure something is scheduled in tSchedule
      return true; //

    return false;
  }

  /**
   * This method runs all of the test methods and outputs a message to the console if they failed or
   * passed
   * 
   * @param args
   */
  public static void main(String[] args) {
    if (!testEnqueueCustomProcessQueue()) {
      System.out.println("Error in test method for CustomProcessQueue enqueue method");
    } else if (!testDequeueCustomProcessQueue()) {
      System.out.println("Error in test method for CustomProcessQueue dequeue method");
    } else if (!testCustomProcessCompareTo()) {
      System.out.println("Error in test method for CustomProcess compareTo method");
    } else if (!testProcessScheduler())
      System.out.println("Error in test method for ProcessScheduler");
    else {
      System.out.println("All Test Methods Passed, Congratualations! You got this bread!");
    }
  }
}
