/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Movie_T3;

import Movie_T3.*;
import java.io.Serializable;

/**
 *
 * @author 221477934
 */
public class Movie implements Serializable {
   private String movieTitle;
 private String movieDirector;
 private String movieGenre;

    public Movie(String movieTitle, String movieDirector, String movieGenre) {
        this.movieTitle = movieTitle;
        this.movieDirector = movieDirector;
        this.movieGenre = movieGenre;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    @Override
    public String toString() {
        return "Movie{" + "movieTitle=" + movieTitle + ", movieDirector=" + movieDirector + ", movieGenre=" + movieGenre + '}';
    }
 
 

}
