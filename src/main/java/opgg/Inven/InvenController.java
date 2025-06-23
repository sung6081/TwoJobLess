package opgg.Inven;

import opgg.entity.InvenPost;
import opgg.Inven.InvenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inven/post")
public class InvenController {

    private final InvenService invenService;

    @Autowired
    public InvenController(InvenService invenService) {
        this.invenService = invenService;
    }

    // 게시글 작성
    @PostMapping
    public InvenPost createPost(@RequestBody InvenPost post) {
        return invenService.createPost(post);
    }

    // 게시글 전체 조회
    @GetMapping
    public List<InvenPost> getAllPosts() {
        return invenService.getAllPosts();
    }

    // 게시글 상세 조회
    @GetMapping("/{id}")
    public InvenPost getPostById(@PathVariable Long id) {
        return invenService.getPostById(id);
    }

    // 게시글 수정
    @PutMapping("/{id}")
    public InvenPost updatePost(@PathVariable Long id, @RequestBody InvenPost updatedPost) {
        return invenService.updatePost(id, updatedPost);
    }

    // 게시글 삭제
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        invenService.deletePost(id);
    }
}
