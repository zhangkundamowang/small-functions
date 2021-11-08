package com.zk.mybatisplus.common.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2017/3/4.
 * Time: 下午 10:14.
 * Explain:Redis连接池
 */
public final class RedisPool {
	final static Logger logger = LoggerFactory.getLogger(RedisPool.class);

    //Redis服务器IP
    private static String ADDR = CsvUtil.getSysProperty("redis.ADDR");
    //Redis的端口号
    private static Integer PORT = Integer.valueOf(CsvUtil.getSysProperty("redis.PORT"));
    //访问密码
    private static String AUTH = CsvUtil.getSysProperty("redis.AUTH");

    //可用连接实例的最大数目，默认为8；
    //如果赋值为-1，则表示不限制，如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)
    private static Integer MAX_TOTAL = -1;
    //控制一个pool最多有多少个状态为idle(空闲)的jedis实例，默认值是8
    private static Integer MAX_IDLE = 10;
    //等待可用连接的最大时间，单位是毫秒，默认值为-1，表示永不超时。
    //如果超过等待时间，则直接抛出JedisConnectionException
    //private static Integer MAX_WAIT_MILLIS = 10000;
    private static Integer MAX_WAIT_MILLIS = 5000*1;
    private static Integer TIMEOUT = 5000;//最多5秒
    //在borrow(用)一个jedis实例时，是否提前进行validate(验证)操作；
    //如果为true，则得到的jedis实例均是可用的
    private static Boolean TEST_ON_BORROW = true;
    private  static JedisPool jedisPool = null;

    /**
     * 静态块，初始化Redis连接池
     */
    /**
     * 初始化Redis连接池
     */
    private static void initializePool() {
        try {
            logger.info("ADDR:"+ADDR+";PORT:"+PORT+";AUTH"+AUTH);
            JedisPoolConfig config = new JedisPoolConfig();
        /*注意：
            在高版本的jedis jar包，比如本版本2.9.0，JedisPoolConfig没有setMaxActive和setMaxWait属性了
            这是因为高版本中官方废弃了此方法，用以下两个属性替换。
            maxActive  ==>  maxTotal
            maxWait==>  maxWaitMillis
         */
            /*app.cache.remote.caches.redis.read.minIdle=2
app.cache.remote.caches.redis.read.maxIdle=10
app.cache.remote.caches.redis.read.host=10.201.1.222
app.cache.remote.caches.redis.read.port=6379
app.cache.remote.caches.redis.read.password=redis
app.cache.remote.caches.redis.read.timeout=600000
app.cache.remote.caches.redis.read.maxWaitMillis=5000*/
          //设置最大连接数（100个足够用了，没必要设置太大）
            config.setMinIdle(2);
    		config.setMaxTotal(MAX_TOTAL);
    		//最大空闲连接数  原因，运行环境配置较差，redis连接数过高所致，降低redis.maxIdle和redis.maxActive后，问题解决。没有再出现。
    		config.setMaxIdle(MAX_IDLE);
    		//获取Jedis连接的最大等待时间（50秒） 
    		config.setMaxWaitMillis(MAX_WAIT_MILLIS);
    		//在获取Jedis连接时，自动检验连接是否可用
    		config.setTestOnBorrow(TEST_ON_BORROW);
    		//在将连接放回池中前，自动检验连接是否有效
    		config.setTestOnReturn(true);
    		//自动测试池中的空闲连接是否都是可用连接
    		config.setTestWhileIdle(true);
            if(StringUtils.isBlank(AUTH))
            {//密码为空就不认真
                jedisPool = new JedisPool(config,ADDR,PORT);
            }   
            else
            {
            	jedisPool = new JedisPool(config,ADDR,PORT,TIMEOUT,AUTH);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * 多线程环境同步初始化（保证项目中有且仅有一个连接池）
     */
    private static synchronized void poolInit() {
        if (null == jedisPool) {
            initializePool();
        }
    }

    
    
    public static Jedis getJedis() {
    	 if (null == jedisPool) {
             poolInit();
         }
        Jedis jedis = null;
        int timeoutCount = 0;
        int retryNum=3;
        while (jedis == null && timeoutCount < retryNum) {
            try {
                jedis = jedisPool.getResource();
            } catch (Exception e) {
            	if (e instanceof JedisConnectionException) {
                    timeoutCount++;
                    logger.info("getJedis timeoutCount={}"+timeoutCount);
                    if (timeoutCount > 3) {
                        break;
                    }
                } else {
                    logger.info("jedisInfo ... NumActive=" + jedisPool.getNumActive()
                            + ", NumIdle=" + jedisPool.getNumIdle()
                            + ", NumWaiters=" + jedisPool.getNumWaiters()
                            + ", isClosed=" + jedisPool.isClosed());
                    logger.info("GetJedis error,"+e);
                    break;
                }
            }
           /* finally {//returnResource(jedis);
                if (jedis != null) 
                    jedis.close(); //注意这里不是关闭连接，在JedisPool模式下，Jedis会被归还给资源池。
            }*/
            timeoutCount++;
        }
        return jedis;
    }
    public static void closeJedisPool(Jedis jedis) {
    	 if (jedis != null) 
    	 {
    		 //logger.info("关闭redis");
    		 //Jedis 3.0 以后
    		 //jedis.close(); //注意这里不是关闭连接，在JedisPool模式下，Jedis会被归还给资源池。 		 
    		 //Jedis 3.0 以前
    		 /*logger.info("关闭前连接信息 jedisInfo ... NumActive=" + jedisPool.getNumActive()
                     + ", NumIdle=" + jedisPool.getNumIdle()
                     + ", NumWaiters=" + jedisPool.getNumWaiters()
                     + ", isClosed=" + jedisPool.isClosed());*/
    		 returnResource(jedis);
    	 }
    }
    public static void returnResource(Jedis jedis){
        //方法参数被声明为final，表示它是只读的。
        if(jedis!=null){
            jedisPool.returnResource(jedis);
        }
    }
    /**
     * 获取Jedis实例
     * @return
     * 暂时不用
     */
    public synchronized static Jedis getJedis_old(){
    	Jedis jedis = null;
        try {
            if(jedisPool != null){
                jedis = jedisPool.getResource();
                return jedis;
            }else{
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            //logger.error("Get jedis error : "+e);
            return null;
        }
        finally{
        	returnResource(jedis);
        	}
    }
    public static void getJedisInfo() {
    	logger.info("jedisInfo ... NumActive=" + jedisPool.getNumActive()
                + ", NumIdle=" + jedisPool.getNumIdle()
                + ", NumWaiters=" + jedisPool.getNumWaiters()
                + ", isClosed=" + jedisPool.isClosed());
    }
    /**
     * 获取Jedis实例
     * 暂时不用了
     */
    public static Jedis getJedis1() {
        if (null == jedisPool) {
            poolInit();
        }
 
        int timeoutCount = 0;
        while (true) {
            try {
                if (null != jedisPool) {
                    return jedisPool.getResource();
                }
            } catch (Exception e) {
                if (e instanceof JedisConnectionException) {
                    timeoutCount++;
                    logger.info("getJedis timeoutCount={}"+timeoutCount);
                    if (timeoutCount > 3) {
                        break;
                    }
                } else {
                    logger.info("jedisInfo ... NumActive=" + jedisPool.getNumActive()
                            + ", NumIdle=" + jedisPool.getNumIdle()
                            + ", NumWaiters=" + jedisPool.getNumWaiters()
                            + ", isClosed=" + jedisPool.isClosed());
                    logger.info("GetJedis error,"+e);
                    break;
                }
            }
            break;
        }
        return null;
    }
}
