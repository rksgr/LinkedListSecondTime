package com.example.bookstorebackend.repository;
import com.example.bookstorebackend.dto.BookStoreDTO;
import com.example.bookstorebackend.entity.BookStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookStore,Integer> {

    List<BookStore> findAll();

    Optional<BookStore> findById(Integer bookId);

    BookStore save(BookStoreDTO bookStoreDTO);

    void  deleteById(Integer bookId);
}
