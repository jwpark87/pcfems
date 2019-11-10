package com.aot.pcfems.common.redis;

import com.eact.web.lib.utils.CommUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class RedisUtils extends CommUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisUtils.class);

    public static final boolean cacheSetup(final String strKey, final String strVal, final String strMode) throws InterruptedException {
        boolean b_ret = false;
        int isContinue = 3;

        resetError();
        do {
            try (Jedis jedis = RedisPool.getPool().getResource()) {
                if (strMode.equals("SET")) {
                    jedis.set(strKey, strVal);
                } else if (strMode.equals("LPUSH")) {
                    jedis.lpush(strKey, strVal);
                } else if (strMode.equals("RPUSH")) {
                    jedis.rpush(strKey, strVal);
                }
                isContinue = 0;
                b_ret = true;
            } catch (final Exception se) {
                // mayby cache server was restarted.
                LOGGER.error(ExceptionUtils.getStackTrace(se));
                TimeUnit.SECONDS.sleep(1); // for retry.
            }
        } while (--isContinue > 0);
        LOGGER.debug("cacheSetting {} key: {}, value: {}", strMode, strKey, strVal);
        return b_ret;
    }

    public final static long cacheSize(final String strKey) throws InterruptedException {
        long nSize = 0;
        int isContinue = 3;
        resetError();
        do {
            try (Jedis jedis = RedisPool.getPool().getResource()) {
                nSize = jedis.llen(strKey);
                isContinue = 0;
            } catch (final Exception e) {
                // mayby cache server was restarted.
                LOGGER.error(ExceptionUtils.getStackTrace(e));
                TimeUnit.SECONDS.sleep(1); // for retry.
            }
        } while (--isContinue > 0);
        // logger.debug("cacheSetting to key: {}, value: {}", strKey, strVal);
        return nSize;
    }

    public final static void enQueue(final String strQueueName, final String strValue) throws InterruptedException {
        if (cacheSetup(strQueueName, strValue, "LPUSH") != true) {
            LOGGER.error("Push to {} error. errMsg: {}", strQueueName, CommUtils.getLastErrMsg());
        }
    }

    // timeout : 0 is infinite.
    public final static String deQueue(final String strQueueName, final String strQueueNameWork, final int timeout) throws InterruptedException {
        String strValue = null;
        int isContinue = 3;

        resetError();
        do {
            try (Jedis jedis = RedisPool.getPool().getResource()) {
                if (strQueueNameWork != null && strQueueNameWork.length() > 0) {
                    strValue = jedis.brpoplpush(strQueueName, strQueueNameWork, timeout);
                } else {
                    final List<String> messages = jedis.brpop(0, strQueueName);
                    strValue = messages.get(1);
                }
                LOGGER.info("Got the message  value: " + strValue);
                isContinue = 0;
            } catch (final Exception se) {
                // mayby cache server was restarted.
                LOGGER.error(ExceptionUtils.getStackTrace(se));
                TimeUnit.SECONDS.sleep(1); // for retry.
            }
        } while (--isContinue > 0);

        return strValue;
    }

    public final static String clearQueue(final String strQueueName) throws InterruptedException {
        final String strValue = null;
        int isContinue = 3;

        resetError();
        do {
            try (Jedis jedis = RedisPool.getPool().getResource()) {

                jedis.del(strQueueName);
                isContinue = 0;
            } catch (final Exception se) {
                // mayby cache server was restarted.
                LOGGER.error(ExceptionUtils.getStackTrace(se));
                TimeUnit.SECONDS.sleep(1); // for retry.
            }
        } while (--isContinue > 0);

        return strValue;
    }

    public final static String moveQueue(final String strQueueFrom, final String strQueueTo) throws InterruptedException {
        String strValue = null;
        int isContinue = 3;
        resetError();
        do {
            try (Jedis jedis = RedisPool.getPool().getResource()) {
                while (true) {
                    strValue = jedis.lpop(strQueueFrom);
                    if (strValue == null) {
                        break;
                    }

                    jedis.rpush(strQueueTo, strValue);
                }
                isContinue = 0;
            } catch (final Exception se) {
                // mayby cache server was restarted.
                LOGGER.error(ExceptionUtils.getStackTrace(se));
                TimeUnit.SECONDS.sleep(1); // for retry.
            }
        } while (--isContinue > 0);

        return strValue;
    }
}
