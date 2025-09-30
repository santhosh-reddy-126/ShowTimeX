package com.movie.ShowTimeX.Utilities;

public class SeatUtil {
    public static int getSeatNo(int row,int col,int x){
        return (row-1)*x+col;
    }

    public static int getRow(int seat,int x){
        if(seat%x==0){
            return seat/x;
        }else{
            return seat/x+1;
        }
    }

    public static int getCol(int seat,int x){
        if(seat%x==0){
            return x;
        }else{
            return seat%x;
        }
    }
}
