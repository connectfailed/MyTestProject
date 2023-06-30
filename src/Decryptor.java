import java.io.*;

public class Decryptor {

    public Decryptor(){
    }

    public static void decryptFileCaesarMethod(File decryptedFile, File encryptedFile, int key, String alphabet) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(encryptedFile));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(decryptedFile))) {
            while (bufferedReader.ready()) {
                String readString = bufferedReader.readLine();
                StringBuilder writeString = new StringBuilder();
                for (int i = 0; i < readString.length(); i++) {
                    if (alphabet.indexOf(readString.charAt(i)) != -1) {
                        writeString.append(alphabet.charAt(alphabet.indexOf(readString.charAt(i) - key)));
                    } else {
                        writeString.append(readString.charAt(i));
                    }
                }
                bufferedWriter.write(writeString + "\n");
                System.out.print("В файл записано: " + writeString);
            }
        } catch (IOException e) {
            System.out.println("Файл не найден.");
        }
    }
}
