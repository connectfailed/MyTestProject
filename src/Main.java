// This program is encrypt/decrypt textfile by Caesar method
// Эта программа шифрует/расшифровывает текст файла по методу Цезаря
// Author: Alexey (pro_bell) pro_bell@mail.ru
// Version 0.1


import java.io.*;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {

    private static final String ALPHABET = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЫЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя.,\":-!?\s";

    public static void main(String[] args) {

        System.out.println("Выберите режим программы (введите число):\n" +
                "1 - Зашифровать файл ключом\n" +
                "2 - Расшифровать файл ключом\n" +
                "3 - Расшифровать файл перебором ключа");

        Scanner scanner = new Scanner(System.in);
        int pointMenu = scanner.nextInt();
        switch (pointMenu){
            case 1 -> Encryptor.encryptFileCaesarMethod(ALPHABET);
            case 2 -> Decryptor.decryptFileCaesarMethod(ALPHABET);
            case 3 -> BruteForceCaesarSypher.bruteForce(ALPHABET);
            default -> System.out.println("Нет такого пункта меню.");
        }
    }
}
