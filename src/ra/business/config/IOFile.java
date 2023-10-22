package ra.business.config;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOFile <T>{
    public static final String PRODUCT_PATH = "src\\ra\\business\\data\\products.txt";
    public static final String CATALOG_PATH = "src\\ra\\business\\data\\catalogs.txt";
    public static<T> List<T> readFileByBinary(String path) {
        List<T> list = new ArrayList<>();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(path);
            ois = new ObjectInputStream(fis);
            list = (List<T>) ois.readObject();
        }catch (EOFException e){

        }
        catch (IOException | ClassNotFoundException e) {

        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return list;
    }
    public static <T> void writeToFileBinary(String path, List<T> list){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(path);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
