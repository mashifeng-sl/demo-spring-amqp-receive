package cn.sailing3d.demospringamqpreceive;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import cn.sailing3d.demospringamqpreceive.dto.CreatePost;

@Component
public class Receive {

    // @RabbitListener(queues = "myqueue")
    // @SendTo("myqueue")
    // public String receiveMessage(CreatePost createPost) {
    // System.out.println("Received1 <" + createPost.toString() + ">");
    // // throw new Error("error");
    // return "老子收到了";
    // }

    // @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "myqueue")))
    @RabbitListener(queues = "myqueue")
    @SendTo("myqueue")
    public String receiveMessage(String message) {
        System.out.println("Received2 <" + message + ">");
        return "老子收到了";

    }

}