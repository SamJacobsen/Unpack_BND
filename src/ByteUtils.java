import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteUtils {

    public static String readNullTerminatedString(BufferedInputStream inputStream) throws IOException {
        StringBuilder directoryChunk = new StringBuilder();
        int b;

        while (true) {
            b = inputStream.read();
            if (b == 0) {
                break;
            }
            directoryChunk.append((char) b);
        }

        return directoryChunk.toString();
    }

    public static String byteArrayToHex(byte[] bytes) {
        String st = new String();
        for (byte b : bytes) {
            st += String.format("%02X", b);
        }
        return st;
    }

    public static String byteArrayToHexFlip(byte[] bytes) {
        String st = new String();
        for (byte b : bytes) {
            st = String.format("%02X", b) + st;
        }
        return st;
    }

    public static int byteChunkToLong(byte[] bytes, int index) {
        return Integer.parseInt(byteArrayToHexFlip(Arrays.copyOfRange(bytes, index, index+4)), 16);
    }
}
