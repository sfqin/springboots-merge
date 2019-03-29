package cn.zzzcr.springboots.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "myes",type = "myDoc",shards = 3,replicas = 2)
public class EsUser {

    @Id
    private Integer id;

    private String name;

    private String addr;

    private Integer age;
}
