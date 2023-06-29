import java.io.*;

public class Encryptor {

    public Encryptor(){
    }

    public static void encryptFileCaesarMethod(File decryptedFile, File encryptedFile, int key, char[] alphabet){
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(decryptedFile));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(encryptedFile))) {
            while (bufferedReader.ready()) {
                String readString = bufferedReader.readLine();
                StringBuilder writeString = new StringBuilder();
                for (int i = 0; i < readString.length(); i++) {
                    String ch = String.valueOf(readString.charAt(i));
                    if (ch.matches("[А-Яа-я!?., \"\n:-]")) {
                        writeString.append((char)((int)readString.charAt(i) + key));
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
