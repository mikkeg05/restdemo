package facades;

import dtos.MovieDTO;
import dtos.RenameMeDTO;
import entities.Movie;
import entities.RenameMe;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MovieFacade {

    private static MovieFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private MovieFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MovieFacade getMovieFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public MovieDTO create(MovieDTO m){
        Movie mv =  new Movie(m.getTitle(), m.getYear(), m.getActors());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(mv);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new MovieDTO(mv);
    }


    public MovieDTO getById(int id){
        EntityManager em = emf.createEntityManager();
        try{
            return new MovieDTO(em.find(Movie.class, id));

        }finally{
            em.close();
        }}


    @SuppressWarnings("unchecked")
    public List<MovieDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Movie> query = em.createQuery("SELECT m FROM Movie m", Movie.class);
            List<Movie> mvList = query.getResultList();
            return (List<MovieDTO>) (List<?>) mvList;
        } finally
        {
            em.close();
        }}

    @SuppressWarnings("unchecked")
    public List<MovieDTO> getByTitle(String title){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Movie> query = em.createQuery("Select m from Movie m where m.title = :title", Movie.class);
            query.setParameter("title", title);
            List<Movie> mvList = query.getResultList();
            return(List<MovieDTO>)(List<?>)mvList;
        } finally {
            em.close();
        }}}
