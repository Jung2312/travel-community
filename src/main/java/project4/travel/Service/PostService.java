package project4.travel.Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project4.travel.DTO.CreatePostDTO;
import project4.travel.DTO.FindPostsDto;
import project4.travel.DTO.UpdatePostsDto;
import project4.travel.Entity.Posts;
import project4.travel.member.Member;
import project4.travel.member.MemberRepository;
import project4.travel.Repository.PostRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired //의존성 주입
    PostRepository postRepository;

    public List<Posts> getAllPosts() {
        return postRepository.findAll();
    }


    public Posts create(String userid, CreatePostDTO createDTO) {

        Optional<Member> optionalMember = memberRepository.findByUserid(userid);

        Member member = optionalMember.orElseThrow(() -> new RuntimeException("userid에 대한 회원을 찾을 수 없습니다: " + userid));

        Posts addPost = new Posts(createDTO.getTitle(), createDTO.getContent(), member);
        postRepository.save(addPost);

        return addPost;
    }

    public Posts getPost(long postID) {
        return postRepository.findByid(postID);
    }

    public Member getMemberByPostId(long postId) {
        Posts post = postRepository.findByid(postId);
        return post != null ? post.getUserid() : null;
    }

    public List<Posts> findPosts(FindPostsDto filter) {
        return postRepository.findAllByTitleContainingOrContentContaining(filter.getTitle(), filter.getContent());
    }


    public Posts update(long id, UpdatePostsDto requestDto) {
        Posts entity = postRepository.findByid(id); // id에 해당하는 게시글을 검색

        if (entity == null) {
            throw new IllegalArgumentException();
        }

        if (requestDto.getTitle() != null) {
            entity.setTitle(requestDto.getTitle());
        }

        if (requestDto.getContent() != null) {
            entity.setContent(requestDto.getContent());
        }

        return postRepository.save(entity);
    }


    @Transactional
    public void deletePost(Long postID) {
        postRepository.deletePostsById(postID);
    }
}
