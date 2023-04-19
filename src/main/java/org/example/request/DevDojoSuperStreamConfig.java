package org.example.request;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.common.RabbitStreamTemplateSimpleFactory;
import org.example.common.StreamListener;
import org.example.common.StreamListenerContainerSimpleFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.rabbit.stream.config.SuperStream;
import org.springframework.rabbit.stream.listener.StreamListenerContainer;
import org.springframework.rabbit.stream.producer.RabbitStreamTemplate;

@Configuration
@RequiredArgsConstructor
class DevDojoSuperStreamConfig {
    private static final String SUPER_STREAM = "devdojo.super";

    @Bean
    SuperStream devDojoSuperStream() {
        return new SuperStream(SUPER_STREAM, 3);
    }

    @Bean
    RabbitStreamTemplate devDojoRabbitStreamTemplate(RabbitStreamTemplateSimpleFactory factory) {
        return factory.apply(SUPER_STREAM);
    }

    @Bean
    <T> StreamListenerContainer devDojoContainer(StreamListenerContainerSimpleFactory<T> factory,
                                                 StreamListener<T> devDojoSuperStreamConsumer) {
        return factory.apply(SUPER_STREAM, devDojoSuperStreamConsumer);
    }
}