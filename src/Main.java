// This program is encrypt/decrypt textfile by Caesar method
// Эта программа шифрует/расшифровывает текст файла по методу Цезаря


import java.io.*;
import java.util.Scanner;

public class Main {
    static File decryptedFile;
    static File encryptedFile;
    private static int key;
    private static final String ALPHABET = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЫЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя.,\":-!?\s";

    public static void main(String[] args) {
        //Window window = new Window(); //visualisation class with swing JFrame
        //window.getjFrame(); //getting custom frame-window

        getDecryptedFile();

        getKey();

        getEncryptedFile();

        Encryptor.encryptFileCaesarMethod(decryptedFile, encryptedFile, key, ALPHABET);

        //Decryptor.decryptFileCaesarMethod(decryptedFile, encryptedFile, key, ALPHABET);

        BruteForceCaesarSypher.bruteForce(encryptedFile, ALPHABET);
    }

    private static void getDecryptedFile() {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        System.out.println("Введите путь к исходному файлу: ");
        while (true){
            String file = scanner.nextLine();
            if (file.equals("")) {
                System.out.println("Введите путь к исходному файлу: ");
                count++;
            } else if (count >= 3) {
                throw new RuntimeException("Указанный Вами файл не найден. Программа завершается.");
            } else {
                decryptedFile = new File(file);
                break;
            }
        }
    }

    private static void getEncryptedFile() {
        String oldFileName = decryptedFile.getName();
        String path = decryptedFile.getPath();
        String newFileNamePath = path.replace(oldFileName, "");
        String newFileName = newFileNamePath +
                oldFileName.substring(0, oldFileName.lastIndexOf(".")) +
                "ENCRYPTED" +
                oldFileName.substring(oldFileName.lastIndexOf("."));
        encryptedFile = new File(newFileName);
    }

    private static void getKey(){
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        int tmp;
        while (count < 3){
            System.out.printf("Ведите числовой ключ от 1 до %d: \n", ALPHABET.length());
            tmp = scanner.nextInt();
            if (tmp <= 0 || tmp > ALPHABET.length()){
                System.out.println("\n Вы ввели неправильное число. попробуйте еще раз.");
                count++;
            } else {
                key = tmp;
                break;
            }
        }
        if (count > 3) {
            throw  new RuntimeException("Вы ввели неправильное число. Если вам не нужен правильный результат, мне - тоже...");
        }
    }
}