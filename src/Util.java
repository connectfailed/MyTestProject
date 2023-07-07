import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Util {

    public static File setDecryptedFile(File encryptedFile) {                                           //make new decrypted file method
        String decryptedFileName = "";
        String encryptedFileName = encryptedFile.getPath();
        if (encryptedFileName.contains("ENCRYPTED")) {
            decryptedFileName = encryptedFileName.replace("ENCRYPTED", "DECRYPTED");
        } else {
            encryptedFileName = encryptedFile.getName();
            String path = encryptedFile.getPath();
            String decryptedFilePath = path.replace(encryptedFileName, "");
            decryptedFileName = decryptedFilePath +
                    encryptedFileName.substring(0, encryptedFileName.lastIndexOf(".")) +
                    "DECRYPTED" +
                    encryptedFileName.substring(encryptedFileName.lastIndexOf("."));
        }
        return new File(decryptedFileName);
    }
    public static File getDecryptedFile() {
        File decryptedFile = Path.of("").toFile();
        Scanner scanner = new Scanner(System.in);
        int attempt = 0;
        while (!decryptedFile.isFile()){
            System.out.println("Введите путь к исходному файлу: ");
            attempt++;
            decryptedFile = Path.of(scanner.nextLine()).toFile();
            if (attempt >= 3) {
                throw new RuntimeException("Вы неправильно указали исходный файл. Программа будет завершена.");
            }
        }
        return decryptedFile;
    }

    public static File setEncryptedFile(File decryptedFile) {
        String decryptedFileName = decryptedFile.getName();
        String path = decryptedFile.getPath();
        String encryptedFilePath = path.replace(decryptedFileName, "");
        String encryptedFileName = encryptedFilePath +
                decryptedFileName.substring(0, decryptedFileName.lastIndexOf(".")) +
                "ENCRYPTED" +
                decryptedFileName.substring(decryptedFileName.lastIndexOf("."));
        return new File(encryptedFileName);
    }

    public static File getEncryptedFile() {
        Scanner scanner = new Scanner(System.in);
        int attempt = 0;
        System.out.println("Введите путь к зашифрованному файлу: ");
        File encryptedFile = Paths.get(scanner.nextLine()).toFile();
        while (!(encryptedFile.isFile())){
            System.out.println("Введите правильный путь к зашифрованному файлу: ");
            attempt++;
            encryptedFile = Paths.get(scanner.nextLine()).toFile();
            if (attempt >= 3) {
                throw new RuntimeException("Вы неправильно указали исходный файл. Программа будет завершена.");
            }
        }
        return encryptedFile;
    }

    public static int setKey(String alphabet) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Введите числовой ключ шифрования (целое число от 1 до %d): ", alphabet.length() - 1);
        int key = scanner.nextInt();
        if (key <= 0) {
            System.out.println("Вы ввели число меньше минимально разрешенного. Ключ установлен на минимальное значение!");
            key = 1;
        } else if (key > alphabet.length() - 1) {
            System.out.println("Вы ввели число больше максимально разрешенного. Ключ автоматически установлен в максимальное значение.");
            key = alphabet.length() - 1;
        }
        return key;
    }
}
