package com.hydra.spider.zhihu.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.selenium.SeleniumDownloader;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @author : Hydra
 * @date: 2023/3/20 13:17
 * @version: 1.0
 */
public class WenxinProcessor4 implements PageProcessor {
    private Site site = Site.me()
            .setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        page.putField("title", page.getHtml()
                .xpath("//h1[@class='QuestionHeader-title']/text()").toString());
        page.putField("question", page.getHtml()
                .xpath("//div[@class='QuestionRichText']//tidyText()").toString());

        String contentPath= "div[@class='QuestionAnswers-answers']"+
                "//div[@class='RichContent RichContent--unescapable']" +
                "//div[@class='RichContent-inner']"+
                "/tidyText()";
        List<String> answerList = page.getHtml().xpath(contentPath).all();
        page.putField("answer",answerList);
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new WenxinProcessor4())
                .addUrl("https://www.zhihu.com/question/589929380")
                .thread(2)
                .setDownloader(new SeleniumDownloader("F:\\gitProject\\chrome\\chromedriver_win32\\chromedriver.exe")
                        .setSleepTime(1000))
                .run();
    }
}
