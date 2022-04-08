package uz.pdp.absamatov_avaz_b6_exam_1_variant.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.pdp.absamatov_avaz_b6_exam_1_variant.model.Author;
import uz.pdp.absamatov_avaz_b6_exam_1_variant.model.Book;
import uz.pdp.absamatov_avaz_b6_exam_1_variant.model.Role;
import uz.pdp.absamatov_avaz_b6_exam_1_variant.model.User;
import uz.pdp.absamatov_avaz_b6_exam_1_variant.repository.AuthorRepository;
import uz.pdp.absamatov_avaz_b6_exam_1_variant.repository.BookRepository;
import uz.pdp.absamatov_avaz_b6_exam_1_variant.repository.RoleRepository;
import uz.pdp.absamatov_avaz_b6_exam_1_variant.repository.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Role> roles1 = new ArrayList<>(Arrays.asList(
                new Role(null, "ROLE_SUPER_ADMIN") ));

        roleRepository.saveAll(roles1);
        List<Role> roles2 = new ArrayList<>(Arrays.asList(
                new Role(null,"ROLE_ADMIN")
        ));
        roleRepository.saveAll(roles2);
        userRepository.save(new User(null,"john","123",roles1));
        userRepository.save(new User(null,"jack","111",roles2));

        List<Author> authors = new ArrayList<>(Arrays.asList(
                new Author(null, "Albert", "Enshteyn"),
                new Author(null, "Robert", "Kiyosaki")
        ));
        authorRepository.saveAll(authors);



        bookRepository.save(new Book(null,"Physic",25000.0,authors));
    }
}
