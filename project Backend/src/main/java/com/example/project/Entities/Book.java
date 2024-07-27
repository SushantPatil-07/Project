package com.example.project.Entities;

import java.util.Base64;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int bookId;

    @Column(name = "isbn")
    private String iSBN;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "price")
    private double price;

    @Column(name = "genre")
    private String genre;
    
    @Column(name = "status")
    private int status = 0;

    @Lob
    @Column(name = "image", columnDefinition = "LONGBLOB")
    private byte[] image;

    public Book() {
        // Default constructor
    }

    public Book(String iSBN, String title, String author, double price, String genre, int status, byte[] image) {
        super();
        this.iSBN = iSBN;
        this.title = title;
        this.author = author;
        this.price = price;
        this.genre = genre;
        this.status = status;
        this.image = image;
    }

    // Getters and setters
    // toString method

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getiSBN() {
        return iSBN;
    }

    public void setiSBN(String iSBN) {
        this.iSBN = iSBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(String base64Image) {
        // Decode the Base64-encoded string into a byte array
        this.image = Base64.getDecoder().decode(base64Image);
    }

}