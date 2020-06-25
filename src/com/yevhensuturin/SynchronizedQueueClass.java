package com.yevhensuturin;

// !!!!!!!!!!!!!!!!!!!!! SynchronousQueue !!!!!!!!!!!!!!!!!!!
//A blocking queue in which each insert operation must wait for a corresponding remove operation by another thread, and vice versa.
// A synchronous queue does not have any internal capacity, not even a capacity of one.
// You cannot peek at a synchronous queue because an element is only present when you try to remove it;
// you cannot insert an element (using any method) unless another thread is trying to remove it;
// you cannot iterate as there is nothing to iterate.
// The head of the queue is the element that the first queued inserting thread is trying to add to the queue;
// if there is no such queued thread then no element is available for removal and poll() will return null.
//        For purposes of other Collection methods (for example contains), a SynchronousQueue acts as an empty collection. This queue does not permit null elements.
//        Synchronous queues are similar to rendezvous channels used in CSP and Ada. They are well suited for handoff designs, in which an object running in one thread must sync up with an object running in another thread in order to hand it some information, event, or task.
//
//        This class supports an optional fairness policy for ordering waiting producer and consumer threads. By default, this ordering is not guaranteed. However, a queue constructed with fairness set to true grants threads access in FIFO order.
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

import java.util.concurrent.*;

public class SynchronizedQueueClass {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        BlockingQueue<String> queue = new SynchronousQueue<String>();

        service.submit(new Producer(queue, "This is my first own expiriens of using Blocking QUEUE interface "+BlockingQueueInterface.FINISH_SYMBOL));
        service.submit(new SynchronizedConsumer(queue));
        service.shutdown();

    }
}
