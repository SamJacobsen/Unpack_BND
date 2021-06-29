import java.io.*;
import java.util.Iterator;
import java.util.List;

public class PackedFileIterator implements Iterator<PackedFile> {
    private RandomAccessFile randomAccessFile;
    private Iterator<PackedFile> filesMetaDataIterator;

    public PackedFileIterator(File inputFile, List<PackedFile> filesMetaData) throws FileNotFoundException {
        this.randomAccessFile = new RandomAccessFile(inputFile, "r");
        this.filesMetaDataIterator = filesMetaData.iterator();
    }

    @Override
    public boolean hasNext() {
        return filesMetaDataIterator.hasNext();
    }

    @Override
    public PackedFile next() {
        PackedFile packedFile = this.filesMetaDataIterator.next();
        if(packedFile.getData() == null) {
            try {
                byte[] data = new byte[packedFile.getFileSize()];
                randomAccessFile.seek(Long.valueOf(packedFile.getStartIndexInFile()));
                randomAccessFile.readFully(data);
                packedFile.setData(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return packedFile;
    }
}
