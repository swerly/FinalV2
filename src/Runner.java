import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Runner {
    public static String currentFileExtension;
    public static String currentFileName;
    public static int byteLength;

    private static ArrayList<byte[]> encrypted;

    public void run() {
        //setup
        try {
            String inputFileName = "file5.txt";
            Path inputFilePath = Paths.get(".", "testFiles", inputFileName);
            byte[] inputByteArray = Files.readAllBytes(inputFilePath);
            byteLength = inputByteArray.length;
            String[] split = inputFileName.split("\\.");
            currentFileExtension = split[1];
            currentFileName = split[0];
            ImageWriter iw = new ImageWriter();

            String curCipher;

            for (int i = 0; i < 3; i++) {
                curCipher = i == 0 ? Encryptor.DES : (i == 1 ? Encryptor.DES3 : Encryptor.AES);

                System.out.println(curCipher);
                for (int j = 0; j<5; j++){
                    long startTime = System.currentTimeMillis();
                    runMode(curCipher, inputByteArray, j);
                    long endTime = System.currentTimeMillis();
                    long totalTime = endTime-startTime;

                    //iw.writeFile(encrypted, byteLength, curCipher, j);
                    System.out.print(totalTime + "\n");
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void runMode(String curCipher, byte[] input, int modeInt){
        String currentMode = Utilities.getMode(modeInt);
        Encryptor enc = new Encryptor(curCipher, currentMode);
        System.out.print("    " + currentMode + ": ");
        encrypted = enc.runTest(input);
    }

    private void printTime(long millis){

    }
}
