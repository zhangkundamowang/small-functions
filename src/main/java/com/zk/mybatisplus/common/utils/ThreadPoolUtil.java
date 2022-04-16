package com.zk.mybatisplus.common.utils;

import java.util.HashMap;
import java.util.concurrent.*;

public class ThreadPoolUtil {

    /**
     * 参数：queue。工作队列。这队列用来保持那些execute()方法提交的还没有执行的任务。常用的队列有SynchronousQueue,LinkedBlockingQueue,ArrayBlockingQueue。一般我们需要根据自己的实际业务需求选择合适的工作队列。
     * SynchronousQueue：直接传递。对于一个好的默认的工作队列选择是SynchronousQueue，该队列传递任务到线程而不持有它们。在这一点上，试图向该队列压入一个任务，如果没有可用的线程立刻运行任务，那么就会入列失败，所以一个新的线程就会被创建。当处理那些内部依赖的任务集合时，这个选择可以避免锁住。直接接传递通常需要无边界的最大线程数来避免新提交任务被拒绝处理。当任务以平均快于被处理的速度提交到线程池时，它依次地确认无边界线程增长的可能性；
     * LinkedBlockingQueue：无界队列。没有预先定义容量的无界队列，在核心线程数都繁忙的时候会使新提交的任务在队列中等待被执行，所以将不会创建更多的线程，因此，最大线程数的值将不起作用。当每个任务之间是相互独立的时比较适合该队列，所以任务之间不能互相影响执行。例如，在一个WEB页面服务器，当平滑的出现短暂的请求爆发时这个类型的队列是非常有用的，当任务以快于平均处理速度被提交时该队列会确认无边界队列增长的可能性。
     * ArrayBlockingQueue：有界阻塞队列，遵循FIFO原则，一旦创建容量不能改变，当向一个已经满了的该队列中添加元素和向一个已经为空的该队列取出元素都会导致阻塞；当线程池使用有限的最大线程数时该队列可以帮助保护资源枯竭，但它更难协调和控制。队列大小和最大线程数在性能上可以互相交换：使用大队列和小线程池会降低CPU使用和OS资源与上下文切换开销，但会导致人为降低吞吐量，如果任务频繁阻塞，系统的线程调度时间会超过我们的允许值；如果使用小队列大池，这将会使CPU较为繁忙但会出现难以接受的调度开销，这也会导致降低吞吐量。
     */
    private static BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(1000);

    //private static BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(100);

    private static final int CORE_POOL_SIZE = 16;

    private static final int MAXIMUM_POOL_SIZE = 32;

    private static final int KEEP_ALIVE_TIME = 2000;

