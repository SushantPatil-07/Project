package com.example.project.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.Entities.Book;
import com.example.project.Entities.IssuedBook;
import com.example.project.Repository.BookRepository;

@Service
public class BookService {
    
    @Autowired
     BookRepository bookRepository;
    
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
    public List<Book> getNotVerifiedBook()
    {
    	return bookRepository.getNotVerified();
    }
    
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
   
    public Book findBookById(int id) {
        Optional<Book> or = bookRepository.findById(id);
        return or.orElse(null);
    }
    
    public Book updateBDetails(Book book) {
    	
    	System.out.println(book);
		Book p=bookRepository.findById(book.getBookId()).get();
			p.setiSBN(book.getiSBN());
			p.setTitle(book.getTitle());
			p.setAuthor(book.getAuthor());
			p.setPrice(book.getPrice());
			p.setGenre(book.getGenre());
			
			return bookRepository.save(p);

    }
    
    public int verifybookbyid(int bid)
    {
    	return bookRepository.VerifyBook(bid);
    }
    
    public Book getBookBySearch(String bname) {
    	return bookRepository.getBook(bname);
    }
    
    public List<Book> searchBooks(String title) {
        if (title.length() == 1) {
            
            return bookRepository.findByTitleLikeIgnoreCase(title);
        } else {
  
            String formattedQuery = "%" + title.replaceAll("\\s", "%") + "%";
            return bookRepository.findByTitleStartingWithIgnoreCase(formattedQuery);
        }
    }
    
    public List<Book> getBookByGenre(String genre) {
        if (genre.length() == 1) {      
            return bookRepository.findByGenreLikeIgnoreCase(genre);
        } else {
            String formattedQuery = "%" + genre.replaceAll("\\s", "%") + "%";
            return bookRepository.findByGenreStartingWithIgnoreCase(formattedQuery);
        }
    }
    
    public List<Book> getBookByAuthor(String author) {
        if (author.length() == 1) {    
            return bookRepository.findByAuthorLikeIgnoreCase(author);
        } else {
            String formattedQuery = "%" + author.replaceAll("\\s", "%") + "%";
            return bookRepository.findByAuthorStartingWithIgnoreCase(formattedQuery);
        }
    }
    
   
    
    
    
    


    
  
    
  
}
