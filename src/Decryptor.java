import java.io.*;

public class Decryptor {

    public Decryptor(){
    }

    public static void decryptFileCaesarMethod(File decryptedFile, File encryptedFile, int key, char[] alphabet) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(encryptedFile));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(decryptedFile))) {
            while (bufferedReader.ready()) {
                String readString = bufferedReader.readLine();
                StringBuilder writeString = new StringBuilder();
                for (int i = 0; i < readString.length(); i++) {
                    writeString.append((char)((int)readString.charAt(i) - key));
                }
                bufferedWriter.write(writeString + "\n");
                System.out.println("В файл записано: " + writeString);
            }
        } catch (IOException e) {
            System.out.println("Файл не найден.");
        }
    }
}
