package cn.enn.api.configure;

public class ConfigureApi {
	
	public interface KafkaProperties {
        public final static String ZK = "192.168.1.113:2181";
        public final static String GROUP_ID = "test_group1";
        public final static String TOPIC = "test4";
        public final static String BROKER_LIST = "192.168.1.113:9092,192.168.1.113:9093,192.168.1.113:9094";
        public final static int BUFFER_SIZE = 64 * 1024;
        public final static int TIMEOUT = 20000;
        public final static int INTERVAL = 10000;
    }

}
