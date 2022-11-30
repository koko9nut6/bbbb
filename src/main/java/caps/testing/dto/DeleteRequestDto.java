package caps.testing.dto;


import caps.testing.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteRequestDto {

//    private String name;
//    private String email;
//    private String phone;
//    private String pwd;
    private String team_name;

    public Member toDelete() {
        return Member.builder()
                .team_name(team_name)
                .build();
    }
}
