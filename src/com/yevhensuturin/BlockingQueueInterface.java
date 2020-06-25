package com.yevhensuturin;

import java.util.concurrent.*;

//
//public abstract interface BlockingQueue<E>
//  Comment  	  Returned-by  	  Constructor-argument  	  Method-argument  	  Field-type  	  Type-bound  	  Links
//        A java.util.Queue that additionally supports operations that wait for the queue to become non-empty when retrieving an element, and wait for space to become available in the queue when storing an element.
//
//        BlockingQueue methods come in four forms, with different ways of handling operations that cannot be satisfied immediately, but may be satisfied at some point in the future: one throws an exception, the second returns a special value (either null or false, depending on the operation), the third blocks the current thread indefinitely until the operation can succeed, and the fourth blocks for only a given maximum time limit before giving up. These methods are summarized in the following table:
//        Summary of BlockingQueue methods
//        Throws exception	Special value	Blocks	Times out
//        Insert	add(e)	offer(e)	put(e)	offer(e, time, unit)
//        Remove	remove()	poll()	take()	poll(time, unit)
//        Examine	element()	peek()	not applicable	not applicable
//        A BlockingQueue does not accept null elements. Implementations throw NullPointerException on attempts to add, put or offer a null. A null is used as a sentinel value to indicate failure of poll operations.
//
//        A BlockingQueue may be capacity bounded. At any given time it may have a remainingCapacity beyond which no additional elements can be put without blocking. A BlockingQueue without any intrinsic capacity constraints always reports a remaining capacity of Integer.MAX_VALUE.
//
//        BlockingQueue implementations are designed to be used primarily for producer-consumer queues, but additionally support the java.util.Collection interface. So, for example, it is possible to remove an arbitrary element from a queue using remove(x). However, such operations are in general not performed very efficiently, and are intended for only occasional use, such as when a queued message is cancelled.
//
//        BlockingQueue implementations are thread-safe. All queuing methods achieve their effects atomically using internal locks or other forms of concurrency control. However, the bulk Collection operations addAll, containsAll, retainAll and removeAll are not necessarily performed atomically unless specified otherwise in an implementation. So it is possible, for example, for addAll(c) to fail (throwing an exception) after adding only some of the elements in c.
//
//        A BlockingQueue does not intrinsically support any kind of "close" or "shutdown" operation to indicate that no more items will be added. The needs and usage of such features tend to be implementation-dependent. For example, a common tactic is for producers to insert special end-of-stream or poison objects, that are interpreted accordingly when taken by consumers.
//
//        Usage example, based on a typical producer-consumer scenario. Note that a BlockingQueue can safely be used with multiple producers and multiple consumers.
//
//
// !!!!!!!!!!!!!!!!!!!!! ArrayBlockingQueue!!!!!!!!!!!!!!!!!!!!!!!!!!!
//
//A bounded blocking queue backed by an array. This queue orders elements FIFO (first-in-first-out).
// The head of the queue is that element that has been on the queue the longest time.
// The tail of the queue is that element that has been on the queue the shortest time.
// New elements are inserted at the tail of the queue, and the queue retrieval operations obtain elements at the head of the queue.
//
//        This is a classic "bounded buffer", in which a fixed-sized array holds elements inserted by producers and extracted by consumers.
//        Once created, the capacity cannot be changed. Attempts to put an element into a full queue will result in the operation blocking;
//        attempts to take an element from an empty queue will similarly block.
//
//        This class supports an optional fairness policy for ordering waiting producer and consumer threads.
//        By default, this ordering is not guaranteed. However, a queue constructed with fairness set to true grants threads access in FIFO order.
//        Fairness generally decreases throughput but reduces variability and avoids starvation.
//
//        This class and its iterator implement all of the optional methods of the Collection and Iterator interfaces.
//
//        This class is a member of the Java Collections Framework.
//
//        extends AbstractQueue<E> implements BlockingQueue<E>, Serializable
//
//        Parameters:
//<E>	   	the type of elements held in this collection
//        Since:  1.5


// !!!!!!!!!!!!!!!!!!!!! LinkedBlockingQueue!!!!!!!!!!!!!!!!!!!!!!!!!!!

//An optionally-bounded blocking queue based on linked nodes.
// This queue orders elements FIFO (first-in-first-out).
// The head of the queue is that element that has been on the queue the longest time.
// The tail of the queue is that element that has been on the queue the shortest time.
// New elements are inserted at the tail of the queue, and the queue retrieval operations obtain elements at the head of the queue.
// Linked queues typically have higher throughput than array-based queues but less predictable performance in most concurrent applications.
//
//        The optional capacity bound constructor argument serves as a way to prevent excessive queue expansion.
//        The capacity, if unspecified, is equal to Integer.MAX_VALUE.
//        Linked nodes are dynamically created upon each insertion unless this would bring the queue above capacity.
//
//        This class and its iterator implement all of the optional methods of the Collection and Iterator interfaces.
//
//        This class is a member of the Java Collections Framework.
//
//        extends AbstractQueue<E> implements BlockingQueue<E>, Serializable
//
//        Parameters:
//<E>	   	the type of elements held in this collection
//        Since:  1.5



// An unbounded TransferQueue based on linked nodes. This queue orders elements FIFO (first-in-first-out) with respect to any given producer.
// The head of the queue is that element that has been on the queue the longest time for some producer.
// The tail of the queue is that element that has been on the queue the shortest time for some producer.
//
//        Beware that, unlike in most collections, the size method is NOT a constant-time operation.
//        Because of the asynchronous nature of these queues, determining the current number of elements requires a traversal of the elements, and so may report inaccurate results if this collection is modified during traversal. Additionally, the bulk operations addAll, removeAll, retainAll, containsAll, equals, and toArray are not guaranteed to be performed atomically. For example, an iterator operating concurrently with an addAll operation might view only some of the added elements.
//
//        This class and its iterator implement all of the optional methods of the Collection and Iterator interfaces.
//
//        Memory consistency effects: As with other concurrent collections, actions in a thread prior to placing an object into a LinkedTransferQueue happen-before actions subsequent to the access or removal of that element from the LinkedTransferQueue in another thread.
//
//        This class is a member of the Java Collections Framework.
//
//        extends AbstractQueue<E> implements TransferQueue<E>, Serializable
//
//        Parameters:
//<E>	   	the type of elements held in this collection
//        Since:  1.7

public class BlockingQueueInterface {
    public static String FINISH_SYMBOL = "__FINISH__";

    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(10);
        //BlockingQueue<String> queue = new ArrayBlockingQueue<String>(100);
        BlockingQueue<String> queue = new LinkedBlockingDeque<>(100);

        service.submit(new Consumer(queue));
        service.submit(new Producer(queue, "This is my first own expiriens of using Blocking QUEUE interface "+FINISH_SYMBOL));
        service.submit(new Consumer(queue));
        service.submit(new Consumer(queue));
        service.shutdown();
    }
}
