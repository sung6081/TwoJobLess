package opgg.repository;

import opgg.entity.InvenPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvenRepository extends JpaRepository<InvenPost, Long> {
}
