import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class BruteForceCaesarSypher {
    private static int key = 1;
    private static int count = 0;
    private static File decryptedFile = new File("/Users/alexmer/IdeaProjects/MyTestProject/src/decryptFileDECRYPTED.txt");
    private static char[] charsAlphabet;


    public static void bruteForce(File encryptedFile, String alphabet) {
        charsAlphabet = alphabet.toCharArray();


        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(encryptedFile));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(decryptedFile))) {
            while (bufferedReader.ready()) {
                String readLine = bufferedReader.readLine();
                while (!isKey(readLine)){
                    key++;
                    count++;
                    if (count > charsAlphabet.length) {
                        throw new RuntimeException("Данным способом расшифровать файл не получилось.");
                    }
                }
                ArrayList<Character> shiftedAlphabet = new ArrayList<>();
                for (char ch : charsAlphabet) {
                    shiftedAlphabet.add(ch);
                }
                Collections.rotate(shiftedAlphabet, key);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < readLine.length(); i++) {
                    int index = shiftedAlphabet.get(readLine.charAt(i));
                    sb.append(charsAlphabet[index]);
                }
                bufferedWriter.write(sb + "\n");
            }
        } catch (IOException e) {
            System.out.println("Файл не найден.");
        }
    }

    private static boolean isKey(String readLine){
        ArrayList<Character> shiftedAlphabet = new ArrayList<>();
        for (char ch : charsAlphabet) {
            shiftedAlphabet.add(ch);
        }
        Collections.rotate(shiftedAlphabet, key);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < readLine.length(); i++) {
            int index = shiftedAlphabet.get(readLine.charAt(i));
            sb.append(charsAlphabet[index]);
        }
        String testString = sb.toString();
        if (testString.endsWith("!") || testString.contains(", ") || testString.contains("ет ")
        || testString.contains("ь ") || testString.contains("ит ")){
            return true;
        } else {
            return false;
        }
    }

//    private static File getDecryptedFile() {
//        String oldFileName = encryptedFile.getAbsolutePath();
//        String newFileName = oldFileName.replace("ENCRYPTED", "DECRYPTED");
//        return new File(newFileName);
//    }
}
