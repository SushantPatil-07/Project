package com.example.project.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.project.Entities.Book;
import com.example.project.Entities.DummyRequest;
import com.example.project.Entities.IssuedBook;
import com.example.project.Entities.Librarian;
import com.example.project.Entities.Login;
import com.example.project.Entities.Roles;
import com.example.project.Entities.User;
import com.example.project.POJO.DummyUser;
import com.example.project.Repository.BookRepository;
import com.example.project.Service.BookService;
import com.example.project.Service.IssuedBookService;
import com.example.project.Service.LibrarianService;
import com.example.project.Service.LoginService;
import com.example.project.Service.RoleService;
import com.example.project.Service.UserService;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	 
		@Autowired
		private UserService uservice;
	    
	    @Autowired
	    private  LoginService lservice;
	    
	    @Autowired
	    private RoleService rservice;
	    
	    @Autowired
	    private LibrarianService liservice;
	    
	    @Autowired
	    private BookService bservice;
	    
	    
	    @Autowired
	    private IssuedBookService iservice;
	    
	    
	    @PostMapping("/userRegister")
		public User registerCustomer(@RequestBody DummyUser cr)
		{
	    	System.out.println(cr);
	    	System.out.println("here in user controller");
	    	Roles rollId = rservice.getRole(1);
	    	//System.out.println(cr.getRoll_id());
	    	//System.out.println(cr);
	    	
	    	Login l = lservice.saveLogin(new Login(cr.getEmail(), cr.getPassword(), rollId));
	    	System.out.println(l);
	    	User user_details = uservice.saveUser(new User(cr.getFirstname(), cr.getLastname(), cr.getEmail(), cr.getMobileNo(), cr.getPassword(), cr.getAddress(), l));
	    	
	    	 return user_details;
		}
	    @GetMapping("/viewmembers")
	    public List<User> getMembers(){
	    	return uservice.viewMembers();
	    }
	    
	    @GetMapping("/viewLibrarian")
	    public List<Librarian>viewLibrarian(){
	    	return liservice.viewLibrarian();
	    }
	    
	    @PostMapping("/login")
	    public Login userlogin(@RequestBody Login n)
	    {
	    	Login chk=lservice.getLoginByEmail(n.getEmail(),n.getPassword());
	    	System.out.println(chk);
	    	
	    	return chk;
	    }
	   
	   
	    @GetMapping("/getUserById" )
	    public User getUserByID(@RequestParam int loginId)
	    {
	    	return uservice.getUserById(loginId);
	    }
	    
	    @GetMapping("/deleteLibrarian")
	    public  int deleteById(@RequestParam int id)
	    {
	    	return liservice.deleteLibrarian(id);
	    }
	    
	    @GetMapping("/deleteMember")
	    public  int deleteMemberById(@RequestParam int id)
	    {
	    	return uservice.deleteMember(id);
	    //	return=lservice.deletelogin(id);
	    }
	    
	    @PostMapping("/AddBook")
	    public Book addBook(@RequestParam("image") MultipartFile imageFile,
	                        @RequestParam("iSBN") String iSBN,
	                        @RequestParam("title") String title,
	                        @RequestParam("author") String author,
	                        @RequestParam("price") double price,
	                        @RequestParam(value = "genre", required = false) String genre,
	                        @RequestParam(value = "status", required = false, defaultValue = "0") int status) {
	        try {
	            byte[] imageData = imageFile.getBytes();
	            Book bk = bservice.saveBook(new Book(iSBN, title, author, price, genre, status, imageData));
	            return bk;
	        } catch (IOException e) {
	            e.printStackTrace();
	            throw new RuntimeException("Failed to process image file: " + e.getMessage());
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException("An error occurred: " + e.getMessage());
	        }
	    }




	    
	    
	    @PostMapping("/LibrarianRegister")
		public Librarian LibrarianRegister(@RequestBody DummyUser lr)
		{
	    	System.out.println(lr);
	    	System.out.println("here in user controller");
	    	Roles rollId = rservice.getRole(lr.getRoll_id());
	    	System.out.println(2);
	    	
	    	
	    	Login l = lservice.saveLogin(new Login(lr.getEmail(), lr.getPassword(),rollId));
	    	Librarian tr= liservice.saveLibrarian(new Librarian(lr.getAadhar_id(),lr.getEducational_qualification(),lr.getFirstname(),lr.getLastname(), lr.getAddress(),lr.getMobileNo(),lr.getPassword(),lr.getEmail(),l));
	    	
	    	 return tr;
		}
	    
	    

	    @GetMapping("/AllBooks")
	    public List<Book> getAllBooks() {
	        List<Book> books = bservice.getAllBooks();
	        for (Book book : books) {
	            // Convert the byte array image data to a Base64-encoded string
	            String base64Image = Base64.getEncoder().encodeToString(book.getImage());
	            book.setImage(base64Image);
	        }
	        return books;
	    }


	    
	    @GetMapping("/EditBook")
	    public Book EditBook(@RequestParam int id){
	    	return bservice.findBookById(id);
	    }
	    
	    @PutMapping("/updateDetails")
	    public Book updateBook(@RequestBody Book bk)
	    {
	    	Book br = bservice.updateBDetails(bk);
	    	return br;
	    }
	    
	    
	    
	    @GetMapping("/GetIssuedInformation")
	    public List<IssuedBook>getAllIssuedINformation()
	    {
	    	return iservice.GetAllInfo();
	    }
	    
	   
}
	  
	
	    
	    
	    
	    
	    
	    
	 
	    
	    
	

	    

	


