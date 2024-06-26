package com.example.demo.service;

import com.example.demo.model.Movie;
import com.example.demo.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        return movie.orElse(null);
    }

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Long id, Movie updatedMovie) {
        // private int releaseYear;
        // private String genre;
        // private String director;
        // private double averageRating;
        Movie existingMovie = getMovieById(id);
        if (existingMovie != null) {
            existingMovie.setTitle(updatedMovie.getTitle());
            existingMovie.setGenre(updatedMovie.getGenre());
            existingMovie.setReleaseYear(updatedMovie.getReleaseYear());
            existingMovie.setDirector(updatedMovie.getDirector());
            existingMovie.setAverageRating((updatedMovie.getAverageRating()+existingMovie.getAverageRating())/2);
            return movieRepository.save(existingMovie);
        }
        return null;
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}
