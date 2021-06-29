import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.InflaterInputStream;

public class DCXDecompresser {

    private BufferedInputStream inputStream;

    public DCXDecompresser(BufferedInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public PackedFile read() {
        PackedFile packedFilesMeta = new PackedFile();

        try {
            inputStream.readNBytes(76);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(InflaterInputStream iis = new InflaterInputStream(new ByteArrayInputStream(inputStream.readAllBytes()))) {
            packedFilesMeta.setData(iis.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return packedFilesMeta;
    }
}
