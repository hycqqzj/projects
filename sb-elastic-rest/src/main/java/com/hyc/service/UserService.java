package com.hyc.service;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.hyc.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class UserService {
    public static final String INDEX_NAME = "index_user2";
    public static final String TYPE = "user";

    @Autowired
    private RestHighLevelClient restClient;

    public boolean createIndex() {
        boolean result = false;
        try {
            Map<String,Object> settings = new HashMap<>();
            settings.put("number_of_shards",2);
            settings.put("number_of_replicas",0);

            String mappingStr = new StringBuilder()
                    .append("{").append("\"user\": {").append("\"properties\": {")
                    .append("\"id\": {\"type\": \"keyword\"},").append("\"birthDate\": {\"type\": \"date\"},")
                    .append("\"name\": {\"type\": \"text\", \"analyzer\": \"ik_max_word\", \"search_analyzer\": \"ik_max_word\"},")
                    .append("\"address\": {\"type\": \"text\", \"analyzer\": \"ik_max_word\", \"search_analyzer\": \"ik_max_word\"},")
                    .append("\"hobbies\": {\"type\": \"text\", \"analyzer\": \"ik_max_word\", \"search_analyzer\": \"ik_max_word\"},")
                    .append("\"age\": {\"type\": \"integer\"}").append("}}}").toString();

            CreateIndexRequest request = Requests.createIndexRequest(INDEX_NAME).settings(settings).mapping(TYPE,mappingStr, XContentType.JSON);
            request.timeout("2m");
            request.masterNodeTimeout("1m");

            CreateIndexResponse createIndexResponse = restClient.indices().create(request, RequestOptions.DEFAULT);
            result = createIndexResponse.isAcknowledged();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return result;
    }

    public boolean deleteIndex() {
        boolean result = false;
        try {
            DeleteIndexRequest request = new DeleteIndexRequest(INDEX_NAME);
            request.timeout("2m");
            request.masterNodeTimeout("1m");

            AcknowledgedResponse response = restClient.indices().delete(request, RequestOptions.DEFAULT);

            result = response.isAcknowledged();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }

    public boolean insertDoc() {
        User user = User.builder().id(1).name("张老五").address("杭州市余杭区西湖大道").age(30)
                .birthDate(DateUtil.parseDate("1996-06-22")).hobbies(Arrays.asList("睡觉","玩游戏","看电视")).build();
        String userStr = JSON.toJSONString(user);
        //是否插入成功标识
        boolean flag = false;
        try {
            IndexRequest request = new IndexRequest(INDEX_NAME, TYPE, user.getId().toString()).source(userStr, XContentType.JSON);
            request.timeout("5s");
            request.opType(DocWriteRequest.OpType.CREATE);
            IndexResponse indexResponse = restClient.index(request, RequestOptions.DEFAULT);
            if(indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
                flag = true;
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return flag;
    }

    public boolean deleteDoc() {
        boolean result = false;

        try {
            DeleteRequest request = new DeleteRequest(INDEX_NAME,TYPE,"1");
            request.timeout("2m");

            DeleteResponse response = restClient.delete(request, RequestOptions.DEFAULT);
            if(response.getResult() == DocWriteResponse.Result.DELETED) {
                result = true;
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }

    public List<User> findByName(){
        List<User> userList = new ArrayList<>();

        GetRequest request = new GetRequest(INDEX_NAME);

        return userList;
    }
}
