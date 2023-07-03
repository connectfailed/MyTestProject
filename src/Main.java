// This program is encrypt/decrypt textfile by Caesar method
// Эта программа шифрует/расшифровывает текст файла по методу Цезаря


import java.io.*;
import java.util.Scanner;

public class Main {
    private static File decryptedFile;
    private static File encryptedFile;
    private static int key = 0;
    private static final String ALPHABET = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЫЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя.,\":-!?\s";

    public static void main(String[] args) {
        //Window window = new Window(); //visualisation class with swing JFrame
        //window.getjFrame(); //getting custom frame-window

        decryptedFile = getDecryptedFile();

        key = getKey();

        encryptedFile = getEncryptedFile();

        Encryptor.encryptFileCaesarMethod(decryptedFile, encryptedFile, key, ALPHABET);

        Decryptor.decryptFileCaesarMethod(decryptedFile, encryptedFile, key, ALPHABET);

        BruteForceCaesarSypher.bruteForce(encryptedFile, ALPHABET);
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
        String path = decryptedFile.getAbsolutePath();
        String newFileNamePath = path.replace(oldFileName, "");
        String newFileName = newFileNamePath +
                oldFileName.substring(0, oldFileName.lastIndexOf(".")) +
                "ENCRYPTED" +
                oldFileName.substring(oldFileName.lastIndexOf("."));
        return new File(newFileName);
    }

    private static int getKey(){
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        int keySize = ALPHABET.length();
        while (true) {
            System.out.printf("Введите ключ шифрования (число от 1 до %d): ", keySize);
            key = scanner.nextInt();
            if (count >= 3) {
                throw new RuntimeException("Вы ввели неверный ключ. Программа будет завершена.");      //break program after 3 wrong try
            } else if (key <= 0 || key > keySize) {
                count++;
                System.out.println();
            } else break;
        }
        return key;
    }
}