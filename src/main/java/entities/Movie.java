package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "movie")
@Entity
@NamedQueries({
      @NamedQuery(name = "Movie.deleteAllRows", query = "DELETE from Movie"),
      @NamedQuery(name = "Movie.getAll", query = "Select m from Movie m"),
      @NamedQuery(name = "movie.getByTitle", query = "Select m from Movie m where m.title like :title")
})
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String title;
    private int year;

    @ElementCollection
            @CollectionTable(
                    name="actor", joinColumns=@JoinColumn(name="id",referencedColumnName = "id")
            )
    @Column(name ="actor")
        List<String> actors = new ArrayList<>();

    public Movie() {
    }

    public Movie(String title, int year, List<String> actors) {
        this.title = title;
        this.year = year;
        this.actors = actors;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }
}