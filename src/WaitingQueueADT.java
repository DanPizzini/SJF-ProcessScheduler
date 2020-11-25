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
 * This Class extends Comparable in order to utilize its array capabilities
 * 
 * @author joseph cambio, Dante Pizzini
 *
 * @param <T>
 */
public interface WaitingQueueADT<T extends Comparable<T>> {

  /*
   * This class will queue an object to the to the end of the list
   */
  public void enqueue(T newObject); // inserts a newObject in the priority queue

  /**
   * This method removes the first object in the list and returns it
   * 
   * @return
   */
  public T dequeue(); // removes and returns the item with the highest priority

  /**
   * This method Checks the first object in the list and returns it
   * 
   * @return
   */
  public T peek(); // returns without removing the item with the highest priority


  /**
   * this method returns the size of the list
   * 
   * @return
   */
  public int size(); // returns size of the waiting queue

  /**
   * This method checks if the list is empty and returns a boolean
   * 
   * @return
   */
  public boolean isEmpty(); // checks if the waiting queue is empty
}
