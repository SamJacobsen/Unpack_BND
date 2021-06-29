import org.apache.commons.io.FilenameUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DCXParser implements Parser {

    @Override
    public Iterator<PackedFile> read(File inputFile) {
        try(BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(inputFile))) {
            List<PackedFile> filesMetaData = new ArrayList<>();

            DCXDecompresser decompresser = new DCXDecompresser(inputStream);
            PackedFile metaData = decompresser.read();

            metaData.setDirectory(new File(FilenameUtils.getName(FilenameUtils.removeExtension(inputFile.toString()))));

            filesMetaData.add(metaData);
            return new PackedFileIterator(inputFile, filesMetaData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
