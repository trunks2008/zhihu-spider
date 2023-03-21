package com.hydra.spider.zhihu.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author : Hydra
 * @date: 2023/3/20 13:17
 * @version: 1.0
 */
public class WenxinProcessor1 implements PageProcessor {
    private Site site = Site.me()
            .setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        String title = page.getHtml()
                .xpath("//h1[@class='QuestionHeader-title']/text()").toString();
        String question= page.getHtml()
                .xpath("//div[@class='QuestionRichText']//tidyText()").toString();

        System.out.println(title);
        System.out.println(question);
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new WenxinProcessor1())
                .addUrl("https://www.zhihu.com/question/589929380")
                .thread(2)
                .run();
    }
}
