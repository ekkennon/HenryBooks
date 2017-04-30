
package business;

/**
 *
 * @author ekk
 */
public class Store {
    private int storeid;
    private int numEmployees;
    private String storeName;
    private String storeAddress;

    public Store() {
        storeid = 0;
        numEmployees = 0;
        storeName = "";
        storeAddress = "";
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
     * @return the numEmployees
     */
    public int getNumEmployees() {
        return numEmployees;
    }

    /**
     * @param numEmployees the numEmployees to set
     */
    public void setNumEmployees(int numEmployees) {
        this.numEmployees = numEmployees;
    }

    /**
     * @return the storeName
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * @param storeName the storeName to set
     */
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    /**
     * @return the storeAddress
     */
    public String getStoreAddress() {
        return storeAddress;
    }

    /**
     * @param storeAddress the storeAddress to set
     */
    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }
}
