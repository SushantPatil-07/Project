package com.example.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.Entities.Book;
import com.example.project.Service.BookService;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

	
	  @Autowired
	    private BookService bservice;
	  
	  @GetMapping("/editBook")
	    public Book EditBook(@RequestParam int id){
	    	return bservice.findBookById(id);
	    }
	  
	  @GetMapping("/getnotverified")
	  public List<Book> getNotV()
	  {
		  return bservice.getNotVerifiedBook();
	  }
	  
	  @GetMapping("/getBookById")
	  public Book BookById(int id)
	  {
		  return bservice.findBookById(id);
	  }
	  
	  @GetMapping("/getBookBySearch")
	  public Book getBookSearch(@RequestParam String bname) {
		  return bservice.getBookBySearch(bname);
	  }
	  
	  @GetMapping("/search")
	    public ResponseEntity<List<Book>> searchBooks(@RequestParam String title) {
	        List<Book> books = bservice.searchBooks(title);
	        return ResponseEntity.ok(books);
	    }
	  
	  @GetMapping("/searchByGenre")
		  public List<Book> BookByGenre(@RequestParam String genre){
		  return bservice.getBookByGenre(genre);
	  }
	  
	  @GetMapping("/searchByAuthor")
	  public ResponseEntity<List<Book>> searchBooksByAuthor(@RequestParam String author) {
	      List<Book> books = bservice.getBookByAuthor(author);
	      return ResponseEntity.ok(books);
	  }

}
