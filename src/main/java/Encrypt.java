import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Encrypt {
    public static void main(String[] args) throws Exception {
        FileInputStream inputStreamWord = new FileInputStream("src/main/resources/Word.txt");

        FileReader fileReader = new FileReader("src/main/resources/Text.txt");
        BufferedReader readerText = new BufferedReader(fileReader);

        FileWriter fileWriter = new FileWriter("src/main/resources/TextWithWord.txt");
        BufferedWriter writerText = new BufferedWriter(fileWriter);

        List<String> text = new ArrayList<>();
        String line = readerText.readLine();
        while (line != null) {
            text.add(line);
            line = readerText.readLine();
        }
        readerText.close();

        byte[] bytes = inputStreamWord.readAllBytes();
        StringBuilder bits = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            bits.append(String.format("%8s", Integer.toBinaryString(bytes[i]))
                    .replace(" ", "0"));
        }

        char[] charsBits = bits.toString().toCharArray();
        for (int i = 0; i < charsBits.length; i++) {
            if (charsBits[i] == '1') {
                text.set(i, text.get(i).concat(" "));
            }
        }

        for (String item: text) {
            writerText.write(item.concat("\n"));
        }
        writerText.close();
    }
}