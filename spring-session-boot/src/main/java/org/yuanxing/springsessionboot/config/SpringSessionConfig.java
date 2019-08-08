package org.yuanxing.springsessionboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author yuanxing
 * @create 2019-08-06 15:46
 * @see
 *
 *      (1) maxInactiveIntervalInSeconds 默认是1800秒过期，这里测试修改为60秒
 *          RedisFlushMode有两个参数：ON_SAVE（表示在response commit前刷新缓存），IMMEDIATE（表示只要有更新，就刷新缓存）
 *          redisNamespace
 *          cleanupCron
 */

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60, redisNamespace = "yuan:xing")
public class SpringSessionConfig{

}
