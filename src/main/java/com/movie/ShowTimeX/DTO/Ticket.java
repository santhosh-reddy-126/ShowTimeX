package com.movie.ShowTimeX.DTO;

import com.movie.ShowTimeX.Model.Screen;
import com.movie.ShowTimeX.Model.Show;

public class Ticket {
    private Show show;
    private Screen screen;

    public Ticket(Show show, Screen screen) {
        this.show = show;
        this.screen = screen;
    }

    public Ticket() {
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }
}
