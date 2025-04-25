package com.payu.CatalogueWebUI.dto;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class BookDto {
    private Long id;
    private String name;
    private String isbnNumber;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private LocalDate publishDate;

    private Double price;
    private String bookType; // Use String for simplicity with enums


    public BookDto() {

    }

    public BookDto(Long id, String name, String isbnNumber, LocalDate publishDate, Double price, String bookType) {
        this.id = id;
        this.name = name;
        this.isbnNumber = isbnNumber;
        this.publishDate = publishDate;
        this.price = price;
        this.bookType = bookType;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }

    public void setIsbnNumber(String isbnNumber) {
        this.isbnNumber = isbnNumber;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isbnNumber='" + isbnNumber + '\'' +
                ", publishDate=" + publishDate +
                ", price=" + price +
                ", bookType='" + bookType + '\'' +
                '}';
    }
}
