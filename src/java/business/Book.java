/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.text.NumberFormat;

/**
 *
 * @author raefo
 */
public class Book {
    private String bookid;
    private int storeid;
    private int onhand;
    private String title;
    private double price;
    private NumberFormat curr = NumberFormat.getCurrencyInstance();

    public Book() {
        setBookid("");
        setStoreid(0);
        setOnhand(0);
        setTitle("");
        setPrice(0);
    }
    
    /**
     * @return the bookid
     */
    public String getBookid() {
        return bookid;
    }

    /**
     * @param bookid the bookid to set
     */
    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    /**
     * @return the storeid
     */
    public int getStoreid() {
        return storeid;
    }

    /**
     * @param storeid the storeid to set
     */
    public void setStoreid(int storeid) {
        this.storeid = storeid;
    }

    /**
     * @return the onhand
     */
    public int getOnhand() {
        return onhand;
    }

    /**
     * @param onhand the onhand to set
     */
    public void setOnhand(int onhand) {
        this.onhand = onhand;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the price
     */
    public String getPrice() {
        return curr.format(price);
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    public String toString() {
        return title;
    }
}
