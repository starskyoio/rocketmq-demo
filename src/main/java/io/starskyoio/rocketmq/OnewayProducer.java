package io.starskyoio.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class OnewayProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("my-group");
        producer.setNamesrvAddr("192.168.142.10:9876");
        producer.start();

        int messageCount = 10;
        for (int i = 0; i < messageCount; i++) {
            Message msg = new Message("WarningMsg",
                    "hardware",
                    ("磁盘占用超过90%"+i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );

            producer.sendOneway(msg);
        }

        Thread.sleep(5000);
        producer.shutdown();
    }
}
