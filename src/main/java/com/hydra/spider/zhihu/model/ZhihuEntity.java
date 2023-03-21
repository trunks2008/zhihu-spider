package com.hydra.spider.zhihu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zxp.esclientrhl.annotation.ESID;
import org.zxp.esclientrhl.annotation.ESMapping;
import org.zxp.esclientrhl.annotation.ESMetaData;
import org.zxp.esclientrhl.enums.Analyzer;
import org.zxp.esclientrhl.enums.DataType;

/**
 * @author : Hydra
 * @date: 2023/2/6 10:07
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ESMetaData(indexName = "wenxin-doc-index", number_of_shards = 3,
        number_of_replicas = 2, printLog = true)
public class ZhihuEntity {

    @ESID
    private String baseId;

    @ESMapping(datatype = DataType.text_type,
            analyzer = Analyzer.ik_max_word, search_analyzer = Analyzer.ik_smart)
    private String title;

    @ESMapping(datatype = DataType.text_type,
            analyzer = Analyzer.ik_max_word, search_analyzer = Analyzer.ik_smart)
    private String question;

    @ESMapping(datatype = DataType.text_type,
            analyzer = Analyzer.ik_max_word, search_analyzer = Analyzer.ik_smart)
    private String answer;

}
