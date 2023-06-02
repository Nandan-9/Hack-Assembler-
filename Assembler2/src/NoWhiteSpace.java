import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class NoWhiteSpace {
    public  static ArrayList<String> reader(String filePath) {
        ArrayList<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter("NoWhiteSpace.asm"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.replaceAll("\\s", "");
                if (!line.isEmpty() && !line.startsWith("/")) {
                    int commentIndex = line.indexOf("/");
                    if (commentIndex != -1) {
                        line = line.substring(0, commentIndex);
                    }
                    result.add(line);
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }




}