package uz.pdp.absamatov_avaz_b6_exam_1_variant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.absamatov_avaz_b6_exam_1_variant.model.Author;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookDto {
    private String name;
    private Double price;
    private List<Author> authors;
}
