package com.moon.bookstore.service.spider;

import com.moon.bookstore.api.entity.PilotCea;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 试点配额爬虫
 *
 * @author yujiangtao
 * @date 2022/2/22
 */
public class PilotCeaProcessor implements PageProcessor {

    private final Site site = Site.me().setRetryTimes(3).setSleepTime(2000);

    private static volatile int pageNo = 1;

    @Override
    @SneakyThrows
    public void process(Page page) {
        List<Selectable> nodes = page.getHtml().xpath("//table/tbody/tr").nodes();
        if(nodes != null && nodes.size() > 0) {
            List<PilotCea> list = new ArrayList<>();
            for (int i = 1; i < nodes.size(); i++) {
                Selectable selectable = nodes.get(i);
                Date tradeDate = DateUtils.parseDate(selectable.xpath("//td[1]/text()").toString(), "yyyy-MM-dd");
                String stockType = selectable.xpath("//td[2]/text()").toString();
                if ("SZA-2020".equals(stockType) || "SZA-2019".equals(stockType)) {
                    Double openingPrice = Double.valueOf(selectable.xpath("//td[3]/text()").toString());
                    String maxPriceStr = selectable.xpath("//td[4]/text()").toString();
                    Double maxPrice = StringUtils.isNotBlank(maxPriceStr) ? Double.valueOf(maxPriceStr) : null;
                    String minPriceStr = selectable.xpath("//td[5]/text()").toString();
                    Double minPrice = StringUtils.isNotBlank(minPriceStr) ? Double.valueOf(minPriceStr) : null;
                    String averagePriceStr = selectable.xpath("//td[6]/text()").toString();
                    Double averagePrice = StringUtils.isNotBlank(averagePriceStr) ? Double.valueOf(averagePriceStr) : null;
                    Double closingPrice = Double.valueOf(selectable.xpath("//td[7]/text()").toString());
                    Long tradeVolume = Long.valueOf(selectable.xpath("//td[8]/text()").toString().replace(",", ""));
                    Double tradeFee = Double.valueOf(selectable.xpath("//td[9]/text()").toString().replace(",", ""));
                    PilotCea pilotCea = PilotCea.builder().tradeDate(tradeDate).stockType(stockType).exchangeCode("755")
                            .openingPrice(openingPrice).maxPrice(maxPrice).minPrice(minPrice).averagePrice(averagePrice)
                            .closingPrice(closingPrice).tradeVolume(tradeVolume).tradeFee(tradeFee).build();
                    list.add(pilotCea);
                }
            }
            page.putField("list", list);
        }
        page.addTargetRequest("http://www.cerx.cn/dailynewsCN/index_" + (++pageNo) + ".htm");
    }

    @Override
    public Site getSite() {
        return site;
    }
}
