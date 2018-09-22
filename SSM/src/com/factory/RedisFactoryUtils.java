package com.factory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisFactoryUtils {

	private static JedisPoolConfig poolCfg = new JedisPoolConfig();
	private static JedisPool pool;

	static {
		poolCfg.setMaxIdle(50);
		poolCfg.setMaxTotal(100);
		poolCfg.setMaxWaitMillis(3000);
		pool = new JedisPool(poolCfg, "localhost");
	}

	public static Jedis getJedis() {
		Jedis jedis = pool.getResource();
		jedis.auth("abcd1234");
		return jedis;
	}

}
