import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class ParserExecutor {
    ConcurrentHashMap<File, Parser> parsersThreaded;

    public ParserExecutor(ConcurrentHashMap<File, Parser> parsers) {
        parsersThreaded = parsers;
    }


    public void run() {
        PackedFileWriter writer = new PackedFileWriter();
        parsersThreaded.entrySet().parallelStream().forEach(entry -> {
            Parser fileParser = entry.getValue();
            File inputFile = entry.getKey();

            Iterator<PackedFile> iter = fileParser.read(inputFile);
            while (iter.hasNext()) {
                writer.write(iter.next());
            }
        });

    }

}
