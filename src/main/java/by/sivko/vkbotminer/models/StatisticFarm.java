package by.sivko.vkbotminer.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;


public class StatisticFarm {

    public int uptime;
    public int contime;
    public String server;
    public int port;
    public String user;
    public String version;
    public String error;
    public int id;
    public List<ResultFarm> result;

    public StatisticFarm() {
    }

    public StatisticFarm(int uptime, int contime, String server, int port, String user, String version, String error, int id, List<ResultFarm> result) {
        this.uptime = uptime;
        this.contime = contime;
        this.server = server;
        this.port = port;
        this.user = user;
        this.version = version;
        this.error = error;
        this.id = id;
        this.result = result;
    }

    @Override
    public String toString() {
        return "StatisticFarm{" +
                "uptime=" + uptime +
                ", contime=" + contime +
                ", server='" + server + '\'' +
                ", port=" + port +
                ", user='" + user + '\'' +
                ", version='" + version + '\'' +
                ", error='" + error + '\'' +
                ", id=" + id +
                ", result=" + result +
                '}';
    }
}
