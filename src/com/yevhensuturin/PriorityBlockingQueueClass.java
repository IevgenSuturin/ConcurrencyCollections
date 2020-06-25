package com.yevhensuturin;

// An unbounded blocking queue that uses the same ordering rules as class PriorityQueue and supplies blocking retrieval operations.
// While this queue is logically unbounded, attempted additions may fail due to resource exhaustion (causing OutOfMemoryError).
// This class does not permit null elements. A priority queue relying on natural ordering also does not permit insertion of non-comparable objects (doing so results in ClassCastException).
//
//        This class and its iterator implement all of the optional methods of the Collection and Iterator interfaces. The Iterator provided in method iterator() is not guaranteed to traverse the elements of the PriorityBlockingQueue in any particular order. If you need ordered traversal, consider using Arrays.sort(pq.toArray()). Also, method drainTo can be used to remove some or all elements in priority order and place them in another collection.
//
//        Operations on this class make no guarantees about the ordering of elements with equal priority. If you need to enforce an ordering, you can define custom classes or comparators that use a secondary key to break ties in primary priority values. For example, here is a class that applies first-in-first-out tie-breaking to comparable elements. To use it, you would insert a new FIFOEntry(anEntry) instead of a plain entry object.
//
//This class is a member of the Java Collections Framework.
//
//        extends AbstractQueue<E> implements BlockingQueue<E>, Serializable
//
//        Parameters:
//<E>	   	the type of elements held in this collection
//        Since:  1.5

import java.util.concurrent.*;

class MyPriorityEntry<E extends Comparable<? super E>> implements Comparable<MyPriorityEntry<E>>{
    private final E entry;

    public MyPriorityEntry(E entry) {
        this.entry = entry;
    }

    public E getEntry() {
        return entry;
    }

    @Override
    public int compareTo(MyPriorityEntry<E> o) {
        return this.entry.compareTo(o.getEntry());
    }
}


public class PriorityBlockingQueueClass {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        BlockingQueue<String> queue = new PriorityBlockingQueue<>();

        service.submit(new Producer(queue, "This is my first own expiriens of using Blocking QUEUE interface "+BlockingQueueInterface.FINISH_SYMBOL));
        service.submit(new Consumer(queue));
        service.submit(new Consumer(queue));
        service.shutdown();

    }
}
