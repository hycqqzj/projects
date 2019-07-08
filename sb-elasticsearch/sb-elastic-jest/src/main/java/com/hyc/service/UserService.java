package com.hyc.service;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.hyc.entity.User;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.*;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class UserService {
    public static final String INDEX_NAME = "index_user";
    public static final String TYPE = "user";

    @Autowired
    private JestClient jestClient;

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

            CreateIndex.Builder builder = new CreateIndex.Builder(INDEX_NAME);
            builder.settings(settings);
            builder.mappings(mappingStr);

            JestResult jestResult = jestClient.execute(builder.build());
            if (jestResult != null && jestResult.isSucceeded()) {
                result = true;
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return result;
    }

    public boolean deleteIndex() {
        boolean result = false;
        try {
            DeleteIndex.Builder builder = new DeleteIndex.Builder(INDEX_NAME);
            JestResult jestResult = jestClient.execute(builder.build());
            if (jestResult != null && jestResult.isSucceeded()) {
                result = true;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }

    public boolean deleteDoc() {
        boolean result = false;
        try {
            Delete.Builder builder = new Delete.Builder("3").index(INDEX_NAME).type(TYPE);
            JestResult jestResult = jestClient.execute(builder.build());
            if (jestResult != null && jestResult.isSucceeded()) {
                result = true;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }

    public boolean insertOrUpdateDoc() {
        User user = User.builder().id(1).name("张老五").address("杭州市余杭区西湖大道").age(30)
                .birthDate(DateUtil.parseDate("1996-06-22")).hobbies(Arrays.asList("睡觉","玩游戏","看电视")).build();
        String userStr = JSON.toJSONString(user);
        //是否插入成功标识
        boolean flag = false;
        Index.Builder builder = new Index.Builder(userStr);
        builder.refresh(true);
        Index indexDoc = builder.index(INDEX_NAME).type(TYPE).id(user.getId().toString()).build();
        JestResult result;
        try {
            result = jestClient.execute(indexDoc);
            if (result != null && result.isSucceeded())
                flag = true;
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return flag;
    }

    public Boolean batchInsertDoc() {
        boolean flag = false;

        User user1 = User.builder().id(2).name("王小明").address("南京市雨花台区翠岭银河").age(25)
                .birthDate(DateUtil.parseDate("1991-06-22")).hobbies(Arrays.asList("编程","看电影","KTV")).build();

        User user2 = User.builder().id(3).name("赵雷").address("南京市江宁区天元西路").age(25)
                .birthDate(DateUtil.parseDate("1991-06-22")).hobbies(Arrays.asList("跳舞","羽毛球","足球")).build();
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        try {
            Bulk.Builder bulkBuilder = new Bulk.Builder();
            //循环构造批量数据
            for (User user : userList) {
                String userStr = JSON.toJSONString(user);
                Index indexDoc = new Index.Builder(userStr).index(INDEX_NAME).type(TYPE).id(user.getId().toString()).build();
                bulkBuilder.addAction(indexDoc);
            }
            BulkResult br = jestClient.execute(bulkBuilder.build());
            if (br != null && br.isSucceeded()) {
                flag = true;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return flag;
    }

    public List<User> findByName(String name){
        List<User> userList = new ArrayList<>();

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("name",name));

        Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex(INDEX_NAME).addType(TYPE).build();
        try {
            JestResult result = jestClient.execute(search);
            List<String> userStrList = result.getSourceAsStringList();
            for (String userStr : userStrList) {
                User user = JSON.parseObject(userStr,User.class);
                userList.add(user);
            }
            return userList;
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return null;
    }


}
