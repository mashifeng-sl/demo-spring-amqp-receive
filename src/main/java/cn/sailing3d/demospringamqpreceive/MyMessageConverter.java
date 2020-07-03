package cn.sailing3d.demospringamqpreceive;

import java.io.Console;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

import cn.sailing3d.demospringamqpreceive.dto.CreatePost;

public class MyMessageConverter implements MessageConverter {

    private final ObjectMapper objectMapper;

    MyMessageConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Message toMessage(Object object, MessageProperties messageProperties) throws MessageConversionException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        String typeId = message.getMessageProperties().getHeader("__TypeId__").toString();
        Object result;
        if (typeId.indexOf(CreatePost.class.getSimpleName()) > -1) {
            try {
                result = this.objectMapper.readValue(message.getBody(), CreatePost.class);
            } catch (Exception e) {
                result = new String(message.getBody());
                e.printStackTrace();
            }
        } else {
            result = new String(message.getBody());
        }
        return result;

    }

}