package com.example.surge;


public class Clothes {

    private int clothes_id;
    private String clothType;
    private String clothSize;
    private String clothColour;
    private String clothPrice;

    public Clothes(int clothes_id, String clothType, String clothSize, String clothColour, String clothPrice){

        this.clothes_id = clothes_id;
        this.clothType = clothType;
        this.clothSize = clothSize;
        this.clothColour = clothColour;
        this.clothPrice = clothPrice;
    }

    public int getclothes_id() {
        return clothes_id;
    }

    public String getclothType() {
        return clothType;
    }


    public String getclothSize() {
        return clothSize;
    }

    public String getclothColour() {
        return clothColour;
    }

    public String getclothPrice() {
        return clothPrice;
    }

}