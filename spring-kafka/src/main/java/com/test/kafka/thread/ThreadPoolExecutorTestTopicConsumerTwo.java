package com.test.kafka.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * 初始化线程池
 * 主题：test-topin
 * 具体一个消费者线程
 * @author lsl
 */
public class ThreadPoolExecutorTestTopicConsumerTwo {

    private static volatile ThreadPoolExecutor instance;

    /**
     * 核心线程数
     * 核心线程数保持和每次拉取任务的最大数保持一致
     * 目的：避免有新任务等待，最后消费完毕的任务会拉长总消费时间
     * 类似短板
     */
    private static final int CORE_POOL_SIZE = 50;
    /**
     * 最大线程数
     */
    private static final int MAXIMUM_POOL_SIZE = 100;
    /**
     * 闲置线程存活时间
     */
    private static final Long KEEP_ALIVE_TIME = 600000L;

    /**
     * 排队队列的容量大小
     */
    private static final int LINK_BLOCK_CAPACITY = 50;
    public static ThreadPoolExecutor getInstance() {
        if (null == instance) {
            synchronized (ThreadPoolExecutorTestTopicConsumerTwo.class) {
                if (null == instance) {
                    instance = new ThreadPoolExecutor(
                            CORE_POOL_SIZE,
                            MAXIMUM_POOL_SIZE,
                            KEEP_ALIVE_TIME,
                            TimeUnit.MILLISECONDS,
                            new LinkedBlockingQueue<Runnable>(LINK_BLOCK_CAPACITY),
                            Executors.defaultThreadFactory()
                    );
                }
            }
        }
        return instance;
    }
}
