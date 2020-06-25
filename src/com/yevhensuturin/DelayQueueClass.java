package com.yevhensuturin;

// !!!!!!!!!!!!!!!!!! DelayQueue !!!!!!!!!!!!!!!!!!!!!!
// An unbounded blocking queue of Delayed elements, in which an element can only be taken when its delay has expired.
// The head of the queue is that Delayed element whose delay expired furthest in the past.
// If no delay has expired there is no head and poll will return null.
// Expiration occurs when an element's getDelay(TimeUnit.NANOSECONDS) method returns a value less than or equal to zero.
// Even though unexpired elements cannot be removed using take or poll, they are otherwise treated as normal elements.
// For example, the size method returns the count of both expired and unexpired elements. This queue does not permit null elements.
//
//        This class and its iterator implement all of the optional methods of the Collection and Iterator interfaces.
//        The Iterator provided in method iterator() is not guaranteed to traverse the elements of the DelayQueue in any particular order.
//
//        This class is a member of the Java Collections Framework.
//
//        extends AbstractQueue<E> implements BlockingQueue<E>
//
//Parameters:
//<E>	   	the type of elements held in this collection
//        Since:  1.5

import java.util.Random;
import java.util.concurrent.*;

class MyDelayed implements Delayed{
    private final Random random = new Random();
    private final Integer delay;
    private final String value;

    public MyDelayed(String value) {
        this.value = value;
        this.delay = random.nextInt(1000);
    }

    public String getValue() {
        return value;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return delay;
    }

    @Override
    public int compareTo(Delayed o) {
        return (int)(o.getDelay(TimeUnit.MILLISECONDS) - this.delay);
    }
}

public class DelayQueueClass {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        BlockingQueue<MyDelayed> queue = new DelayQueue<MyDelayed>();

        service.submit(new DelayProducer(queue, "This is my first own expiriens of using Blocking QUEUE interface "+BlockingQueueInterface.FINISH_SYMBOL));
        service.submit(new DelayConsumer(queue));
        service.submit(new DelayConsumer(queue));
        service.shutdown();

    }
}
