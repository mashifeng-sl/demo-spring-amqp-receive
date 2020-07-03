package cn.sailing3d.demospringamqpreceive;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.api.RabbitListenerErrorHandler;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.sailing3d.demospringamqpreceive.dto.CreatePost;

@Configuration
@EnableRabbit
public class Appconfig {

    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public CachingConnectionFactory connectionFactory(@Value("${amqp.host.name}") String hostName,
            @Value("${amqp.port}") int port, @Value("${amqp.username}") String username,
            @Value("${amqp.password}") String password) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(hostName, port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            CachingConnectionFactory connectionFactory, ObjectMapper objectMapper) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        // factory.setMessageConverter(new MyMessageConverter(objectMapper));
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrentConsumers(3);
        factory.setMaxConcurrentConsumers(10);
        return factory;
    }

    @Bean
    public RabbitListenerErrorHandler rabbitListenerErrorHandler() {

        return new RabbitListenerErrorHandler() {

            @Override
            public Object handleError(Message amqpMessage, org.springframework.messaging.Message<?> message,
                    ListenerExecutionFailedException exception) throws Exception {
                System.out.println(message);
                return "hhhhhhh";
                // throw exception;
            }
        };

    }

}