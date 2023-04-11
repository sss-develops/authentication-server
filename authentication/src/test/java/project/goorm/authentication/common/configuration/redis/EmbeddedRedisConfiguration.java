package project.goorm.authentication.common.configuration.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.StringUtils;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Configuration
@ActiveProfiles("test")
public class EmbeddedRedisConfiguration {

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.host}")
    private String host;

    private RedisServer redisServer;

    @Primary
    @Bean("redisConnectionFactoryTest")
    public RedisConnectionFactory redisConnectionFactoryTest() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    @Primary
    @Bean(name = "sessionsRedisTemplateTest")
    public RedisTemplate<String, Object> objectRedisTemplateTest() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactoryTest());
        return redisTemplate;
    }

    @Primary
    @Bean(name = "sessionRedisTemplateTest")
    public StringRedisTemplate stringRedisTemplateTest(){
        StringRedisTemplate stringRedisTemplate=new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(redisConnectionFactoryTest());
        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
        stringRedisTemplate.setValueSerializer(new StringRedisSerializer());
        return stringRedisTemplate;
    }

    @Primary
    @Bean(name = "loginCountRedisTemplateTest")
    public RedisTemplate<String, Long> loginCountRedisTemplateTest() {
        RedisTemplate<String, Long> redisTemplate = new RedisTemplate<>();
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactoryTest());
        return redisTemplate;
    }


    @PostConstruct
    public void redisServer() throws IOException {
        redisServer = new RedisServer(isRunning() ? findAvailablePort() : port);
        redisServer.start();
    }

    @PreDestroy
    public void stopRedis() {
        if (redisServer != null) {
            redisServer.stop();
        }
    }

    private boolean isRunning() throws IOException {
        return isRunning(executeGrepProcessCommand(port));
    }

    public int findAvailablePort() throws IOException {
        for (int port = 10000; port <= 65535; port++) {
            Process process = executeGrepProcessCommand(port);
            if (!isRunning(process)) {
                return port;
            }
        }
        throw new IllegalArgumentException("Not Found Available port: 10000 ~ 65535");
    }

    private Process executeGrepProcessCommand(int port) throws IOException {
        String command = String.format("netstat -nat | grep LISTEN|grep %d", port);
        String[] shell = {"/bin/sh", "-c", command};
        return Runtime.getRuntime().exec(shell);
    }

    private boolean isRunning(Process process) {
        String line;
        StringBuilder pidInfo = new StringBuilder();

        try (BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()))) {

            while ((line = input.readLine()) != null) {
                pidInfo.append(line);
            }

        } catch (Exception e) {

        }

        return StringUtils.hasText(pidInfo.toString());
    }
}
