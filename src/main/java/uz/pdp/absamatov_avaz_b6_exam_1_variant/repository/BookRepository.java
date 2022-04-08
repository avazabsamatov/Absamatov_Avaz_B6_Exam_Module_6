package uz.pdp.absamatov_avaz_b6_exam_1_variant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.absamatov_avaz_b6_exam_1_variant.model.Book;

public interface BookRepository extends JpaRepository<Book,Integer> {
}
