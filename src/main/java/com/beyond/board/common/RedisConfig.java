package com.beyond.board.common;//package com.beyond.board.common;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClusterConnection;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

// Bean이 2개가 필요하다 기본적으로
@Configuration
public class RedisConfig {

    //  yml의 spring.redis.host의 정보를 소스코드의 변수로 가져오는 것 이다.
    @Value("${spring.redis.host}")
    public String host;

    @Value("${spring.redis.port}")
    public int port;

//  RedisConnectionFactory는 Redis서버와의 연결을 설정하는 역할을 한다.
//  LettuceConnectionFactory는 RedisConnectionFactory의 구현체로서 실질적인 역할 수행
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
//      커넥션정보를 여기에 넣는다
//      return new LettuceConnectionFactory(host, port);//유연하지 못하다 이 설정은
        RedisStandaloneConfiguration configuration
                = new RedisStandaloneConfiguration();

        configuration.setHostName(host);
        configuration.setPort(port);
//      configuration.setDatabase(2);
//      configuration.setPassword(1234);

//      이렇게하면 yml이 변경되더라도 다 같이 변경이 될 것 이다.
//      데이터베이스를 선택하고 싶을 수 있다. 비밀번호도 셋팅할 수 있지 않나
        return new LettuceConnectionFactory(configuration);

    }

//    redis 템플릿 정리
//    redis와 상호작용할 때 Redis key,value의 형식을 정의한다
//    빈번하게 사용되니까 반드시 외워라.
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
//        나중에 쓸 개념
//        redisTemplate.opsForValue().set(key,value)
//        redisTemplate.opsForValue().get(key)
//        redisTemplate.opsForValue().increment 또는 decrement

    }

}