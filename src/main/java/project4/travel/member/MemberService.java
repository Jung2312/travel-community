package project4.travel.member;


import java.util.Optional;

public interface MemberService {
    String join(MemberFormDTO memberFormDTO);
    boolean login(String userid, String password);

    Optional<Member> findByUserid(String userid);

    Optional<Member> findLoggedInUser();
}
