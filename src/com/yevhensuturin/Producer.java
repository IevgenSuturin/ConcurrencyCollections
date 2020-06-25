package com.yevhensuturin;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

class Producer implements Runnable{
    private final BlockingQueue<String> queue;
    private final String subject;

    public Producer(BlockingQueue<String> queue, String subject) {
        this.queue = queue;
        this.subject = subject;
    }

    @Override
    public void run() {
        try{
            for(String str: subject.split(" ")){
                queue.put(str);
                System.out.println(Thread.currentThread().getName() + " put to queue  " + str);
            }
        }catch (InterruptedException e){
            System.out.println(Thread.currentThread().getName()+ " was interrupted!");
        }
    }
}


class DelayProducer implements Runnable{
    private final BlockingQueue<MyDelayed> queue;
    private final String subject;
    private final Random random = new Random();

    public DelayProducer(BlockingQueue<MyDelayed> queue, String subject) {
        this.queue = queue;
        this.subject = subject;
    }

    @Override
    public void run() {
        try{
            for(String str: subject.split(" ")){
                queue.put(new MyDelayed(str));
                System.out.println(Thread.currentThread().getName() + " put to queue  " + str);
            }
        }catch (InterruptedException e){
            System.out.println(Thread.currentThread().getName()+ " was interrupted!");
        }
    }
}


class PriorityProducer implements Runnable{
    private final BlockingQueue<MyPriorityEntry<String>> queue;
    private final String subject;

    public PriorityProducer(BlockingQueue<MyPriorityEntry<String>> queue, String subject) {
        this.queue = queue;
        this.subject = subject;
    }

    @Override
    public void run() {
        try{
            for(String str: subject.split(" ")){
                queue.put(new MyPriorityEntry<>(str));
                System.out.println(Thread.currentThread().getName() + " put to queue  " + str);
            }
        }catch (InterruptedException e){
            System.out.println(Thread.currentThread().getName()+ " was interrupted!");
        }
    }
}
