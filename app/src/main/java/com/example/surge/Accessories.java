package com.example.surge;

public class Accessories {
    private int acs_id;
    private String acs_type;
    private String acs_size;
    private String acs_colour;
    private String acs_price;

    public Accessories(int acs_id, String acs_type, String acs_size, String acs_colour, String acs_price) {
        this.acs_id = acs_id;
        this.acs_type = acs_type;
        this.acs_size = acs_size;
        this.acs_colour = acs_colour;
        this.acs_price = acs_price;
    }

    public int getAcs_id() {
        return acs_id;
    }

    public String getAcs_type() {
        return acs_type;
    }

    public String getAcs_size() {
        return acs_size;
    }

    public String getAcs_colour() {
        return acs_colour;
    }

    public String getAcs_price() {
        return acs_price;
    }
}
