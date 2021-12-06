package writer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CsvWriter implements Writer{
    @Override
    public void write(String path, StringBuilder data) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        writer.write(data.toString());
        writer.flush();
        writer.close();
        System.out.println("Output file generated!");
    }
}
