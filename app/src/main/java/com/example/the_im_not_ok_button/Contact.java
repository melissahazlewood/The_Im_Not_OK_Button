package com.example.the_im_not_ok_button;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contact_table")
public class Contact {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String first_name;
    private String last_name;
    private String phone_number;

    public Contact(String first_name, String last_name, String phone_number) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPhone_number() {
        return phone_number;
    }
}
