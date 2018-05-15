package com.jarq.queue;

public class QueueUnderflow extends RuntimeException {

    public QueueUnderflow() {
        super("Queue is empty!");
    }

}
