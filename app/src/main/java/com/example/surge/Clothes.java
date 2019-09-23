package com.example.surge;


public class Clothes {

    private int clothes_id;
    private  String clothType;
    private  String clothSize;
    private  String clothColour;
    private  String clothPrice;
    private byte[] image;

    public Clothes(int clothes_id, String clothType, String clothSize, String clothColour, String clothPrice, byte[] image){

        this.clothes_id = clothes_id;
        this.clothType = clothType;
        this.clothSize = clothSize;
        this.clothColour = clothColour;
        this.clothPrice = clothPrice;
        this.image = image;
    }

    public int getclothes_id() {
        return clothes_id;
    }

    public  String getclothType() {
        return clothType;
    }


    public  String getclothSize() {
        return clothSize;
    }

    public  String getclothColour() {
        return clothColour;
    }

    public  String getclothPrice() {
        return clothPrice;
    }

    public byte[] getimage(){return image;}

}