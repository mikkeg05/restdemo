/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.MovieDTO;
import dtos.RenameMeDTO;
import entities.RenameMe;
import entities.Movie;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        FacadeExample fe = FacadeExample.getFacadeExample(emf);
        MovieFacade mf = MovieFacade.getMovieFacade(emf);
        List<String> os = new ArrayList<>();
        os.add("Mikkel");
        os.add("Abdi");
        os.add("Nicolai");

        mf.create(new MovieDTO(new Movie ("God film", 1999, os)));
        mf.create(new MovieDTO(new Movie ("Bedre Film", 2011, os)));
        fe.create(new RenameMeDTO(new RenameMe("First 1", "Last 1")));
        fe.create(new RenameMeDTO(new RenameMe("First 2", "Last 2")));
        fe.create(new RenameMeDTO(new RenameMe("First 3", "Last 3")));
    }



    public static void main(String[] args) {
        populate();
    }
}
