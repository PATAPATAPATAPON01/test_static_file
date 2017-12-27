package com.example.demo.concurrent_learn;

import com.sun.media.sound.SoftTuning;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by PataPon on 2017/12/17.
 */
public class CountTask extends RecursiveTask<Integer> {

    private int THRESHOLD = 2;

    private int start;
    private int end;


    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = end - start <= THRESHOLD;
        if (canCompute) {

            for (int i = start; i <= end; i++) {

                sum += i;

            }
        } else {  //进行任务拆分

            int middle = (start + end) / 2;

            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle + 1, end);
            leftTask.fork();
            rightTask.fork(); //执行子任务 继续分割或者执行

            Integer leftResult = leftTask.join();
            Integer rightResult = rightTask.join();
            sum += leftResult + rightResult;
        }
        return null;
    }

    public static void main(String[] args)   {
        ForkJoinPool forkJoinPool = new ForkJoinPool(3);
        ForkJoinTask<Integer> task = forkJoinPool.submit(new CountTask(1, 100));
//        System.in.read();

        try {
            task.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println(task.isCompletedAbnormally());
            System.out.println(task.getException());
            e.printStackTrace();
        }
//        while (!task.isDone()){
//
//        }
        if (task.isCompletedAbnormally()){
            System.out.println("task exception happens---> "+task.getException());
        }
    }

}
