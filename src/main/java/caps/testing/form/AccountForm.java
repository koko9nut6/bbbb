package caps.testing.form;

import caps.testing.domain.Administration;
import caps.testing.domain.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
public class AccountForm {

    private Long id;
    private String name;
    private String pwd;
    private String email;
    @Enumerated(EnumType.STRING)
    private Administration admin;
    private String phone;

    @Builder
    public AccountForm(Long id, String name, String pwd, String email, Administration admin, String phone) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.email = email;
        this.admin = admin;
        this.phone = phone;
    }

    public Member toEntity(){
        return Member.builder()
                .id(id)
                .name(name)
                .email(email)
                .pwd(new BCryptPasswordEncoder().encode(pwd))
                .admin(admin)
                .phone(phone)
                .build();
    }

}