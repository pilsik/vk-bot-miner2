package by.sivko.vkbotminer.services;

import by.sivko.vkbotminer.models.StatisticFarm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SaveStatisticsFarmService {

    public StatisticFarm statisticFarm;

    public Date date;

    public void save(StatisticFarm statisticFarm) {
        this.statisticFarm = statisticFarm;
        this.date = new Date();
    }

    public SaveStatisticsFarmService() {
    }
}
