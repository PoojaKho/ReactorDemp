package com.example.reactordemo.channels;

import java.util.UUID;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

public interface KafkaTopicsChannel {

    @Output("dummyTopicOutput")
    MessageChannel dummyTopicOutput();

    @Input("dummyTopicInput")
    KStream<UUID,Message> inputChannel();
}
