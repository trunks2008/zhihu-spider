package com.hydra.spider.zhihu.controller;

import com.hydra.spider.zhihu.pipeline.WenxinPipeline;
import com.hydra.spider.zhihu.processor.WenxinProcessor4;
import com.hydra.spider.zhihu.repository.ZhihuRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.selenium.SeleniumDownloader;

/**
 * @author : Hydra
 * @date: 2023/2/6 9:38
 * @version: 1.0
 */
@RestController
@RequestMapping("spider")
@AllArgsConstructor
public class ZhihuController {

    private final ZhihuRepository zhihuRepository;

    @GetMapping("wenxin")
    public void wenxin() {
        new Thread(() -> {
            Request request = new Request("https://www.zhihu.com/question/589929380");
            WenxinProcessor4 wenxinProcessor = new WenxinProcessor4();
            Spider.create(wenxinProcessor)
                    .addRequest(request)
                    .addPipeline(new WenxinPipeline(zhihuRepository))
                    .setDownloader(new SeleniumDownloader("D:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe")
                            .setSleepTime(1000))
                    .run();
        }).start();
    }

    @GetMapping("wenxin2")
    public void wenxin2() {
        new Thread(() -> {
            Spider.create(new WenxinProcessor4())
                    .addUrl(
                            "https://www.zhihu.com/question/589938328",
                            "https://www.zhihu.com/question/589954079",
                            "https://www.zhihu.com/question/589700247",
                            "https://www.zhihu.com/question/585777863"
//                            "https://www.zhihu.com/question/589941496",
//                            "https://www.zhihu.com/question/589904230"
                    )
                    .addPipeline(new WenxinPipeline(zhihuRepository))
                    .setDownloader(new SeleniumDownloader("D:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe")
                            .setSleepTime(1000))
                    .run();
        }).start();
    }

}
