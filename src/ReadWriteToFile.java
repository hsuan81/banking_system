import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.io.*;

public class ReadWriteToFile<T> {
    private String fileLocation;
	private static final Gson gson = new Gson();

    public ReadWriteToFile(String location) {
        fileLocation = location;
    }

    public void write(T data) {
        // File file = new File(fileLocation);

        try (FileWriter writer = new FileWriter(fileLocation)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DataPool<T> read() {
        DataPool<T> dataPool;
        Type dataType = new TypeToken<DataPool<T>>(){}.getType();
         
        try (Reader reader = new FileReader(fileLocation)) {

            // Convert JSON File to Java Object
            dataPool = gson.fromJson(reader, dataType);
			
			// print staff object
            System.out.println(dataPool);
            

        } catch (IOException e) {
            e.printStackTrace();
            dataPool = null;
        }
        return dataPool;
    }

}
