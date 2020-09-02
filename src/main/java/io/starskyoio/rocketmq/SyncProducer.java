package io.starskyoio.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class SyncProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("my-group");
        producer.setNamesrvAddr("192.168.142.10:9876");
        producer.start();

        for (int i = 0; i < 10; i++) {
            Message msg = new Message("WarningMsg",
                    "hardware",
                    ("cpu高负荷运转"+i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            SendResult result = producer.send(msg);
            System.out.printf("%s%n", result);
        }

        producer.shutdown();
    }
}
