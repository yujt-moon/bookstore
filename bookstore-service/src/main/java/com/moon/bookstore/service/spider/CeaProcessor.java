package com.moon.bookstore.service.spider;

import com.moon.bookstore.api.entity.Cea;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yujiangtao
 * @date 2021/12/29 下午5:04
 */
public class CeaProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        List<Selectable> nodes = page.getHtml().xpath("//ul[@class='list-unstyled articel-list']/li[@class='text-ellipsis  hidden-xs']").nodes();
        if(nodes != null && nodes.size() > 0) {
            nodes.forEach(node -> {
                String detailHref = node.xpath("//a/@href").get();
                Request request = new Request(detailHref);
                page.addTargetRequest(request);
            });
        } else {
            String content = page.getHtml().xpath("//div[@class='article-con font16']/p[2]/span/text()").get();
            String content2 = page.getHtml().xpath("//div[@class='article-con font16']/p[3]/span/text()").get();
            String content3 = page.getHtml().xpath("//div[@class='article-con font16']/p[4]/span/text()").get();
            String accumulate = page.getHtml().xpath("//div[@class='article-con font16']/p[3]/span/text()").get();
            if (StringUtils.isNotBlank(content2) && content2.length() > content.length()) {
                content = content2;
                accumulate = page.getHtml().xpath("//div[@class='article-con font16']/p[4]/span/text()").get();
            }
            if (StringUtils.isBlank(content) || (StringUtils.isNotBlank(content3) && content3.length() > content.length())) {
                content = content3;
                accumulate = page.getHtml().xpath("//div[@class='article-con font16']/p[5]/span/text()").get();
            }
            String dateStr = page.getHtml().xpath("//div[@class='detail-con']/p/text()").get();
            Cea cea = new Cea();
            if (StringUtils.isBlank(content)) {
                return;
            }
            String pattern = "挂牌协议交易成交量(\\d+(,\\d{3})*(.\\d+)?)吨";
            String result = regexMatch(pattern, content, "挂牌协议交易成交量", "吨", ",");
            if(StringUtils.isBlank(result)) {
                return;
            }
            cea.setListingTradeVolume(Double.valueOf(result).longValue());

            pattern = "成交额\\d+(,\\d{3})*(.\\d+)?元，开盘价";
            result = regexMatch(pattern, content, "成交额", "元，开盘价", ",");
            cea.setListingTradeFee(BigDecimal.valueOf(Double.parseDouble(result)));

            pattern = "开盘价\\d+(,\\d{3})*(.\\d+)?元";
            result = regexMatch(pattern, content, "开盘价", "元", ",");
            cea.setOpeningPrice(BigDecimal.valueOf(Double.parseDouble(result)));

            pattern = "最高价\\d+(,\\d{3})*(.\\d+)?元";
            result = regexMatch(pattern, content, "最高价", "元", ",");
            cea.setMaxPrice(BigDecimal.valueOf(Double.parseDouble(result)));

            pattern = "最低价\\d+(,\\d{3})*(.\\d+)?元";
            result = regexMatch(pattern, content, "最低价", "元", ",");
            cea.setMinPrice(BigDecimal.valueOf(Double.parseDouble(result)));

            pattern = "收盘价\\d+(,\\d{3})*(.\\d+)?元";
            result = regexMatch(pattern, content, "收盘价", "元", ",");
            cea.setClosingPrice(BigDecimal.valueOf(Double.parseDouble(result)));

            pattern = "收盘价较前一日(?:上涨|下跌)\\d+(,\\d{3})*(.\\d+)?";
            result = regexMatch(pattern, content, "收盘价较前一日", ",");
            cea.setPriceLimit(BigDecimal.valueOf(Double.parseDouble(StringUtils.isNotBlank(result) ? result : "0")));

            pattern = "大宗协议交易成交量\\d+(,\\d{3})*(.\\d+)?吨";
            result = regexMatch(pattern, content, "大宗协议交易成交量", "吨", ",");
            cea.setLargeTradeVolume(Long.parseLong(StringUtils.isNotBlank(result) ? result : "0"));

            pattern = "成交额\\d+(,\\d{3})*(.\\d+)?元。今日";
            result = regexMatch(pattern, content, "成交额", "元。今日", ",");
            cea.setLargeTradeFee(BigDecimal.valueOf(Double.parseDouble(StringUtils.isNotBlank(result) ? result : "0")));

            pattern = "累计成交量\\d+(,\\d{3})*(.\\d+)?吨";
            result = regexMatch(pattern, accumulate, "累计成交量", "吨", ",");
            cea.setAccumulateVolume(Long.valueOf(result));

            pattern = "累计成交额\\d+(,\\d{3})*(.\\d+)?元";
            result = regexMatch(pattern, accumulate, "累计成交额", "元", ",");
            cea.setAccumulateFee(BigDecimal.valueOf(Double.parseDouble(result)));

            try {
                Date date = DateUtils.parseDate(dateStr.replace("发布时间：", ""), "yyyy-MM-dd");
                cea.setTradeDate(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
            page.putField("cea", cea);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    private String regexMatch(String pattern, String content, String... replaces) {
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(content);
        String group = "";
        if (matcher.find()) {
            group = matcher.group(0);
        }
        for (int i = 0; i < replaces.length; i++) {
            group = group.replace(replaces[i], "");
        }
        group = group.replace("上涨", "");
        group = group.replace("下跌", "-");
        return group;
    }
}
