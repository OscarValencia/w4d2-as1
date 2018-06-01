package com.valencia.oscar.w3d4_as1;

public class UserItem {
    private String title;
    private String firstName;
    private String lastName;
    private String picture;
    private String email;
    private String phone;
    private String city;

    public UserItem(String title, String firstName, String lastName, String picture, String email, String phone, String city) {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.picture = picture;
        this.email = email;
        this.phone = phone;
        this.city = city;
    }

    public String getTitle() {
        return title;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPicture() {
        return picture;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCity() {
        return city;
    }
}
