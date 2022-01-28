import java.util.HashMap;

public class DataPool<T> {

    private HashMap<Integer, T> pool = new HashMap<>();

    public void put(int customerNumber, T data) {
        pool.put(customerNumber, data);
    }

    public void remove(int customerNumber) {
        pool.remove(customerNumber); 
    }

    public T get(int customerNumber) {
        return pool.get(customerNumber);
    }

    public boolean check(int customerNumber) {
        if (pool.isEmpty()) {
            return false;
        }
        return pool.containsKey(customerNumber); 
    }
}
