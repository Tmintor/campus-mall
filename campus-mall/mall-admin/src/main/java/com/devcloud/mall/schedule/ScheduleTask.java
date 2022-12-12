package com.devcloud.mall.schedule;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.devcloud.mall.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author 吴员外
 * @date 2022/10/18 0:13
 */
@Component
public class ScheduleTask {

    @Autowired
    private StatisticsService statisticsService;

    //每天凌晨00:00:01执行，统计前一天数据
    @Scheduled(cron = "1 0 0 1/1 * ?")
    public void statisticData() {
        DateTime yesterday = DateUtil.yesterday();
        statisticsService.generateStatistics(yesterday.toSqlDate());
    }


}
