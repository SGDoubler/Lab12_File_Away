import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Dictionary;
import java.util.Hashtable;

public class Main {
    public static void main(String[] args) {
        // Open the file dialog and select a file.
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            // Open the selected file and get the following:
            /*
                •	The name of the file the user chose to process
                •	Number of lines in the file
                •	Number of words in the file
                •	Number of characters in the file
             */
            Dictionary<String,Integer> stats = get_file_stats(selectedFile.getAbsolutePath());
            System.out.println("Lines: " + stats.get("lines").toString());
            System.out.println("Words: " + stats.get("words").toString());
            System.out.println("Characters: " + stats.get("chars").toString());
        }
    }

    public static Dictionary<String,Integer> get_file_stats(String path_to_file) {
        // Declare local vars.
        Dictionary<String, Integer> results = new Hashtable<>();
        Integer lines = 0, words = 0, characters = 0;

        try(BufferedReader br = new BufferedReader(new FileReader(path_to_file))) {
            String line = br.readLine();
            lines++;

            while (line != null) {
                // Get the number of words and add it to the count.
                words = words + line.split("\\s").length;
                characters = characters + line.length();
                // Increment the lines counter.
                lines++;
                line = br.readLine();
            }
        } catch(Exception ignored) {

        }

        results.put("lines",lines);
        results.put("words",words);
        results.put("chars",characters);
        return results;
    }
}