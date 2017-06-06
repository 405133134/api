package cn.enn.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.http.Http;
import org.nutz.http.Response;
import org.nutz.json.Json;
import org.nutz.lang.Times;

public class OpentsdbService {
	
	public static void putData(){
		Map<String,Object> tags=new HashMap<>();
		tags.put("deviceId", 10);
		tags.put("gatewayId", 8);
		tags.put("deviceTypeId", 1);
		tags.put("deviceNo", "1234wifi");
		
		Map<String,Object> dataMap=new HashMap<>();
		dataMap.put("metric", "P");
		dataMap.put("timestamp", Times.now().getTime()/1000);
		dataMap.put("value", "12.3");
		dataMap.put("tags", tags);
		
		Response response = Http.post3("http://192.168.1.113:4242/api/put", Json.toJson(dataMap), null,6000);
		
		System.out.println(response.getContent());
		
	}
	
	public static void queryData(){
		List<Map<String,Object>> queries=new ArrayList<>();
		
		Map<String,Object> map=new HashMap<>();
		map.put("aggregator", "avg");
		map.put("metric", "P");
		
		Map<String,Object> tags=new HashMap<>();
		tags.put("type", "literal_or");
		tags.put("tagk", "deviceId");
		tags.put("filter", "10");
		tags.put("groupBy", true);
		
		List<Map<String,Object>> filters=new ArrayList<>();
		
		filters.add(tags);
		
		map.put("filters", filters);
		
		queries.add(map);
		
		Map<String,Object> dataMap=new HashMap<>();
		dataMap.put("start", "1490586530");
		dataMap.put("queries", queries);
		
		Response resp=Http.post3("http://192.168.1.113:4242/api/query", Json.toJson(dataMap), null,6000);
		
		System.out.println(resp.getContent());
	}
	
	public static void main(String[] args) {
		OpentsdbService.queryData();
	}

}
