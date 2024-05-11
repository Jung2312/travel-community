package project4.travel.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Data			// Getter Setter
@Builder        // DTO -> Entity화
@AllArgsConstructor    // 모든 컬럼 생성자 생성
@NoArgsConstructor    // 기본 생성자
@Table(name = "member")
public class Member {
    @Id
    private String userid;

    @Column(nullable = false)
    private String userpw;

    @Column
    private int userage;

    public boolean authenticate(String userid, String password) {
        return this.userid.equals(userid) && this.userpw.equals(password);
    }

}
