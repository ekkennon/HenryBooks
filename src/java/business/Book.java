
package business;

import java.text.NumberFormat;

/**
 *
 * @author ekk
 */
public class Book {
    private String bookid;
    private int storeid;
    private int onhand;
    private String title;
    private String author;
    private double price;
    private NumberFormat curr = NumberFormat.getCurrencyInstance();

    public Book() {
        bookid = "";
        storeid = 0;
        onhand = 0;
        title = "";
        price = 0;
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
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
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
    
    @Override
    public String toString() {
        return title;
    }
}
