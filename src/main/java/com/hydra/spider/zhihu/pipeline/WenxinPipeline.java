package com.hydra.spider.zhihu.pipeline;

import com.hydra.spider.zhihu.model.ZhihuEntity;
import com.hydra.spider.zhihu.repository.ZhihuRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.util.DigestUtils;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author : Hydra
 * @date: 2023/2/7 14:48
 * @version: 1.0
 */
@Slf4j
@AllArgsConstructor
public class WenxinPipeline implements Pipeline {
    private final ZhihuRepository zhihuRepository;

    @Override
    public void process(ResultItems resultItems, Task task) {
        Map<String, Object> map = resultItems.getAll();
        String title = map.get("title").toString();
        Object questionObj = map.get("question");
        String question = questionObj==null?"":questionObj.toString();
        List<String> answer = (List<String>) map.get("answer");

        ZhihuEntity zhihuEntity;
        for (String an : answer) {
            zhihuEntity = new ZhihuEntity();
            zhihuEntity.setTitle(title);
            zhihuEntity.setQuestion(question);
            zhihuEntity.setAnswer(an);
            try {
                zhihuRepository.save(zhihuEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
