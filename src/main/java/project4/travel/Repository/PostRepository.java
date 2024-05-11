package project4.travel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project4.travel.Entity.Posts;

import java.util.List;

public interface PostRepository extends JpaRepository<Posts, Long> {

    List<Posts> findAll();

    Posts findByid(long id);

    List<Posts> findAllByTitleContainingOrContentContaining(String title, String content);

    void deletePostsById(Long id);


}