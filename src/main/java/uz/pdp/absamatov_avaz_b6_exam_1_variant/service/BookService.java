package uz.pdp.absamatov_avaz_b6_exam_1_variant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.absamatov_avaz_b6_exam_1_variant.dto.BookDto;
import uz.pdp.absamatov_avaz_b6_exam_1_variant.model.Book;
import uz.pdp.absamatov_avaz_b6_exam_1_variant.repository.BookRepository;

import javax.xml.ws.Response;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }


    public HttpEntity<?> editBook(Integer id, BookDto bookDto) {
        Optional<Book> bookById = bookRepository.findById(id);
        if(bookById.isPresent()){
            Book editBook = bookById.get();
            editBook.setName(bookDto.getName());
            editBook.setPrice(bookDto.getPrice());
            editBook.setAuthors(bookDto.getAuthors());
            return ResponseEntity.ok(editBook);
        }
        return ResponseEntity.notFound().build();

    }


    public HttpEntity<?> deleteBook(Integer id) {
        if(id !=null) {
            bookRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        else return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    public HttpEntity<?> getBookById(Integer id) {
        if(id !=null){
            return ResponseEntity.ok(bookRepository.findById(id));
        }
        return ResponseEntity.badRequest().build();
    }

    public HttpEntity<?> addBook(Book book) {
        return ResponseEntity.ok(bookRepository.save(book));
    }
}
