package com.hyc.service;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class OrderService {
    public static final String INDEX_NAME = "index_order";
    public static final String TYPE = "order";

    @Autowired
    private TransportClient client;

    public boolean createIndex(){
        boolean result = false;

        Map<String,Object> settings = new HashMap<>();
        settings.put("number_of_shards",2);
        settings.put("number_of_replicas",0);

        String mappingStr = new StringBuilder()
                .append("{").append("\"order\": {").append("\"properties\": {")
                .append("\"id\": {\"type\": \"keyword\"},").append("\"orderDate\": {\"type\": \"date\"},")
                .append("\"orderNo\": {\"type\": \"keyword\"},")
                .append("\"address\": {\"type\": \"text\", \"analyzer\": \"ik_max_word\", \"search_analyzer\": \"ik_max_word\"},")
                .append("\"items\": {\"type\": \"text\", \"analyzer\": \"ik_max_word\", \"search_analyzer\": \"ik_max_word\"},")
                .append("\"custId\": {\"type\": \"keyword\"}").append("}}}").toString();

        CreateIndexRequestBuilder builder = client.admin().indices().prepareCreate(INDEX_NAME).setSettings(settings).addMapping(TYPE, mappingStr, XContentType.JSON);
        CreateIndexResponse response = builder.get();
        if(response.isAcknowledged() && response.isShardsAcknowledged()) {
            result = true;
        }

        return result;
    }

    public boolean deleteIndex(){
        boolean result = false;
        try {
            AcknowledgedResponse response = client.admin().indices().prepareDelete(INDEX_NAME).execute().get();
            result = response.isAcknowledged();
        } catch (Exception e) {
            log.error("deleteIndex", e);
        }

        return result;
    }


}
