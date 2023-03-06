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
public class Team {

    private String _id;
    private String name;
    private List<String> playerIds = new ArrayList<>(11);

    public Team(String name) {
        this.name = name;
    }
}
