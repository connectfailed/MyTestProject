import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class BruteForceCaesarSypher {

    private static char[] charsAlphabet;
    private static int key = 1;
    private static int concurrence;
    private static int attempt;


    public static void bruteForce(String alphabet) {
        charsAlphabet = alphabet.toCharArray();
        File encryptedFile = Util.getEncryptedFile();
        File decryptedFile = Util.setDecryptedFile(encryptedFile);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(encryptedFile))){  //trying to get right key
            while (bufferedReader.ready()) {
                String readLines = bufferedReader.lines().collect(Collectors.joining());
                while (!isKey(readLines)) {
                    key++;
                    attempt++;
                    if (attempt > charsAlphabet.length*2) {
                        throw new RuntimeException("Данным способом расшифровать файл не получилось.");
                    }
                }
            }
        } catch (IOException e){
            System.out.println("Файл не найден.");
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(encryptedFile));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(decryptedFile))) {
            while (bufferedReader.ready()) {
                String readLine = bufferedReader.readLine();
                ArrayList<Character> shiftedAlphabet = new ArrayList<>();
                for (char ch : charsAlphabet) {
                    shiftedAlphabet.add(ch);
                }
                Collections.rotate(shiftedAlphabet, key);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < readLine.length(); i++) {
                    int index = shiftedAlphabet.indexOf(readLine.charAt(i));
                    if (index != -1) {
                        sb.append(charsAlphabet[index]);
                    } else {
                        sb.append(readLine.charAt(i));
                    }
                }
                bufferedWriter.write(sb + "\n");
            }
        } catch (IOException e) {
            System.out.println("Файл не найден.");
        }
        System.out.printf("Файл %s успешно расшифрован.", encryptedFile);
    }

    private static boolean isKey(String readLine){                          //checking key method
        ArrayList<Character> shiftedAlphabet = new ArrayList<>();
        concurrence = 0;
        for (char ch : charsAlphabet) {
            shiftedAlphabet.add(ch);
        }
        Collections.rotate(shiftedAlphabet, key);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < readLine.length(); i++) {
            int index = shiftedAlphabet.indexOf(readLine.charAt(i));
            if (index != -1) {
                sb.append(charsAlphabet[index]);
            } else sb.append(readLine.charAt(i));
        }
        String testString = sb.toString();
        if (testString.endsWith("!") && testString.contains("ет ") || testString.contains("ит ")) {
            concurrence+=50;
        }
        if (testString.contains(", ") && testString.contains("ь ") && (testString.contains("ет ") || testString.contains("ит "))) {
            concurrence+=50;
        }
        if (testString.contains("ь ")) {
            concurrence++;
        }
        if (testString.contains("что") || testString.contains("кто") || testString.contains("без") || testString.contains("для") || testString.contains("над")) {
            concurrence+=50;
        }
        return concurrence >= 50;
    }
}
