package caps.testing.dto;

import caps.testing.domain.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamDto {

    private String name;

    public Team toTeam() {
        return Team.builder()
                .name(name)
                .build();
    }
}