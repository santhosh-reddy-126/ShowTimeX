package com.movie.ShowTimeX.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer theatreId;
    private String theatreName;
    private String theatreLocation;

    @OneToMany(mappedBy = "theatre",cascade = CascadeType.ALL)
    @JsonManagedReference(value="theatre-screens")
    private List<Screen> theatreScreens;

    public Theatre(){};
    public Theatre(String theatreName,String theatreLocation){
        this.theatreName=theatreName;
        this.theatreLocation=theatreLocation;
        this.theatreScreens = new ArrayList<>();
    }

    public Integer getTheatreId(){
        return theatreId;
    }

    public String getTheatreName(){
        return theatreName;
    }

    public String getTheatreLocation(){
        return theatreLocation;
    }

    public List<Screen> getTheatreScreens(){
        return theatreScreens;
    }


    public void setTheatreName(String theatreName){
        this.theatreName=theatreName;
    }

    public void setTheatreLocation(String theatreLocation){
        this.theatreLocation=theatreLocation;
    }

    public void setTheatreScreens(List<Screen> theatreScreens){
        this.theatreScreens=theatreScreens;
    }

    public void addScreen(Screen screen){
        theatreScreens.add(screen);
    }

    public void removeScreen(Integer screenId){
        Optional<Screen> currScreen = theatreScreens.stream().filter((screen)-> screen.getScreenId().equals(screenId)).findFirst();
        if(currScreen.isPresent()){
            theatreScreens.remove(currScreen);
        }

    }

}
