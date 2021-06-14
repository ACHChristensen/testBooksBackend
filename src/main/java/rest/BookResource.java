package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nimbusds.jose.JOSEException;
import dtos.BookDTO;
import entities.Book;
import entities.User;
import errorhandling.API_Exception;
import errorhandling.GenericExceptionMapper;
import facades.BookFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import utils.EMF_Creator;

@Path("book")
public class BookResource {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create(); 
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static BookFacade facade = BookFacade.getBookFacade(EMF);
   
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("add")
    public String addbook(String jsonString) {
        BookDTO book = GSON.fromJson(jsonString, BookDTO.class );
        BookDTO bookAdded = facade.addBook(book);
        return GSON.toJson(bookAdded);
    }

}
