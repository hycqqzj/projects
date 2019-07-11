package com.hyc.service;

import com.hyc.dao.PromoterMapper;
import com.hyc.entity.Promoter;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class PromoterService {
    public static final String INDEX_NAME = "index_promoter";
    public static final String TYPE = "promoter";

    @Autowired
    private TransportClient client;

    @Autowired
    private PromoterMapper promoterMapper;

    public void syncElastic() throws IOException {
        int start = 0;
        int pagesize = 2000;
        List<Promoter> promoterList = promoterMapper.listAll(start * pagesize, pagesize);
        while (promoterList != null && !promoterList.isEmpty()) {
            BulkRequestBuilder bulkRequestBuilder = buildBlukRequest(promoterList);
            BulkResponse bulkResponse = bulkRequestBuilder.execute().actionGet();

            if (bulkResponse.hasFailures()) {
                log.error(bulkResponse.buildFailureMessage());
            }
            log.info("第{}页数据同步完成", start);

            start++;
            promoterList = promoterMapper.listAll(start * pagesize, pagesize);
        }

        log.info("同步完成");
    }

    public boolean createPromoterIndex() {
        boolean result = false;

        Map<String, Object> settings = new HashMap<>();
        settings.put("number_of_shards", 2);
        settings.put("number_of_replicas", 0);
        settings.put("index.mapping.total_fields.limit", 10000000);
        try {
            String mappingStr = readJson();
            log.info("从index.json文件中读取到内容：{}", mappingStr);
            CreateIndexRequestBuilder builder = client.admin().indices().prepareCreate(INDEX_NAME).setSettings(settings).addMapping(TYPE, mappingStr, XContentType.JSON);
            CreateIndexResponse response = builder.get();
            if (response.isAcknowledged() && response.isShardsAcknowledged()) {
                result = true;
            }
        } catch (Exception e) {
            log.error("createPromoterIndex异常", e);
        }

        return result;
    }

    public boolean deletePromoterIndex() {
        boolean result = false;
        try {
            AcknowledgedResponse response = client.admin().indices().prepareDelete(INDEX_NAME).execute().get();
            result = response.isAcknowledged();
        } catch (Exception e) {
            log.error("deletePromoterIndex异常", e);
        }

        return result;
    }

    private BulkRequestBuilder buildBlukRequest(List<Promoter> promoterList) throws IOException {
        if(promoterList == null || promoterList.isEmpty()) {
            return null;
        }
        BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();

        for (Promoter promoter : promoterList) {
            IndexRequestBuilder indexBuilder = client.prepareIndex(INDEX_NAME, TYPE).setSource(XContentFactory.jsonBuilder()
                    .startObject()
                    .field("id",promoter.getId())
                    .field("rowno",promoter.getRowNo())
                    .field("code",promoter.getCode())
                    .field("name",promoter.getName())
                    .field("idnumber",promoter.getIdNumber())
                    .field("birthdate",promoter.getBirthDate())
                    .field("sex",promoter.getSex())
                    .field("phone",promoter.getPhone())
                    .field("suppliercode",promoter.getSupplierCode())
                    .field("suppliername",promoter.getSupplierName())
                    .field("storecode",promoter.getStoreCode())
                    .field("storename",promoter.getStoreName())
                    .field("usertype",promoter.getUserType())
                    .field("contractcode",promoter.getContractCode())
                    .field("categorycode",promoter.getCategoryCode())
                    .field("categoryname",promoter.getCategoryName())
                    .field("brandcode",promoter.getBrandCode())
                    .field("brandname",promoter.getBrandName())
                    .field("begindate",promoter.getBeginDate())
                    .field("enddate",promoter.getEndDate())
                    .field("remark",promoter.getRemark())
                    .field("ncpsndoc",promoter.getNcPsndoc())
                    .field("ncpsndocsub",promoter.getNcPsndocSub())
                    .field("ncflag",promoter.getNcFlag())
                    .field("hzwflag",promoter.getHzwFlag())
                    .field("ncupdatetime",promoter.getNcUpdateTime())
                    .field("createtime",promoter.getCreateTime())
                    .field("createuser",promoter.getCreateUser())
                    .field("updatetime",promoter.getUpdateTime())
                    .field("updateuser",promoter.getUpdateUser())
                    .field("delflag",promoter.getDelFlag())
                    .endObject()
            );
            bulkRequestBuilder.add(indexBuilder);
        }

        return bulkRequestBuilder;
    }

    private String readJson() throws Exception {
        StringBuilder content = new StringBuilder("");

        InputStream inputStream = null;
        try {

            inputStream = getClass().getClassLoader().getResourceAsStream("index.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            String line = null;
            while ((line = br.readLine()) != null) {
                content.append(line);
            }
        } finally {
            if (inputStream != null)
                inputStream.close();
        }

        return content.toString();
    }
}
