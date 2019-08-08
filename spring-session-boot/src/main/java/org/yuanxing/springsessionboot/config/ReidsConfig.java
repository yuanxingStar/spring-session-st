package org.yuanxing.springsessionboot.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @author yuanxing
 * @create 2019-08-06 13:45
 * @see (1) redis 配置
 */
@Configuration
public class ReidsConfig {


    /**
     * @see (1) Redis lettuce连接工厂 (来忒斯)
     */
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        LettuceConnectionFactory lcf = new LettuceConnectionFactory();
        lcf.setHostName("127.0.0.1");
        lcf.setPort(6379);
        lcf.setPassword("yuanxing");
        return lcf;
    }

    /**
     *
     * @param redisCF
     * @return
     * @see RedisTemplate Bean;
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(
            RedisConnectionFactory redisCF) {

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        template.setConnectionFactory(redisCF);
        //key序列化方式
        template.setKeySerializer(redisSerializer);
        //value序列化
        template.setValueSerializer(jackson2JsonRedisSerializer);
        //value hashmap序列化
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        return template;
    }





    /**
     *
     * @return
     * @see （1）Redis 缓存管理器bean
     */
    @Bean
    public RedisCacheManager cacheManager( RedisConnectionFactory redisCF) {
        // 设置缓存有效期一小时
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(1));
        return RedisCacheManager
                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisCF))
                .cacheDefaults(redisCacheConfiguration).build();

    }



    /**
     *
     * @return
     * @see （1）Redis jedis连接工厂Bean
     */
    //@Bean
    public JedisConnectionFactory redisConnectionFactory1() {
        JedisConnectionFactory jcf = new JedisConnectionFactory();
        jcf.setHostName("127.0.0.1");
        jcf.setPort(6379);
        jcf.setPassword("yuanxing");
        return jcf;
    }


    public static void main(String[] args) {
        LettuceConnectionFactory lcf = new LettuceConnectionFactory();
        lcf.setHostName("192.168.1.218");
        lcf.setPort(30569);
        lcf.setPassword("ljs123456");
        System.out.println(lcf);
    }

}