    /**
     * 参数：threadFactory 默认不需要填写（因为默认创建的是非守护线程，线程池的线程需要时非守护的）
     * 它是ThreadFactory类型的变量，用来创建新线程。默认使用Executors.defaultThreadFactory() 来创建线程。使用默认的ThreadFactory来创建线程时，会使新创建的线程具有相同的NORM_PRIORITY优先级并且是非守护线程，同时也设置了线程的名称。
     * <p>
     * 只要当前JVM实例中尚存在任何一个非守护线程没有结束，守护线程就全部工作；只有当最后一个非守护线程结束时，守护线程随着JVM一同结束工作。
     * Daemon的作用是为其他线程的运行提供便利服务，守护线程最典型的应用就是 GC (垃圾回收器)，它就是一个很称职的守护者。
     * User和Daemon两者几乎没有区别，唯一的不同之处就在于虚拟机的离开：如果 User Thread已经全部退出运行了，只剩下Daemon Thread存在了，虚拟机也就退出了。 因为没有了被守护者，Daemon也就没有工作可做了，也就没有继续运行程序的必要了。
     * <p>
     * 下面的threadFactory是创建守护线程
     */
    private static final ThreadFactory threadFactory = new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            //使用守护线程——这种方式不会阻止程序的关停
            t.setDaemon(true);
            return t;
        }
    };

    /**
     * 参数：handler：它是RejectedExecutionHandler类型的变量，表示线程池的饱和策略。如果阻塞队列满了并且没有空闲的线程，这时如果继续提交任务，就需要采取一种策略处理该任务。线程池提供了4种策略：
     * AbortPolicy：直接抛出异常，这是默认策略；
     * CallerRunsPolicy：用调用者所在的线程来执行任务；
     * DiscardOldestPolicy：丢弃阻塞队列中靠最前的任务，并执行当前任务；
     * DiscardPolicy：直接丢弃任务；
     */

    private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE,
            MAXIMUM_POOL_SIZE,
            KEEP_ALIVE_TIME,
            TimeUnit.MILLISECONDS,
            queue,
            new ThreadPoolExecutor.DiscardPolicy());

    static {
        threadPool.prestartAllCoreThreads();
    }

    public static ThreadPoolExecutor getThreadPool() {
        return threadPool;
    }

    /**
     * 获取线程池的详细情况(返回拼接解析好的字符串)
     *
     * @param threadPool 当前线程池实例
     */
    public static String getThreadInfoToString(ThreadPoolExecutor threadPool) {

        HashMap<String, Object> map = getThreadInfoToMap(threadPool);

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("初始线程数:" + map.get("poolSize") +
                "&nbsp;&nbsp;&nbsp;核心数:" + map.get("corePoolSize") +
                "&nbsp;&nbsp;&nbsp;允许最大数:" + map.get("maximumPoolSize") +
                "&nbsp;&nbsp;&nbsp;任务执行中数量:" + map.get("activeCount") +
                "&nbsp;&nbsp;&nbsp;完成数量:" + map.get("completedTaskCount") +
                "&nbsp;&nbsp;&nbsp;总数:" + map.get("taskCount") +
                "&nbsp;&nbsp;&nbsp;队列缓存的任务数量:" + map.get("queueSize") +
                "&nbsp;&nbsp;&nbsp;池中存在的最大线程数:" + map.get("largestPoolSize") +
                "&nbsp;&nbsp;&nbsp;空闲时间:" + map.get("keepAliveTime") +
                "&nbsp;&nbsp;&nbsp;是否关闭:" + map.get("shutdown") +
                "&nbsp;&nbsp;&nbsp;是否终止:" + map.get("terminated"));

        return stringBuffer.toString();
    }

    /**
     * 获取线程池的详细情况
     *
     * @param threadPool 当前线程池实例
     */
    public static HashMap<String, Object> getThreadInfoToMap(ThreadPoolExecutor threadPool) {

        if (threadPool == null) threadPool = getThreadPool();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("poolSize", threadPool.getPoolSize());
        hashMap.put("corePoolSize", threadPool.getCorePoolSize());
        hashMap.put("activeCount", threadPool.getActiveCount());
        hashMap.put("completedTaskCount", threadPool.getCompletedTaskCount());
        hashMap.put("taskCount", threadPool.getTaskCount());
        hashMap.put("queueSize", threadPool.getQueue().size());
        hashMap.put("largestPoolSize", threadPool.getLargestPoolSize());
        hashMap.put("maximumPoolSize", threadPool.getMaximumPoolSize());
        hashMap.put("keepAliveTime", threadPool.getKeepAliveTime(TimeUnit.MILLISECONDS));
        hashMap.put("shutdown", threadPool.isShutdown());
        hashMap.put("terminated", threadPool.isTerminated());

        // 初始线程数、核心线程数、正在执行的任务数量、已完成任务数量、任务总数、队列里缓存的任务数量、池中存在的最大线程数、最大允许的线程数、线程空闲时间、线程池是否关闭、线程池是否终止
        /*log.info(String.format("\r\n初始线程数: %d \r\n核心线程数: %d \r\n正在执行的任务数量: %d \r\n已完成任务数量: %d \r\n任务总数: %d \r\n队列里缓存的任务数量: %d \r\n池中存在的最大线程数: %d \r\n最大允许的线程数: %d \r\n线程空闲时间: %d \r\n线程池是否关闭: %s \r\n线程池是否终止: %s\r\n",
                threadpool.getPoolSize(),
                threadpool.getCorePoolSize(),
                threadpool.getActiveCount(),
                threadpool.getCompletedTaskCount(),
                threadpool.getTaskCount(),
                threadpool.getQueue().size(),
                threadpool.getLargestPoolSize(),
                threadpool.getMaximumPoolSize(),
                threadpool.getKeepAliveTime(TimeUnit.MILLISECONDS),
                threadpool.isShutdown(),
                threadpool.isTerminated()));*/

        return hashMap;
    }
}
