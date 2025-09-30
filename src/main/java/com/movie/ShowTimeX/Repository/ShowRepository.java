package com.movie.ShowTimeX.Repository;

import com.movie.ShowTimeX.Model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show,Integer> {

    List<Show> findByMovie_MovieId(Integer movieId);

}
