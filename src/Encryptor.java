import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Encryptor {

    public static void encryptFileCaesarMethod(File decryptedFile, File encryptedFile, int key, String alphabet){
        char[] charsAlphabet = alphabet.toCharArray();
        ArrayList<Character> arrayList = new ArrayList<>();

        for (char ch : charsAlphabet) {
            arrayList.add(ch);
        }

        Collections.rotate(arrayList, key);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(decryptedFile));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(encryptedFile))) {
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
                System.out.print("В файл записано: " + sb);
            }
        } catch (IOException e) {
            System.out.println("Файл не найден.");
        }
    }
}
