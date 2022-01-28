import java.util.HashMap;

public class AccountPool {

    private HashMap<Integer, Account> pool = new HashMap<>();

    public void put(int customerNumber, Account account) {
        pool.put(customerNumber, account); 
    }

    public void remove(int customerNumber) {
        pool.remove(customerNumber); 
    }

    public Account get(int customerNumber) {
        return pool.get(customerNumber); 
    }

    public boolean check(int customerNumber) {
        return pool.containsKey(customerNumber); 
    }
    
}
