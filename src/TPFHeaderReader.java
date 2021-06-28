import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

public class TPFHeaderReader {

    private BufferedInputStream inputStream;

    public TPFHeaderReader(BufferedInputStream inputStream) { this.inputStream = inputStream; }

    public List<PackedFile> read() {
        ArrayList<PackedFile> packedFilesMeta = new ArrayList<>();

        try {
            ByteBuffer fixedHeader = ByteBuffer.wrap(inputStream.readNBytes(16));
            fixedHeader.order(ByteOrder.LITTLE_ENDIAN);

            int numberOfFiles = fixedHeader.getInt(8);
            System.out.println("Number of Files: " + numberOfFiles);

            ByteBuffer metaDataChunk = ByteBuffer.allocate(20);
            metaDataChunk.order(ByteOrder.LITTLE_ENDIAN);

            for (int i = 0; i < numberOfFiles; i++) {
                PackedFile p = seekFileMeta(metaDataChunk);
                packedFilesMeta.add(p);
            }

            for (PackedFile f : packedFilesMeta) {
                String directory = ByteUtils.readNullTerminatedString(inputStream);
                //directory = FilenameUtils.getPath(directory);
                f.setDirectory(new File(directory + ".dds"));
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

        int index = chunk.getInt(0);
        int size = chunk.getInt(4);
        localFile.setFileSize(size);
        localFile.setStartIndexInFile(index);

        return localFile;
    }
}
