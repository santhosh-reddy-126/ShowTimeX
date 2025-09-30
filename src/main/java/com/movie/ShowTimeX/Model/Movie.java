package com.movie.ShowTimeX.Model;


import jakarta.persistence.*;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;
    private String movieName;



    @Lob
    private String movieUrl;

    private int ticketPrice;
    private String language;
    private int duration;


    public Movie(){

    }

    public Movie(String movieName,String movieUrl,int ticketPrice,String language,int duration){
        this.movieName = movieName;
        this.movieUrl=movieUrl;
        this.ticketPrice = ticketPrice;
        this.language = language;
        this.duration = duration;
    }

    public Integer getMovieId(){
        return movieId;
    }

    public String getMovieName(){
        return movieName;
    }

    public int getTicketPrice(){
        return ticketPrice;
    }

    public String getLanguage(){
        return language;
    }

    public int getDuration(){
        return duration;
    }

    public String getMovieUrl() {
        return movieUrl;
    }

    public void setMovieUrl(String movieUrl) {
        this.movieUrl = movieUrl;
    }

    public void setMovieName(String movieName){
        this.movieName = movieName;
    }

    public void setTicketPrice(int ticketPrice){
        this.ticketPrice=ticketPrice;
    }

    public void setLanguage(String language){
        this.language=language;
    }

    public void setDuration(int duration){
        this.duration = duration;
    }
}
