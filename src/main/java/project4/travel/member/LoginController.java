package project4.travel.member;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller            // 해당 클래스가 컨트롤러임을 알리고 bean으로 등록하기 위함
@RequiredArgsConstructor    // 나중에 의존관계 관련하여 필요한 어노테이션
public class LoginController {
    private final MemberService memberService;
    @GetMapping("/members/login")
    public String loginForm() {
        return "members/loginForm";
    }

    @PostMapping("/members/login")
    public String login(LoginFormDTO loginFormDTO, Model model, HttpSession session) {
        boolean loggedIn = memberService.login(loginFormDTO.getUserid(), loginFormDTO.getPassword());
        System.out.println("로그인 성공 여부: " + loggedIn + loginFormDTO.getUserid());

        if (loggedIn) {
            // Store the user ID in the session upon successful login
            session.setAttribute("loggedInUserId", loginFormDTO.getUserid());
            return "redirect:/post";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "members/loginForm";
        }
    }
}
