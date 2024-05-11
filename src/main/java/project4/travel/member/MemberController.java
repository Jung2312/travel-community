//package project4.travel.member;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller            // 해당 클래스가 컨트롤러임을 알리고 bean으로 등록하기 위함
//@RequiredArgsConstructor    // 나중에 의존관계 관련하여 필요한 어노테이션
//public class MemberController {
//
//    private final MemberService memberService;
//    @GetMapping("/")
//    public String home() {
//        return "home";
//    }
//
//    @GetMapping("/members/new")
//    public String createMemberForm() {
//        return "members/createMemberForm";
//    }
//
//    @PostMapping("/members/new")
//    public String createMember(MemberFormDTO memberFormDTO) {
//        String memberId = memberService.join(memberFormDTO);
//        return "home";
//    }
//
//}
