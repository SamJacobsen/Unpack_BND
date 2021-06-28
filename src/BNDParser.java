import java.io.*;
import java.util.List;
import java.util.Iterator;

public class BNDParser implements Parser {

    @Override
    public Iterator<PackedFile> read(File inputFile) {
        try(BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(inputFile))) {
            BNDHeaderReader headerReader = new BNDHeaderReader(inputStream);
            List<PackedFile> filesMetaData = headerReader.read();
            return new PackedFileIterator(inputFile, filesMetaData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
