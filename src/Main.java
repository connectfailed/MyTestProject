// This program is encrypt/decrypt textfile by Caesar method
// Эта программа шифрует/расшифровывает текст файла по методу Цезаря


import java.io.*;
import java.util.Scanner;

public class Main {
    private static File decryptedFile;
    private static File encryptedFile;
    private static int key;
    private static final String ALPHABET = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЫЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя.,\":-!?\s";

    public static void main(String[] args) {
        //Window window = new Window(); //visualisation class with swing JFrame
        //window.getjFrame(); //getting custom frame-window

        decryptedFile = getDecryptedFile();

        key = getKey();

        encryptedFile = getEncryptedFile();

        Encryptor.encryptFileCaesarMethod(decryptedFile, encryptedFile, key, ALPHABET);

        Decryptor.decryptFileCaesarMethod(decryptedFile, encryptedFile, key, ALPHABET);
    }

    private static File getDecryptedFile() {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        System.out.println("Введите путь к исходному файлу: ");
        while (true){
            String file = scanner.nextLine();
            if (file.equals("")) {
                System.out.println("Введите путь к исходному файлу: ");
                count++;
            } else if (count > 3) {
                throw new RuntimeException("Указанный Вами файл не найден. Программа завершается.");
            } else {
                decryptedFile = new File(file);
                break;
            }
        }
        return decryptedFile;
    }

    private static File getEncryptedFile() {
        String oldFileName = decryptedFile.getName();
        String path = decryptedFile.getPath();
        String newFileNamePath = path.substring(0, path.lastIndexOf("\\") + 1);
        String newFileName = newFileNamePath +
                oldFileName.substring(0, oldFileName.lastIndexOf(".")) +
                "ENCRYPTED" +
                oldFileName.substring(oldFileName.lastIndexOf("."));
        return new File(newFileName);
    }

    private static int getKey(){
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        while (true) {
            System.out.println("Введите ключ шифрования (число больше 0): ");
            key = scanner.nextInt();
            if (key <= 0) {
                System.out.println("Введите числовой ключ больше нуля.");   //check key by zero
                count++;
            } else if (count > 3) {
                throw new RuntimeException("Вы ввели неверный ключ.");      //break program after 3 wrong try
            } else break;
        }
        if (key > ALPHABET.length()) {                                        //trim key to length of alphabet (looping)
            key = key % ALPHABET.length();
        }
        return key;
    }
}