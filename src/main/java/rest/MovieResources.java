package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.MovieDTO;
import entities.Movie;
import facades.FacadeExample;
import facades.MovieFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

//Todo Remove or change relevant parts before ACTUAL use
@Path("movie")
public class MovieResources {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final MovieFacade FACADE =  MovieFacade.getMovieFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces("text/plan")
    public String Hello(){return "hello world!";}

    @Path("/{id}")
    @GET
    @Produces("application/json")
    public String getMovieByID(@PathParam("id") Integer id){
   // MovieFacade facade = new MovieFacade();
        MovieDTO mdto = FACADE.getById(id);
        return new Gson().toJson(mdto);

    }

    @Path("/all")
    @GET
    @Produces("applcation/json")
    public String getAllMovies(){
        List<MovieDTO> md = FACADE.getAll();
        return new Gson().toJson(md);
    }

    @Path("/{title}")
    @GET
    @Produces("application/json")
    public String getByTitle(@PathParam("title") String title){
        List<MovieDTO> md = FACADE.getByTitle(title);
        return new Gson().toJson(md);
    }

}
