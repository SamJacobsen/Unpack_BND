import java.io.File;

public class PackedFile {

    private int fileSize;
    private int startIndexInFile;
    private File directory;
    private byte[] data;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getFileSize() {
        return fileSize;
    }

    public int getStartIndexInFile() {
        return startIndexInFile;
    }

    public File getDirectory() {
        return directory;
    }

    public void setDirectory(File directory) {
        this.directory = directory;
    }

    public void setFileSize(int fileSize) { this.fileSize = fileSize; }

    public void setStartIndexInFile(int startIndexInFile) { this.startIndexInFile = startIndexInFile; }
}
