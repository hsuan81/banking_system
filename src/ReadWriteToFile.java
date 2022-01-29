import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.GsonBuilder;
import java.io.*;

public class ReadWriteToFile<T> {
    private String fileLocation;
	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();;
    private TypeToken<DataPool<T>> dataType;

    public ReadWriteToFile(String location, TypeToken<DataPool<T>> dataType) {
        fileLocation = location;
        this.dataType = dataType;
    }

    public void write(DataPool<T> dataPool) {

        try (FileWriter writer = new FileWriter(fileLocation)) {
            gson.toJson(dataPool, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DataPool<T> read() {
        DataPool<T> dataPool = null;
         
        try (Reader reader = new FileReader(fileLocation)) {

            // Convert JSON File to Java Object
            dataPool = gson.fromJson(reader, dataType.getType());            

        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataPool;
    }

}
