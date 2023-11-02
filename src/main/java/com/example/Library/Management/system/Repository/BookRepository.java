package com.example.Library.Management.system.Repository;

import com.example.Library.Management.system.Entities.Book;
import com.example.Library.Management.system.Entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    List<Book> findBookByGenre(Genre genre);
    //hibernate creates query internally and fetch the data from database

}
