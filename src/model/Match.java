package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Match {

    private String _id;
    private int overs;
    private String firstInningId;
    private String secondInningId;
    private String winnerId;

    public Match(int overs, String firstInningId, String secondInningId) {
        this.overs = overs;
        this.firstInningId = firstInningId;
        this.secondInningId = secondInningId;
    }
}
