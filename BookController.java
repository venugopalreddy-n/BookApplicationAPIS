package com.example.startProject.Controller;

import java.lang.System.Logger;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.startProject.model.Book;


@RestController
public class BookController {
      private HashMap<Integer,Book> bookhash=new HashMap<>();
      private static org.slf4j.Logger logger= LoggerFactory.getLogger(BookController.class);
      //insert Book -post-RequestBody
      //update Book-Put-RequestBody
      //deleteBook-Delete-path variable
      //getBookDetails-Get-pathvariable
      //getAllBooks-Get-Return Boook list
      @PostMapping("/insertBook")
      public ResponseEntity insertBook(@RequestBody Book book)
      {
    	  ((org.slf4j.Logger) logger).info("Book Coming For Insertion {}",book);
    	  if(bookhash.containsKey(book.getId()))
    	  {
    		  ((org.slf4j.Logger) logger).error("Book already Present. ");
    		  return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	  }
    	  bookhash.put(book.getId(), book);
    	  return  new ResponseEntity<>(HttpStatus.CREATED);
    		  
      }
      @PutMapping("updateBook")
      public Book updateBook(@RequestBody Book book)
      {
    	  bookhash.put(book.getId(),book);
    	  return bookhash.get(book.getId());
      }
      @DeleteMapping("/deleteBook/{id}")
      public String deleteBookbyId(@PathVariable("id") int bookId)
      {
    	  bookhash.remove(bookId);
    	  return "Book Deleted Successfully";
      }
      @GetMapping("/book/{bookId}")
      public Book getBookByBookId(@PathVariable("bookId") int bookId)
      {
    	  ((org.slf4j.Logger) logger).info("BookId received is ,{}",bookId);
    	  return bookhash.get(bookId);
      }
      @GetMapping("/books")
      public List<Book> getBooks()
      {
    	  return  bookhash.values().stream().collect(Collectors.toList());
      }
      @PatchMapping("/updateBookDetails/{bookId}")
  	public Book updateBookDetails(@RequestBody Book book, @PathVariable("bookId") int bookId) {
  		bookhash.put(book.getId(), book);
  		return bookhash.get(book.getId());
  	}
      
}
