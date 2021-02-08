import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Decodonator {
    public static void main(String[] args) throws Exception {
        FileReader fileReader = new FileReader("src/main/resources/TextWithWord.txt");
        BufferedReader readerTextWithWord = new BufferedReader(fileReader);

        List<String> TrueText = new ArrayList<>();
        String line = readerTextWithWord.readLine();
        while (line != null) {
            TrueText.add(line);
            line = readerTextWithWord.readLine();
        }

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

        char[] bitsVar = stringBits.delete(stringBits.length() - 8, stringBits.length())
                .toString()
                .toCharArray();

        byte[] bytesVar = new byte[bitsVar.length / 8];
        int byteFromBits = 0;
        for (int i = 0, deg = 7; i < bitsVar.length; i++) {
            byteFromBits += Math.pow(2, deg) * Character.getNumericValue(bitsVar[i]);

            if (deg == 0) {
                deg = 7;
                bytesVar[i / 8] = (byte)byteFromBits;
                byteFromBits = 0;
            }
            else {
                deg--;
            }
        }

        String FinalWord = new String(bytesVar);
        System.out.println(FinalWord);
    }
}