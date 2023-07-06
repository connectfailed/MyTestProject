import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Encryptor {

    public static void encryptFileCaesarMethod(String alphabet){
        File decryptedFile = Util.getDecryptedFile();
        File encryptedFile = Util.setEncryptedFile(decryptedFile);
        int key = Util.setKey(alphabet);
        char[] charsAlphabet = alphabet.toCharArray();
        ArrayList<Character> shiftedAlphabet = new ArrayList<>();
        for (char ch : charsAlphabet) {
            shiftedAlphabet.add(ch);
        }

        Collections.rotate(shiftedAlphabet, key);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(decryptedFile));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(encryptedFile))) {
            while (bufferedReader.ready()) {
                String stringRead = bufferedReader.readLine();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < stringRead.length(); i++) {
                    int index = alphabet.indexOf(stringRead.charAt(i));
                    if (index != -1) {
                        sb.append(shiftedAlphabet.get(index));
                    } else {
                        sb.append(stringRead.charAt(i));
                    }
                }
                bufferedWriter.write(sb + "\n");
            }
        } catch (IOException e) {
            System.out.println("Файл не найден.");
        }
        System.out.println("текст успешно зашифрован.");
    }
}
