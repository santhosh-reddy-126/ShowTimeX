package com.movie.ShowTimeX.Repository;

import com.movie.ShowTimeX.Model.Movie;
import com.movie.ShowTimeX.Model.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
}





