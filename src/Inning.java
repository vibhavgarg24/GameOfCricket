public class Inning {

    private Team battingTeam;
    private Team ballingTeam;
    private int totalRuns;
    private int totalWickets;

    public Inning(Team battingTeam, Team ballingTeam) {
        this.battingTeam = battingTeam;
        this.ballingTeam = ballingTeam;
    }

    public Team getBattingteam() {
        return battingTeam;
    }

    public void setBattingTeam(Team battingTeam) {
        this.battingTeam = battingTeam;
    }

    public Team getBallingTeam() {
        return ballingTeam;
    }

    public void setBallingTeam(Team ballingTeam) {
        this.ballingTeam = ballingTeam;
    }

    public int getTotalRuns() {
        return totalRuns;
    }

    public void setTotalRuns(int totalRuns) {
        this.totalRuns = totalRuns;
    }

    public int getTotalWickets() {
        return totalWickets;
    }

    public void setTotalWickets(int totalWickets) {
        this.totalWickets = totalWickets;
    }
}
