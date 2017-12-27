package com.example.demo.testPic;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;

import java.util.concurrent.*;

/**
 * Created by PataPon on 2017/12/19.
 */
public class MyThreadPool extends ThreadPoolExecutor {

    public MyThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        System.out.println("执行了beforeExecute");
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        System.out.println("执行了afterExecute");
    }

    @Override
    protected void terminated() {
        System.out.println("线程终结之前");
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThreadPool pool = new MyThreadPool(10, 20, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));

        pool.submit(() -> {
        });
//        pool.submit(() -> {
//        });
//        pool.submit(() -> {
//        });

        FutureTask<String> futureTask = new FutureTask<String>((() -> {
            Thread.sleep(5000l);
            return "s";
        }));

        Future<?> future = pool.submit(futureTask);

        future.cancel(true);

        System.out.println(future.equals(futureTask));
        System.out.println(future.get());
        pool.shutdown();

        System.out.println(pool.getTaskCount());
    }
}
