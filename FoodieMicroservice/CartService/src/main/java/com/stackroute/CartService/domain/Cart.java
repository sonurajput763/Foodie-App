package com.stackroute.CartService.domain;




import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import java.util.Date;

@Entity
public class Cart {

    @Id
    @GeneratedValue
    private int cartID;
    private String email;
    private int cusineID;
    private Date date;

    public Cart(String email, int cusineID) {
        this.email = email;
        this.cusineID = cusineID;
        this.date = new Date();
    }

    public Cart() {
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCusineID() {
        return cusineID;
    }

    public void setCusineID(int cusineID) {
        this.cusineID = cusineID;
    }

}
