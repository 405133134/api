package cn.enn.api.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.utils.CloseableUtils;

public class ZookeeperService {
	
	
	public CuratorFramework connect(){
		
		RetryPolicy policy=new RetryNTimes(300, 5);
		
		CuratorFramework client=CuratorFrameworkFactory.builder().connectString("192.168.1.113:2181")
		.sessionTimeoutMs(30000).connectionTimeoutMs(30000).namespace("se-api")
		.retryPolicy(policy).build();
		
		client.start();
		
		return client;
	}
	
	public static void main(String[] args) throws Exception {
		ZookeeperService service=new ZookeeperService();
		
		List<LeaderSelectorClient> leaderSelectorlist=new ArrayList<>();
		List<CuratorFramework> curatorList=new ArrayList<>();
		try{
			for(int i=0;i<10;i++){
				CuratorFramework client = service.connect();
				curatorList.add(client);
				
				LeaderSelectorClient leaderSelectorClient = new LeaderSelectorClient(client, "Client #" + i);
				leaderSelectorlist.add(leaderSelectorClient);
				
			}
			
			System.out.println("----------先观察一会选举的结果-----------");  
            Thread.sleep(10000);  
  
            System.out.println("----------关闭前5个客户端，再观察选举的结果-----------");  
            for (int i = 0; i < 5; i++) {  
            	curatorList.get(i).close();  
            }  
            
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			for (LeaderSelectorClient exampleClient : leaderSelectorlist) {  
                CloseableUtils.closeQuietly(exampleClient);  
            }  
            for (CuratorFramework client : curatorList) {  
                CloseableUtils.closeQuietly(client);  
            }  
		}
	}

}
