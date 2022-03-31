package com.example.bookstorebackend.repository;
import com.example.bookstorebackend.dto.BookStoreDTO;
import com.example.bookstorebackend.entity.BookData;
import com.example.bookstorebackend.entity.BookId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookData,Integer> {

    List<BookData> findAll();

    //Optional<BookData> findById(Integer Math.toIntExact(Long bookid));
    Optional<BookData> findByBookId(Long bookId);

    BookData save(BookStoreDTO bookStoreDTO);

    void  deleteById(Integer bookId);
}
