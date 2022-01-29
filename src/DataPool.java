import java.util.HashMap;

public class DataPool<T> {

    private HashMap<String, T> pool = new HashMap<>();

    public void put(String customerNumber, T data) {
        pool.put(customerNumber, data);
    }

    public void remove(String customerNumber) {
        pool.remove(customerNumber); 
    }

    public T get(String customerNumber) {
        return pool.get(customerNumber);
    }

    public boolean check(String customerNumber) {
        if (pool.isEmpty()) {
            return false;
        }
        return pool.containsKey(customerNumber); 
    }
}
