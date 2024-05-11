package project4.travel.member;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Autowired
    private HttpSession httpSession;
    @Override
    public String join(MemberFormDTO memberFormDTO) {
        Member member = Member.builder()
                .userid(memberFormDTO.getUsername())
                .userpw(memberFormDTO.getPassword())
                .userage(memberFormDTO.getAge())
                .build();

        return memberRepository.save(member).getUserid();
    }

    @Override
    public boolean login(String userid, String password) {
        if (userid != null && !userid.isEmpty() && password != null && !password.isEmpty()) {
            Optional<Member> optionalMember = memberRepository.findByUserid(userid);
            return optionalMember.map(member -> member.authenticate(userid, password)).orElse(false);
        } else {
            return false;
        }
    }

    public Optional<Member> findByUserid(String userid) {
        return memberRepository.findByUserid(userid);
    }

    public Optional<Member> findLoggedInUser() {
        // Retrieve user information from the session
        String loggedInUserId = (String)httpSession.getAttribute("loggedInUserId");

        if (loggedInUserId != null) {
            return findByUserid(loggedInUserId);
        }

        return null;
    }
}
