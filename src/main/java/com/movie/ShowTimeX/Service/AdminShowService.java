    package com.movie.ShowTimeX.Service;


    import com.movie.ShowTimeX.DTO.Response;
    import com.movie.ShowTimeX.Model.*;
    import com.movie.ShowTimeX.Repository.*;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.sql.Time;
    import java.util.ArrayList;
    import java.util.Collections;
    import java.util.List;
    import java.util.Optional;

    @Service
    public class AdminShowService {
        private ShowRepository showRepository;
        private MovieRepository movieRepository;
        private ScreenRepository screenRepository;
        private PersonRepository personRepository;

        @Autowired
        public AdminShowService(ShowRepository showRepository,MovieRepository movieRepository,PersonRepository personRepository,ScreenRepository screenRepository){
            this.showRepository=showRepository;
            this.movieRepository=movieRepository;
            this.screenRepository=screenRepository;
            this.personRepository=personRepository;
        }


        //Get all shows for a movie
        public List<Show> getAllShows(Integer movieId) {
            return showRepository.findByMovie_MovieId(movieId);
        }


        //Get Show by Id
        public Optional<Show> getShow(Integer showId) {
            return showRepository.findById(showId);
        }

        //Add a Show
        public Response addShow(int movieId, int screenId, int adminId, String startTime){
            try {
                Optional<Movie> movieOpt = movieRepository.findById(movieId);
                Optional<Screen> screenOpt = screenRepository.findById(screenId);
                Optional<Admin> adminOpt = personRepository.findById(adminId)
                        .filter(a -> a instanceof Admin)
                        .map(a -> (Admin) a);

                if (movieOpt.isEmpty() || screenOpt.isEmpty() || adminOpt.isEmpty()) {
                    return new Response(false,"Please select valid movie and screen from valid admin account!");
                }

                Show show = new Show();
                show.setMovie(movieOpt.get());
                show.setScreen(screenOpt.get());
                show.setAdmin(adminOpt.get());
                show.setStartTime(Time.valueOf(startTime));

                showRepository.save(show);

                Admin admin = adminOpt.get();
                admin.addShow(show);
                personRepository.save(admin);

                return new Response(true,"Show added successfully!");
            } catch (Exception e) {
                return new Response(false,e.getMessage());
            }
        }




        //Update a Show
        public Response updateShow(Integer showId, Integer movieId, Integer screenId, Integer adminId, String startTime) {
            try {
                Optional<Show> showOpt = showRepository.findById(showId);
                Optional<Movie> movieOpt = movieRepository.findById(movieId);
                Optional<Screen> screenOpt = screenRepository.findById(screenId);
                Optional<Admin> adminOpt = personRepository.findById(adminId)
                        .filter(a -> a instanceof Admin)
                        .map(a -> (Admin) a);

                if (showOpt.isEmpty() || movieOpt.isEmpty() || screenOpt.isEmpty() || adminOpt.isEmpty()) {
                    return new Response(false,"Please select valid movie,show and screen from valid admin account!");
                }

                Show show = showOpt.get();
                show.setMovie(movieOpt.get());
                show.setScreen(screenOpt.get());
                show.setAdmin(adminOpt.get());
                show.setStartTime(Time.valueOf(startTime));

                showRepository.save(show);
                return new Response(true,"Show added successfully!");

            } catch (Exception e) {
                return new Response(false,e.getMessage());
            }
        }

        //Delete a Show
        public Response deleteShow(Integer showId){
            try{
                Optional<Show> showOpt = showRepository.findById(showId);
                if(showOpt.isEmpty()){
                    return new Response(false,"Please select valid show!");
                }

                Show show = showOpt.get();
                Admin admin = show.getAdmin();
                if(admin != null){
                    admin.getShows().remove(show.getShowId());
                }

                showRepository.delete(show);
                return new Response(true,"Show deleted successfully!");
            }catch(Exception e){
                return new Response(false,e.getMessage());
            }
        }


        //get by admin Id
        public List<Show> getShowsByAdmin(Integer adminId) {
            Optional<Admin> adminOpt = personRepository.findById(adminId)
                    .filter(a -> a instanceof Admin)
                    .map(a -> (Admin) a);
            return adminOpt.map(Admin::getShows).orElse(Collections.emptyList());
        }


    }
