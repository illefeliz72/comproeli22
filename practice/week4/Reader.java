package week4;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
    public static void main(String[] args) {
        try(FileReader fr = new FileReader("info.txt")){
            int i;
            while((i = fr.read()) != -1){
                System.out.println((char)i);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}