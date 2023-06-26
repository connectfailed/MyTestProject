// This program is encrypt/decrypt textfile by Caesar method
// Эта программа шифрует/расшифровывает текст файла по методу Цезаря


import java.io.*;
import java.util.Scanner;

public class Main {
    private static File decryptedFile;
    private static File encryptedFile;
    private static int key;
    public static void main(String[] args) {
        Window window = new Window(); //visualisation class with swing JFrame
        window.getjFrame(); //getting custom frame-window

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите путь к исходному файлу: ");
        decryptedFile = new File(scanner.nextLine());
        System.out.println("Введите числовой ключ шифрования: ");
        key = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Введите путь к зашифрованному файлу: ");
        encryptedFile = new File(scanner.nextLine());

        //encryptFileCaesarMethod(decryptedFile, encryptedFile, key);

        //decryptFileCaesarMethod(decryptedFile, encryptedFile, key);

    }

    private static void decryptFileCaesarMethod(File decryptedFile, File encryptedFile, int key) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(encryptedFile));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(decryptedFile))) {
            while (bufferedReader.ready()) {
                String readString = bufferedReader.readLine();
                StringBuilder writeString = new StringBuilder();
                for (int i = 0; i < readString.length(); i++) {
                    writeString.append((char)((int)readString.charAt(i) - key));
                }
                bufferedWriter.write(writeString.toString() + "\n");
                System.out.println("В файл записано: " + writeString);
            }
        } catch (IOException e) {
            System.out.println("Файл не найден.");
        }
    }

    private static void encryptFileCaesarMethod(File decryptedFile, File encryptedFile, int key){
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
                bufferedWriter.write(writeString.toString() + "\n");
                System.out.print("В файл записано: " + writeString);
            }
        } catch (IOException e) {
            System.out.println("Файл не найден.");
        }
    }




}