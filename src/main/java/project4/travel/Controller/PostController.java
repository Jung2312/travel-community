package project4.travel.Controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletResponse;
import project4.travel.DTO.CreatePostDTO;
import project4.travel.DTO.FindPostsDto;
import project4.travel.DTO.UpdatePostsDto;
import project4.travel.Entity.Posts;
import project4.travel.Service.PostService;
import project4.travel.member.Member;
import project4.travel.member.MemberService;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    private MemberService memberService;

    @GetMapping("/post")
    public String readAllPost(Model model) {
        List<Posts> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "/post/postlist"; // "/postlist"로 직접 반환
    }

    @GetMapping("/write-post")
    public String showWritePostForm(Model model, HttpSession session) {
        String loggedInUserId = (String) session.getAttribute("loggedInUserId");

        System.out.println("write-post"+loggedInUserId);
        if (loggedInUserId != null) {
            session.setAttribute("userid", loggedInUserId);
            return "/post/writepost";
        } else {
            // Handle the case where the user is not logged in
            model.addAttribute("userid", "UnknownUser");
            return "/members/loginForm"; // Redirect to the login page, for example
        }

    }

    @PostMapping("/save-post")
    public String savePost(@ModelAttribute CreatePostDTO createDTO, HttpSession session) {
        try {
            String loggedInUserId = (String) session.getAttribute("userid");
            System.out.println("save-post"+loggedInUserId);
            if (loggedInUserId != null) {
                session.setAttribute("userid", loggedInUserId);
                postService.create(loggedInUserId, createDTO);
                return "redirect:/post";
            } else {
                return "/members/loginForm"; // Redirect to the login page, for example
            }
        } catch (RuntimeException e) {
            // 예외 발생 시 처리 (예: 에러 메시지 로깅 또는 사용자에게 알림)
            e.printStackTrace(); // 혹은 로깅 프레임워크를 사용하여 로깅
            return "/error"; // 에러 페이지로 이동하거나 다른 적절한 처리 수행
        }
    }

    @GetMapping("/{postID}")
    Posts readPost(@PathVariable long postID, HttpServletResponse response) {
        Posts post = postService.getPost(postID);

        if (post == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        return post;
    }

    @GetMapping("/post/{postID}")
    public String readPostDetails(@PathVariable long postID, Model model) {
        Posts post = postService.getPost(postID);

        if (post == null) {
            // 게시물이 없을 경우 예외처리 또는 에러 페이지로 리다이렉트
            return "redirect:/error";
        }

        Member member = postService.getMemberByPostId(postID);

        model.addAttribute("post", post);
        model.addAttribute("member", member); // Member 정보 추가

        return "post/postdetails"; // postdetails.html로 이동
    }


    @PostMapping("/find")
    List<Posts> findPosts(@RequestBody FindPostsDto filter) {
        return postService.findPosts(filter);
    }


    @PatchMapping("/{id}")
    public Posts update(
            @PathVariable long id,
            @RequestBody UpdatePostsDto requestDto,
            HttpServletResponse response
    ) {

        long Id = id;

        try {
            return postService.update(id, requestDto); //수정 Posts 값 반환
        } catch (IllegalArgumentException exception) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }


    @DeleteMapping("/{postID}")
    void deletePost(
            @PathVariable Long postID,
            HttpServletResponse response
    ) {
        Posts post = postService.getPost(postID);

        if (post == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        postService.deletePost(postID);
    }

}
