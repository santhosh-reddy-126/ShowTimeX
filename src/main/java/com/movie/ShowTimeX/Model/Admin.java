    package com.movie.ShowTimeX.Model;

    import com.fasterxml.jackson.annotation.JsonManagedReference;
    import jakarta.persistence.*;

    import java.util.ArrayList;
    import java.util.Collections;
    import java.util.List;

    @Entity
    public class Admin extends Person {

        //admin stores Shows, each show has admin ID(Who create it)
        @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true)
        @JsonManagedReference(value="admin-shows")
        private List<Show> shows = new ArrayList<>();



        public Admin() {}

        public Admin(String personName, String username, String password) {
            super(personName, username, password);
        }


        public List<Show> getShows() {
            return Collections.unmodifiableList(shows);
        }

        public void addShow(Show show) {
            shows.add(show);
            show.setAdmin(this);
        }

        public void removeShow(Show show) {
            shows.remove(show);
            show.setAdmin(null);
        }
    }
