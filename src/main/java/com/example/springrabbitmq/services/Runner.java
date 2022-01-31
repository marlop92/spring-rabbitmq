package com.example.springrabbitmq.services;

import com.example.springrabbitmq.config.RabbitMqConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class Runner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    public Runner(RabbitTemplate rabbitTemplate, Receiver receiver) {
        this.rabbitTemplate = rabbitTemplate;
        this.receiver = receiver;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending messages..");
        rabbitTemplate.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE_NAME, "foo.bar.baz", "Hello :)");
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }
}
