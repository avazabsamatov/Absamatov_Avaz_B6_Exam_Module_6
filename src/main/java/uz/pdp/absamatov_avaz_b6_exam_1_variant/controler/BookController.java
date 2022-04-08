package uz.pdp.absamatov_avaz_b6_exam_1_variant.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.absamatov_avaz_b6_exam_1_variant.dto.BookDto;
import uz.pdp.absamatov_avaz_b6_exam_1_variant.model.Book;
import uz.pdp.absamatov_avaz_b6_exam_1_variant.service.BookService;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping
//    @PreAuthorize(value = "hasAnyRole('ADMIN','SUPER_ADMIN','USER')")
    public HttpEntity<?> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }
    @PostMapping
//    @PreAuthorize(value = "hasAnyRole('ADMIN','SUPER_ADMIN')")
    public HttpEntity<?> addNewBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PutMapping("/{id}")
//    @PreAuthorize(value = "hasAnyRole('ADMIN','SUPER_ADMIN')")
    public HttpEntity<?> editBook(@PathVariable Integer id, BookDto bookDto) {
        return ResponseEntity.ok(bookService.editBook(id, bookDto));
    }
    @DeleteMapping("/{id}")
//    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    public HttpEntity<?> deleteBook(@PathVariable Integer id){
        return ResponseEntity.ok( bookService.deleteBook(id));
    }

    @GetMapping("/{id}")
//    @PreAuthorize(value = "hasAnyRole('ADMIN','SUPER_ADMIN','USER')")
    public HttpEntity<?> getBookById(@PathVariable Integer id){
        return ResponseEntity.ok(bookService.getBookById(id));
    }
}
