package com.aot.pcfems.common.redis;

import com.aot.standard.common.util.AotMessageUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisPool.class);
    private static JedisPool m_jedisPool = null;

    // @Value("#{enviromentProperties['REDIS_IP']}")
    private static final String REDIS_IP = AotMessageUtils.getProperty("REDIS_IP");
    // @Value("#{enviromentProperties['REDIS_PORT']}")
    private static final String REDIS_PORT = AotMessageUtils.getProperty("REDIS_PORT");

    // private static HikariDataSource m_ds = null;
    // dataSourceClassName=com.mysql.jdbc.jdbc2.optional.MysqlDataSource
    public static JedisPool getPool() {
        if (m_jedisPool == null) {

            try {
                // logger.debug("strCacheServer=" + strCacheServer);
                LOGGER.debug("REDIS IP: {}, PORT: {}", REDIS_IP, REDIS_PORT);
                m_jedisPool = new JedisPool(new JedisPoolConfig(), REDIS_IP, Integer.parseInt(REDIS_PORT), 10000);

                LOGGER.info("Redis Pool connect success");
            } catch (final Exception e) {
                LOGGER.error(ExceptionUtils.getStackTrace(e));
                // e.printStackTrace();
            }
        }
        return m_jedisPool;
    }

    public static void closePool() {
        if (m_jedisPool != null) {
            m_jedisPool.destroy();
        }
    }

    // public static void main(final String[] args) {
    // System.out.println("Current Directory=" + new File(".").getAbsolutePath());
    /*
     * try { Connection conn = RedisPool.getPool().getConnection(); Connection conn2 = RedisPool.getPool().getConnection(); try { Thread.sleep(1000 * 1000); } catch (Exception e) {} conn.close();
     * conn2.close(); } catch (Exception e) { e.printStackTrace(); } RedisPool.closePool();
     */
    // }
}
