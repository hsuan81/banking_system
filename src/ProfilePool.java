import java.util.HashMap;

public class ProfilePool {
    private HashMap<Integer, Profile> pool = new HashMap<>();

    public void put(int customerNumber, Profile profile) {
        pool.put(customerNumber, profile); 
    }

    public void remove(int customerNumber) {
        pool.remove(customerNumber); 
    }

    public Profile get(int customerNumber) {
        return pool.get(customerNumber); 
    }

    public boolean check(int customerNumber) {
        return pool.containsKey(customerNumber); 
    }


}
