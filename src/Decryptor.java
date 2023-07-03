import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Decryptor {

    public static void decryptFileCaesarMethod(File decryptedFile, File encryptedFile, int key, String alphabet) {
        char[] chars = alphabet.toCharArray();
        ArrayList<Character> arrayList = new ArrayList<>();

        for (char ch : chars) {
            arrayList.add(ch);
        }

        Collections.rotate(arrayList, -key);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(encryptedFile));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(decryptedFile))) {
            while (bufferedReader.ready()) {
                String stringRead = bufferedReader.readLine();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < stringRead.length(); i++) {
                    int index = alphabet.indexOf(stringRead.charAt(i));
                    if (index != -1) {
                        sb.append(arrayList.get(alphabet.indexOf(stringRead.charAt(i))));
                    } else {
                        sb.append(stringRead.charAt(i));
                    }
                }
                bufferedWriter.write(sb + "\n");
                System.out.print("\nВ файл записано: " + sb);
            }
        } catch (IOException e) {
            System.out.println("Файл не найден.");
        }
    }
}
