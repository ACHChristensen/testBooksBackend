package facades;

import dtos.BookDTO;
import entities.Book;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class BookFacade {

    private static BookFacade instance;
    private static EntityManagerFactory emf;

    public static BookFacade getBookFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BookFacade();
        }
        return instance;
    }

    public BookDTO addBook(BookDTO bookDTO) {
        Book book = new Book(bookDTO.getIsbn(), bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getPublisher(), bookDTO.getPublishYear());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(book);
            em.find(Book.class, bookDTO.getIsbn());
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new BookDTO(book);
    }

}
