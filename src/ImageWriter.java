import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by sethw on 5/2/2017.
 */
public class ImageWriter {

    public static void writeFile(ArrayList<byte[]> encrypted, int numBytes, String type, int mode){

        byte[] encryptedByteArray = getByteArray(encrypted, numBytes, type);
        String curMode = Utilities.getMode(mode);
        String outString = "outFiles/" + Runner.currentFileName + "_" + type + "_" + curMode + "." + Runner.currentFileExtension;
        File out = new File(outString);

        try {
            BitmapEncoder.encodeToBitmap(encryptedByteArray, out);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static byte[] getByteArray(ArrayList<byte[]> input, int numBytes, String type){
        int blockSize;
        if (type == Encryptor.AES) {
            blockSize = 16;
        } else {
            blockSize = 8;
        }
        int totalNumBytes = input.size()*blockSize;
        int curIndex = 0;
        byte[] allBytes = new byte[totalNumBytes];
        for (byte[] curByte : input){
            for (int i = 0; i<blockSize; i++){
                allBytes[curIndex+i] = curByte[i];
            }
            curIndex += blockSize;
        }

        return Arrays.copyOfRange(allBytes, 0, numBytes);
    }
}
