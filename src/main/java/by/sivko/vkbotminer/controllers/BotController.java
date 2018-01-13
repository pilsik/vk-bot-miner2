package by.sivko.vkbotminer.controllers;

import by.sivko.vkbotminer.services.SaveStatisticsFarmService;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class BotController {

    private final static Logger log = Logger.getLogger(BotController.class.getName());

    @Autowired
    SaveStatisticsFarmService saveStatisticsFarmService;

    @RequestMapping(value = "/bot", method = RequestMethod.POST)
    public String bot(HttpServletRequest httpServletRequest) {

        String data = "";

        try {
            data = httpServletRequest.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (data.isEmpty()) {
            return "no";
        }

        log.info("DATA:");
        log.info(data);

        final JSONObject object = new JSONObject(data);

        if (!object.getString("secret").equals(System.getenv("VK_SECRET_TOKEN")) && !object.getString("type").equals("confirmation")) {
            log.error(object.getString("secret") + "!=" + System.getenv("VK_SECRET_TOKEN"));
            log.error(object.getString("type") + "!=" + "confirmation");
            return "no";
        }

        switch (object.getString("type")) {
            case "confirmation":
                log.info("return confirmation code");
                return System.getenv("VK_CONFIRMATION_CODE");
            case "message_new":
              /*  String textBody = object.getJSONObject("object").getString("body");*/
                if (saveStatisticsFarmService.statisticFarm != null) {
                    Map<String, String> queryMap = new HashMap<>();
                    queryMap.put("user_id", String.valueOf(object.getJSONObject("object").getInt("user_id")));
                    queryMap.put("message", String.format("Date: %s\n" +
                                    "Server: %s:%s\n" +
                                    "User: %s\n", saveStatisticsFarmService.date.toString(),
                            saveStatisticsFarmService.statisticFarm.server,
                            saveStatisticsFarmService.statisticFarm.port,
                            saveStatisticsFarmService.statisticFarm.user));
                    queryMap.put("access_token", System.getenv("VK_API_TOKEN"));
                    queryMap.put("v", "5.69");
                    try {
                        for (int i = 0; i < saveStatisticsFarmService.statisticFarm.result.size() + 1; i++) {
                            URL serverUrl =
                                    new URL("https://api.vk.com/method/messages.send?" + toQueryString(queryMap));
                            log.info(serverUrl.toString());
                            HttpURLConnection con = (HttpURLConnection) serverUrl.openConnection();
                            con.setRequestMethod("GET");
                            con.getResponseCode();
                            if (i < saveStatisticsFarmService.statisticFarm.result.size())
                                queryMap.put("message", String.format(saveStatisticsFarmService.statisticFarm.result.get(i).toString()));
                        }
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (ProtocolException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Map<String, String> queryMap = new HashMap<>();
                    queryMap.put("user_id", String.valueOf(object.getJSONObject("object").getInt("user_id")));
                    queryMap.put("message", "Need wait");
                    queryMap.put("access_token", System.getenv("VK_API_TOKEN"));
                    queryMap.put("v", "5.69");
                    try {
                        URL serverUrl =
                                new URL("https://api.vk.com/method/messages.send?" + toQueryString(queryMap));
                        log.info(serverUrl.toString());
                        HttpURLConnection con = (HttpURLConnection) serverUrl.openConnection();
                        con.setRequestMethod("GET");
                        con.getResponseCode();
                    } catch (ProtocolException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return "ok";
        }
        return "data";
    }

    public String toQueryString(Map<?, ?> data) throws UnsupportedEncodingException {
        StringBuffer queryString = new StringBuffer();

        for (Map.Entry<?, ?> pair : data.entrySet()) {
            queryString.append(URLEncoder.encode((String) pair.getKey(), "UTF-8") + "=");
            queryString.append(URLEncoder.encode((String) pair.getValue(), "UTF-8") + "&");
        }

        if (queryString.length() > 0) {
            queryString.deleteCharAt(queryString.length() - 1);
        }

        return queryString.toString();
    }
}
