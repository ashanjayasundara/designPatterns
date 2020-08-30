package esad.practice;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author ashan on 2020-08-26
 */
public class MultiFileReader implements Runnable {

    @Override
    public void run() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\ashan\\Desktop\\AH Brotter\\sample.txt"))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                System.out.println(line);
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        String host = "web,webux.ustocktrade.com|weblk,webux.ustocktrade.lk";
        for (String val : host.split("\\|")
        ) {
            String[] data = val.split(",");
            System.out.println(data[0] + " - " + data[1]);

        }
    }
}
