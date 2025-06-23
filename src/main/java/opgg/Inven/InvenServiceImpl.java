package opgg.Inven;

import opgg.entity.InvenPost;
import opgg.repository.InvenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class InvenServiceImpl implements InvenService {

    private final InvenRepository invenRepository;

    @Autowired
    public InvenServiceImpl(InvenRepository invenRepository) {
        this.invenRepository = invenRepository;
    }

    @Override
    @Transactional
    public InvenPost createPost(InvenPost post) {
        return invenRepository.save(post);
    }

    @Override
    public InvenPost getPostById(Long id) {
        return invenRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
    }

    @Override
    public List<InvenPost> getAllPosts() {
        return invenRepository.findAll();
    }

    @Override
    @Transactional
    public InvenPost updatePost(Long id, InvenPost updatedPost) {
        InvenPost existingPost = getPostById(id);
        existingPost.setTitle(updatedPost.getTitle());
        existingPost.setContent(updatedPost.getContent());
        return invenRepository.save(existingPost);
    }

    @Override
    @Transactional
    public void deletePost(Long id) {
        if (!invenRepository.existsById(id)) {
            throw new IllegalArgumentException("삭제할 게시글이 존재하지 않습니다.");
        }
        invenRepository.deleteById(id);
    }
}
