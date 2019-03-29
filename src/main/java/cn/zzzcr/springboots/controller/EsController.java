package cn.zzzcr.springboots.controller;

import cn.zzzcr.springboots.domain.EsUser;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

@RestController
@RequestMapping("/es")
public class EsController {

    @Autowired
    ElasticsearchTemplate esTemplate;


    @GetMapping("createIndex")
    public Object createIndex(@Param("indexName") String indexName){

        boolean index = esTemplate.createIndex(indexName);

        System.out.println(index);
        return index;
    }

    @GetMapping("createTable")
    public Object createDoc(@Param("docName") String docName){

        boolean b = esTemplate.putMapping(EsUser.class);

        System.out.println(b);
        return b;
    }

    @PostMapping("insertData")
    public Object insertData(@RequestBody EsUser user){

        IndexQuery build = new IndexQueryBuilder().withId(user.getId().toString())
                .withIndexName("myes").withType("myDoc").withObject(user).build();

        String index = esTemplate.index(build);

        System.out.println(index);
        return index;
    }

    @PostMapping("updataData")
    public Object updataData(@RequestBody EsUser user){


        IndexRequest indexRequest = new IndexRequest();

        //新版本的不能用对象传入，必须先转成map
        Map map = JSONObject.parseObject(JSON.toJSON(user).toString(), Map.class);

        indexRequest.source(map);

        UpdateQuery build = new UpdateQueryBuilder().withId(user.getId().toString())
                .withIndexName("myes").withType("myDoc")
                .withClass(EsUser.class)
                .withIndexRequest(indexRequest).build();

        UpdateResponse update = esTemplate.update(build);

        System.out.println(update);
        return update;
    }

    @GetMapping("queryData")
    public Object queryData(@Param("name") String name){


        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchQuery("name",name)).build();

        List<EsUser> esUsers = esTemplate.queryForList(searchQuery, EsUser.class);

        System.out.println(esUsers);
        return esUsers;

    }

}
