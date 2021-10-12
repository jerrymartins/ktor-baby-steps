package jr.com.br.kafka

import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.consumer.KafkaConsumer
import java.time.Duration
import java.util.Properties

fun createConsumer(): KafkaConsumer<String, String> {
    val props = Properties()
    props.setProperty("bootstrap.servers", "127.0.0.1:9092")
    props.setProperty("group.id", "test")
    props.setProperty("enable.auto.commit", "true")
    props.setProperty("auto.commit.interval.ms", "1000")
    props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    return KafkaConsumer(props)
}

fun KafkaConsumer<String, String>.consumeMessages(topic: String) {
    subscribe(listOf(topic))
    while (true) {
        val messages: ConsumerRecords<String, String> = poll(Duration.ofMillis(1000))
        if (!messages.isEmpty) {
            for (message in messages) {
                println("Consumer reading message - offset: ${message.offset()} ${message.value()}")
            }
        }
        commitAsync()
    }
}