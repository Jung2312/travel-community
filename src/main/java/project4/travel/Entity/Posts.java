package project4.travel.Entity;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import project4.travel.member.Member;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @CreationTimestamp
    @NotNull
    Date createdAt;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "userid")
    Member userid; // FK

    public Posts(String title, String content, Member member){
        this.title = title;
        this.content = content;
        this.userid = member;
        this.createdAt = new Date();
    }

    public void update(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.createdAt = new Date();
    }
}