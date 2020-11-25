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
import java.util.Arrays;

/**
 * This class handles the assortment of the CustomProcess objects in a queue, it utilizes min-heap
 * array assortment when enqueueing and dequeues by ascending order of smallest burstTime to
 * largest. This class impelements the WaitingQueueADT and must override the methods it inherits
 * accordingly.
 * 
 * @author Dante Pizzini, Joseph Cambio
 *
 */
public class CustomProcessQueue implements WaitingQueueADT<CustomProcess> {

  private static final int INITIAL_CAPACITY = 20; // the initial capacity of the heap
  private CustomProcess[] heap; // array-based min heap storing the data. This is an oversize array
  private int size; // number of CustomProcesses present in this CustomProcessQueue

  /**
   * The no argument constructor for the CustomProcessQueue
   */
  public CustomProcessQueue() {
    this.size = 0; // initial size is 0 or empty
    heap = new CustomProcess[INITIAL_CAPACITY]; // sets the CustomProcess array size to an initial
                                                // capacity of 20
    for (int i = 0; i < INITIAL_CAPACITY; i++) {// ensures all indexes in the queue are null upon
                                                // initialization
      heap[i] = null;
    }
  }

  /**
   * This method adds a CustomProcess to the queue
   * 
   * @param newObject the desired CustomProcess object to be added to the Queue
   */
  @Override
  public void enqueue(CustomProcess newObject) {
    if (size > heap.length) { // checks to see if the initial capacity of the heap has been exceeded
      heap = Arrays.copyOf(heap, heap.length * 2); // copies the current contents of the array
      heap[++size] = newObject; // adds the new CustomProcess to the heap
      minHeapPercolateUp(size); // call to the minHeapPercolateUp helper method to ensure the min
                                // heap implementation of enqueue
      return;
    } else { // initial capacity of the heap has not been exceeded yet
      heap[++size] = newObject; // adds the new CustomProcess to the heap
      minHeapPercolateUp(size); // call to the minHeapPercolateUp helper method to ensure the min
                                // heap implementatino of enqueue
    }
  }

  /**
   * The dequeue method removes the root node from the heap and replaces it with the last
   * CustomProcess in the heap, the program then bubbles down the heap with help from a recursive
   * method to ensure dequeue is sorting the heap by ascending burst times
   */
  @Override
  public CustomProcess dequeue() {
    if (isEmpty() == true) { // if the queue is empty then the dequeue method return null
      return null;
    } else {
      CustomProcess root = heap[1]; // placeholder the root of the heap
      heap[1] = heap[size]; // sets the root of the heap to be the last element in the heap
      heap[size] = null; // sets the location the CustomProcess moved from to null
      --size; // decrements the size by 1 since the root node has been removed
      minHeapPercolateDown(1); // call to the recursive helper minHeapPercolateDown starting from
                               // the new root node
      return root; // returns the original root node
    }
  }

  /**
   * This method peeks into the current Queue and returns the root node if one exists
   */
  @Override
  public CustomProcess peek() {
    if (isEmpty() == true) { // if the queue is empty than peek returns null
      return null;
    }
    return heap[1]; // return the root node in order to "peek" into the current queue
  }

  /**
   * Getter method to get the current size of the queue
   */
  @Override
  public int size() {
    return this.size; // returns current queue size
  }

  /**
   * This method parses through the current heap to see if all indexes are null
   */
  @Override
  public boolean isEmpty() {
    for (int i = 1; i < heap.length; i++) {// for loop to check each reference in the current heap
      if (heap[i] != null) { // There exists a CustomProcess in the heap, the heap is not empty
        return false;
      }
    }
    return true; // returns true if all the indexes in the heap contain null references
  }

  /**
   * Recursive helper method to ensure the enqueue method is sorting the heap in a min-heap based
   * order
   * 
   * @param index the CustomProcess you wish to compare to its parent nodes if it has any
   */
  private void minHeapPercolateUp(int index) {
    if (index == 1) { // the recursive method has bubbled to the root node
      return;
    }
    if (heap[index].compareTo(heap[index / 2]) == -1) { // compares the current CustomProcess at
                                                        // index to its parent at index/2, if the
                                                        // burstTime is smaller for the
                                                        // CustomProcess at index it is swapped with
                                                        // its parent
      CustomProcess swap1 = heap[index]; // place holder for CustomProcess at index
      CustomProcess swap2 = heap[index / 2]; // place holder for CustomProcess at index/2
      heap[index] = swap2; // swaps the child and parent
      heap[index / 2] = swap1; // swaps the parent and child
      minHeapPercolateUp(index / 2); // recursive call to continue bubbling up the heap
    } else {
      minHeapPercolateUp(index / 2); // recursive call to continue bubbling up the heap
    }
  }

  /**
   * Recursive helper method to ensure the dequeue method is sorting the heap by ascending order
   * from smallest burstTime to largest
   * 
   * @param index the CustomProcess which will bubble down the tree if it has children in the heap
   */
  private void minHeapPercolateDown(int index) {
    if (index == this.size) {// The last element in the heap has been reached and the heap is sorted
      return;
    }
    if (index * 2 > size) { // both children are null
      return;
    } else if (index * 2 == size) { // only left child exists
      if (heap[index].compareTo(heap[index * 2]) == 1) { // compares the parent to its left child,
                                                         // if compare returns 1 the parent is
                                                         // larger than its left child
        CustomProcess swap1 = heap[index]; // place holder for the parent
        CustomProcess swap2 = heap[index * 2]; // place holder for the left child
        heap[index] = swap2; // swaps the parent to the left child's index
        heap[index * 2] = swap1; // swaps the child with the parent's index
        minHeapPercolateDown(index * 2); // call to recursive helper to continue bubbling down the
                                         // heap
      } else {
        minHeapPercolateDown(index * 2); // call to recursive helper to continue bubbling down the
                                         // tree, even if there was no swap initially
      }
    } else { // Both a left and right child exist
      if (heap[index].compareTo(heap[index * 2]) == 1 && index * 2 < size) { // if compareTo
                                                                             // indicates the parent
                                                                             // is larger than its
                                                                             // left child they will
                                                                             // be swapped
        CustomProcess swap1 = heap[index]; // place holder for the parent
        CustomProcess swap2 = heap[index * 2]; // place holder for the left child
        heap[index] = swap2; // swaps the parent and left child
        heap[index * 2] = swap1;// swaps the left child with the parent index
        minHeapPercolateDown(index * 2); // call to recursice helper to continue bubbling down the
                                         // heap
      } else {
        minHeapPercolateDown(index * 2); // else no swap occurred, call to recursive helper to
                                         // continue bubbling down the heap
      }
      if (heap[index].compareTo(heap[index * 2 + 1]) == 1 && index * 2 + 1 < size) { // if compareTo
                                                                                     // indicates
                                                                                     // the parent
                                                                                     // is larger
                                                                                     // than its
                                                                                     // right child
                                                                                     // they will be
                                                                                     // swapped
        CustomProcess swap1 = heap[index]; // place holder for the parent
        CustomProcess swap2 = heap[index * 2 + 1]; // place holder for the right child
        heap[index] = swap2; // swaps the parent with the right child
        heap[index * 2 + 1] = swap1;// swaps the right child with the parent index
        minHeapPercolateDown(index * 2 + 1); // recursive helper call to continue bubbling down the
                                             // heap
      } else {
        minHeapPercolateDown(index * 2 + 1); // no swap occurred, still call to recrusive method
                                             // helper to continue to bubble down the heap
      }

    }


  }
}

