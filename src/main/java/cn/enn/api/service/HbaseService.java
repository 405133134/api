package cn.enn.api.service;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HBaseAdmin;

public class HbaseService {
	private static Configuration conf = null;

    public static void init() throws IOException {
    	System.setProperty("hadoop.home.dir", "D:\\hadoop-common-2.2.0-bin-master");
        conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "192.168.1.113");
        conf.set("hbase.ZooKeeper.property.clientPort","2181");
    }
    
    public static void main(String[] args) throws IOException {
		init();
		HBaseAdmin hAdmin = new HBaseAdmin(conf);
		System.out.println(hAdmin.tableExists("mytest"));
	}

}
