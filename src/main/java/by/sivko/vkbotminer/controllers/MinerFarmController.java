package by.sivko.vkbotminer.controllers;

import by.sivko.vkbotminer.models.StatisticFarm;
import by.sivko.vkbotminer.services.SaveStatisticsFarmService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.stream.Collectors;

@RestController
public class MinerFarmController {

    @Autowired
    SaveStatisticsFarmService saveStatisticsFarmService;

    @RequestMapping(value = "/farm", method = RequestMethod.POST)
    public String setStatisticFarm(HttpServletRequest httpServletRequest) {
        String data;
        try {
            data = httpServletRequest.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            ObjectMapper mapper = new ObjectMapper();
            StatisticFarm statisticFarm = mapper.readValue(data, StatisticFarm.class);
            saveStatisticsFarmService.save(statisticFarm);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }
}
