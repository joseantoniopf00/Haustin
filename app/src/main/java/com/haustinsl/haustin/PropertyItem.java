package com.haustinsl.haustin;

public class PropertyItem {
    private String price;
    private String city;
    private String rooms;

    public PropertyItem(String price, String city, String rooms) {
        this.price = price;
        this.city = city;
        this.rooms = rooms;
    }

    public String getPrice() { return price; }
    public String getCity() { return city; }
    public String getRooms() { return rooms; }
}