package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Inning {

    private String _id;
    private String battingTeamId;
    private String bowlingTeamId;
    private int totalRuns;
    private int totalWickets;
    private List<Character> scoreLine = new ArrayList<>();

    public Inning(String battingTeamId, String bowlingTeamId) {
        this.battingTeamId = battingTeamId;
        this.bowlingTeamId = bowlingTeamId;
    }
}
