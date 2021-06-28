import java.io.File;
import java.util.Iterator;

public interface Parser {

    public Iterator<PackedFile> read(File inputFile);

}
