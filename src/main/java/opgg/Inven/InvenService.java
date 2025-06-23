package opgg.Inven;

import opgg.entity.InvenPost;
import java.util.List;

public interface InvenService {

    InvenPost createPost(InvenPost post);

    InvenPost getPostById(Long id);

    List<InvenPost> getAllPosts();

    InvenPost updatePost(Long id, InvenPost updatedPost);

    void deletePost(Long id);
}
