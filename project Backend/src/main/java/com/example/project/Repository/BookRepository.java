package com.example.project.Repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.project.Entities.Book;

import jakarta.transaction.Transactional;




@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book,Integer>{
	@Query("select B from Book B where B.status=0")
	public List<Book> getNotVerified();
	  
	@Modifying
	@Query("UPDATE Book b SET b.status = 1 WHERE b.bookId = :bid")
	public int VerifyBook(int bid);
	
	@Query(value="select * from book where title=:bname",nativeQuery=true)
	public Book getBook(String bname);
	
    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE %:title%")
    List<Book> findByTitleLikeIgnoreCase( String title);

    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE CONCAT(:title, '%')")
    List<Book> findByTitleStartingWithIgnoreCase( String title);
    
    @Query("SELECT b FROM Book b WHERE LOWER(b.genre) LIKE %:genre%")
    List<Book> findByGenreLikeIgnoreCase( String genre);

    @Query("SELECT b FROM Book b WHERE LOWER(b.genre) LIKE CONCAT(:genre, '%')")
    List<Book> findByGenreStartingWithIgnoreCase( String genre);
    
    @Query("SELECT b FROM Book b WHERE LOWER(b.author) LIKE CONCAT(:author, '%')")
    List<Book> findByAuthorStartingWithIgnoreCase(String author);

    @Query("SELECT b FROM Book b WHERE LOWER(b.author) LIKE %:author%")
    List<Book> findByAuthorLikeIgnoreCase(String author);
}