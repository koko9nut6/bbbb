package caps.testing.dto;

import caps.testing.domain.Administration;
import caps.testing.domain.Member;
import lombok.*;

import javax.persistence.*;

@Data
@Setter @Getter
@NoArgsConstructor
public class MemberDTO {

    private Long id;
    private String name;
    private String email;
    private String pwd;

//    @Embedded
//    @Column(name = "MEMBER_ADDRESS")
//    private Address address;
    private Administration admin;
    private String phone;
    private String authentication_code;

    @Builder
    public MemberDTO(Long id, String name, String email, String pwd, Administration admin, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pwd = pwd;
        this.admin = admin;
        this.phone = phone;
        this.authentication_code = authentication_code;
    }

    public Member toMember(){
        return Member.builder()
                .id(id)
                .name(name)
                .email(email)
                .pwd(pwd)
                .admin(admin)
                .phone(phone)
                .build();
    }
}
