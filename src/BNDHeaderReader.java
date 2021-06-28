import org.apache.commons.io.FilenameUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

public class BNDHeaderReader {
    private BufferedInputStream inputStream;

    public BNDHeaderReader(BufferedInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public List<PackedFile> read() {
        ArrayList<PackedFile> packedFilesMeta = new ArrayList<>();

        try {
            ByteBuffer chunk = ByteBuffer.wrap(inputStream.readNBytes(32));
            chunk.order(ByteOrder.LITTLE_ENDIAN);

            int numberOfFiles = chunk.getInt(16);
            System.out.println("Number of Files: " + numberOfFiles);

            ByteBuffer metaDataChunk = ByteBuffer.allocate(24);
            metaDataChunk.order(ByteOrder.LITTLE_ENDIAN);
            for (int i = 0; i < numberOfFiles; i++) {
                PackedFile p = seekFileMeta(metaDataChunk);
                packedFilesMeta.add(p);
            }

            for (PackedFile f : packedFilesMeta) {
                String directory = ByteUtils.readNullTerminatedString(inputStream);
                //directory = FilenameUtils.getPath(directory);
                f.setDirectory(new File(directory));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return packedFilesMeta;
    }

    private PackedFile seekFileMeta(ByteBuffer chunk) throws IOException {
        PackedFile localFile = new PackedFile();

        chunk.clear();
        chunk.put(inputStream.readNBytes(chunk.capacity()));

        int size = chunk.getInt(4);
        int index = chunk.getInt(8);
        localFile.setFileSize(size);
        localFile.setStartIndexInFile(index);

        return localFile;
    }

}
