// package com.example.demo.controller;
// import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.stereotype.Controller;
// import com.example.demo.model.Movie;
// import com.example.demo.repository.MovieRepository;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.*;

// // @Controller
// @RequestMapping("/movies")
// public class MovieController {

//     @Autowired
//     private MovieRepository movieRepository;

//     @GetMapping("/")
//     public String index(Model model) {
//         model.addAttribute("movies", movieRepository.findAll());
//         return "movies";
//     }

//     @GetMapping("/add")
//     public String showAddForm(Model model) {
//         model.addAttribute("movie", new Movie());
//         return "add_movie";
//     }

//     @PostMapping("/add")
//     public String addMovie(@ModelAttribute("movie") Movie movie) {
//         movieRepository.save(movie);
//         return "redirect:/";
//     }

//     @GetMapping("/update/{id}")
//     public String showUpdateForm(@PathVariable("id") long id, Model model) {
//         Movie movie = movieRepository.findById(id)
//                 .orElseThrow(() -> new IllegalArgumentException("Invalid movie id: " + id));
//         model.addAttribute("movie", movie);
//         return "update_movie";
//     }

//     @PostMapping("/update/{id}")
//     public String updateMovie(@PathVariable("id") long id, @ModelAttribute("movie") Movie movie, Model model) {
//         movie.setId(id);
//         movieRepository.save(movie);
//         return "redirect:/";
//     }

//     @GetMapping("/delete/{id}")
//     public String deleteMovie(@PathVariable("id") long id) {
//         movieRepository.deleteById(id);
//         return "redirect:/";
//     }
// }
package com.example.demo.controller;

import com.example.demo.model.Movie;
import com.example.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public String getAllMovies(Model model) {
        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        model.addAttribute("movie", new Movie());
        return "movies";
    }

    @GetMapping("/edit/{id}")
    public String editMovie(@PathVariable Long id, Model model) {
        Movie movie = movieService.getMovieById(id);
        model.addAttribute("movie", movie);
        return "edit_movie";
    }

    @PostMapping("/edit/{id}")
    public String updateMovie(@PathVariable("id") Long id, @ModelAttribute Movie updatedMovie) {
        movieService.updateMovie(id, updatedMovie);
        return "redirect:/movies";
    }

    @PostMapping
    public String addMovie(@ModelAttribute Movie movie) {
        movieService.addMovie(movie);
        return "redirect:/movies";
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return "redirect:/movies";
    }
}