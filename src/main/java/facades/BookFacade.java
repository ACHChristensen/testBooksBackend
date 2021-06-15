package facades;

import dtos.BookDTO;
import entities.Book;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

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
           book = em.find(Book.class, bookDTO.getIsbn());
            
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new BookDTO(book);
    }

    
    
    public List<BookDTO> getBookList() {
        EntityManager em = emf.createEntityManager();
        List<BookDTO> dtos = new ArrayList();
        try{
            em.getTransaction().begin();
            TypedQuery<Book> TQ = em.createQuery("SELECT b FROM Book b", Book.class);
            List<Book> books =  TQ.getResultList();
            for (Book book : books) {
                BookDTO dto = new BookDTO(book);
                dtos.add(dto);
                
            }
            
            
        } finally{
            em.close();
        }
       
        
        return dtos;
    }

}
