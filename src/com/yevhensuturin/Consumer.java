package com.yevhensuturin;

import java.util.concurrent.BlockingQueue;

class Consumer implements Runnable{
    private final BlockingQueue<String> queue;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true){
            try {
                String val = queue.take();
                if (val.equals(BlockingQueueInterface.FINISH_SYMBOL)) {
                    queue.put(BlockingQueueInterface.FINISH_SYMBOL);
                    break;
                }
                System.out.println("\t"+Thread.currentThread().getName() + " prints " + val);
            }catch (InterruptedException ex){
                System.out.println("\t"+Thread.currentThread().getName()+ " was interrupted!");
            }
        }
        System.out.println("\t"+Thread.currentThread().getName()+" finished printing.");
    }
}

class SynchronizedConsumer implements Runnable{
    private final BlockingQueue<String> queue;

    public SynchronizedConsumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true){
            try {
                String val = queue.take();
                if (val.equals(BlockingQueueInterface.FINISH_SYMBOL)) {
                    break;
                }
                System.out.println("\t"+Thread.currentThread().getName() + " prints " + val);
            }catch (InterruptedException ex){
                System.out.println("\t"+Thread.currentThread().getName()+ " was interrupted!");
            }
        }
        System.out.println("\t"+Thread.currentThread().getName()+" finished printing.");
    }
}

class DelayConsumer implements Runnable{
    private final BlockingQueue<MyDelayed> queue;

    public DelayConsumer(BlockingQueue<MyDelayed> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true){
            try {
                MyDelayed val = queue.take();
                if (val.getValue().equals(BlockingQueueInterface.FINISH_SYMBOL)) {
                    queue.put(new MyDelayed(BlockingQueueInterface.FINISH_SYMBOL));
                    break;
                }
                System.out.println("\t"+Thread.currentThread().getName() + " prints " + val);
                Thread.sleep(1000);
            }catch (InterruptedException ex){
                System.out.println("\t"+Thread.currentThread().getName()+ " was interrupted!");
            }
        }
        System.out.println("\t"+Thread.currentThread().getName()+" finished printing.");
    }
}


class PriorityConsumer implements Runnable{
    private final BlockingQueue<MyPriorityEntry<String>> queue;

    public PriorityConsumer(BlockingQueue<MyPriorityEntry<String>> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true){
            try {
                MyPriorityEntry<String> val = queue.take();
                if (val.getEntry().equals(BlockingQueueInterface.FINISH_SYMBOL)) {
                    queue.put(new MyPriorityEntry<>(BlockingQueueInterface.FINISH_SYMBOL));
                    break;
                }
                System.out.println("\t"+Thread.currentThread().getName() + " prints " + val);
                Thread.sleep(1000);
            }catch (InterruptedException ex){
                System.out.println("\t"+Thread.currentThread().getName()+ " was interrupted!");
            }
        }
        System.out.println("\t"+Thread.currentThread().getName()+" finished printing.");
    }
}
