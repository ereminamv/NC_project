package maintain;

import ru.UNCedu.phonebook.TestFrame;

import java.io.*;

/**
 * Created by 1345 on 24.02.2016.
 */
public class File_processor {

    private File filename;

    public String read(String newText) {
        StringBuilder sb = new StringBuilder();
        File file = new File(String.valueOf(filename));

        try (BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()))) {
            String s;
            while ((s = in.readLine()) != null) {
                if (s.length() != 0) {
                    sb.append(s);
                    sb.append("\n");
                }
            }
        } catch (IOException ex) {
            System.out.println("File not found");
        }
        return sb.toString();
    }

    public static void write(String fileName, String text) {

        File file = new File(fileName);

        try {

            PrintWriter out = new PrintWriter(file.getAbsoluteFile());

            try {

                out.println(text);
            } finally {

                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(String newText) {
        File file = new File(new TestFrame().filename);
        file.exists();
        StringBuilder sb = new StringBuilder();
        String oldFile = read(new TestFrame().filename);

        sb.append(oldFile);

        sb.append(newText);

        write(new TestFrame().filename, sb.toString());
        System.out.println(file.length());
    }
}