
package dtos;

import entities.Book;


public class BookDTO {

    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private String publishYear;

    public BookDTO(Book book) {
        this.isbn = book.getIsbn()+"";
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.publisher = book.getPublisher();
        this.publishYear = book.getPublishYear()+"";
    }

    public Long getIsbn() {
        return Long.parseLong(isbn);
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn+"";
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublishYear() {
        return Integer.parseInt(publishYear);
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear+"";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
  
}
