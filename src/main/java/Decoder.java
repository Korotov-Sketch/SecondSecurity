import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
//
public class Decoder {
    public static void main(String[] args) throws Exception {
        FileReader fileReader = new FileReader("src/main/resources/TextWithWord.txt");
        BufferedReader readerTextWithWord = new BufferedReader(fileReader);

        List<String> TrueText = new ArrayList<>();
        String line = readerTextWithWord.readLine();
        while (line != null) {
            TrueText.add(line);
            line = readerTextWithWord.readLine();
        }
        readerTextWithWord.close();

        StringBuilder stringBits = new StringBuilder();
        for (int i = 0, j = 0; i < TrueText.size() && (j < 8 || stringBits.length() % 8 != 0); i++) {
            if (TrueText.get(i).endsWith(" ")) {
                j = 0;
                stringBits.append("1");
            }
            else {
                j++;
                stringBits.append("0");
            }
        }

        char[] bits = stringBits.delete(stringBits.length() - 8, stringBits.length())
                .toString()
                .toCharArray();

        byte[] bytes = new byte[bits.length / 8];
        int byteFromBits = 0;
        for (int i = 0, degree = 7; i < bits.length; i++) {
            byteFromBits += Math.pow(2, degree) * Character.getNumericValue(bits[i]);

            if (degree == 0) {
                degree = 7;
                bytes[i / 8] = (byte)byteFromBits;
                byteFromBits = 0;
            }
            else {
                degree--;
            }
        }

        String word = new String(bytes);
        System.out.println(word);
    }
}