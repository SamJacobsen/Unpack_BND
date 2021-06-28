import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class TPFParser implements Parser {

    public Iterator<PackedFile> read(File inputFile) {
        try(BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(inputFile))) {
            TPFHeaderReader headerReader = new TPFHeaderReader(inputStream);
            List<PackedFile> filesMetaData = headerReader.read();
            return new PackedFileIterator(inputFile, filesMetaData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
