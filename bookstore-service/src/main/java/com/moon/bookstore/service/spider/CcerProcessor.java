package com.moon.bookstore.service.spider;

import com.moon.bookstore.api.entity.Ccer;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yujiangtao
 * @date 2021/12/29 下午5:04
 */
public class CcerProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    private static AtomicInteger index = new AtomicInteger(2);

    @Override
    public void process(Page page) {
        List<Selectable> nodes = page.getHtml().xpath("//tr").nodes();
        List<Ccer> ccerList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(nodes) && nodes.size() > 1) {
            for (int i = 1; i < nodes.size(); i++) {
                Selectable node = nodes.get(i);
                String dateStr = node.xpath("//td[1]/text()").get();
                String amountStr = node.xpath("//td[2]/text()").get().replace(",", "");
                String averagePriceStr = node.xpath("//td[3]/text()").get().replace(",", "");
                String tradeFeeStr = node.xpath("//td[4]/text()").get().replace(",", "");
                if (tradeFeeStr.contains("CCER")) {
                    System.out.println(String.format("date: %s, amount: %s, averagePrice: %s, tradeFee: %s",
                            dateStr, amountStr, averagePriceStr, tradeFeeStr));
                    Date date = null;
                    try {
                        date = DateUtils.parseDate(dateStr, "yyyy-MM-dd");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Long amount = Long.valueOf(amountStr);
                    BigDecimal averagePrice = new BigDecimal(averagePriceStr);
                    String pattern = "(\\d+(,\\d{3})*(.\\d+)?)";
                    Pattern compile = Pattern.compile(pattern);
                    Matcher matcher = compile.matcher(tradeFeeStr);
                    String group = "";
                    if (matcher.find()) {
                        group = matcher.group(0);
                    }
                    BigDecimal tradeFee = new BigDecimal(group);

                    if (date != null) {
                        Ccer ccer = new Ccer();
                        ccer.setTradeDate(date);
                        ccer.setExchangeId(1L);     // 北京交易所
                        ccer.setTradeVolume(amount);
                        ccer.setTradeFee(tradeFee);
                        ccer.setAveragePrice(averagePrice);
                        ccerList.add(ccer);
                    }
                }
            }
        }
        page.putField("ccerList", ccerList);
        if (index.intValue() < 99) {
            page.addTargetRequest("https://www.bjets.com.cn/article/jyxx/?" + index.getAndIncrement());
        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}
