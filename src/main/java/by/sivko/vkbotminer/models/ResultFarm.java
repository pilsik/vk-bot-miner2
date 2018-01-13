package by.sivko.vkbotminer.models;

public class ResultFarm {

    public int gpu_id;
    public int temperature;
    public double sol_ps;
    public double avg_sol_ps;
    public double sol_pw;
    public double avg_sol_pw;
    public double power_usage;
    public double avg_power_usage;
    public int accepted_shares;
    public int rejected_shares;
    public int latency;

    public ResultFarm() {
    }

    public ResultFarm(int gpu_id, int temperature, double sol_ps, double avg_sol_ps, double sol_pw, double avg_sol_pw, double power_usage, double avg_power_usage, int accepted_shares, int rejected_shares, int latency) {
        this.gpu_id = gpu_id;
        this.temperature = temperature;
        this.sol_ps = sol_ps;
        this.avg_sol_ps = avg_sol_ps;
        this.sol_pw = sol_pw;
        this.avg_sol_pw = avg_sol_pw;
        this.power_usage = power_usage;
        this.avg_power_usage = avg_power_usage;
        this.accepted_shares = accepted_shares;
        this.rejected_shares = rejected_shares;
        this.latency = latency;
    }

    @Override
    public String toString() {
        return "ResultFarm{" +
                "gpu_id=" + gpu_id +
                ", temperature=" + temperature +
                ", sol_ps=" + sol_ps +
                ", avg_sol_ps=" + avg_sol_ps +
                ", sol_pw=" + sol_pw +
                ", avg_sol_pw=" + avg_sol_pw +
                ", power_usage=" + power_usage +
                ", avg_power_usage=" + avg_power_usage +
                ", accepted_shares=" + accepted_shares +
                ", rejected_shares=" + rejected_shares +
                ", latency=" + latency +
                '}';
    }
}
