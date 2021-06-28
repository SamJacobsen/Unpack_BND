import org.apache.commons.io.FilenameUtils;
import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

    public static void main(String args[]) {
        FileTypeChecker fileChecker = new FileTypeChecker();
        ConcurrentHashMap<File ,Parser> p = new ConcurrentHashMap<>();


        for (String arg : args) {
            p.put(new File(arg), fileChecker.check(FilenameUtils.getExtension(arg)));
        }

        ParserExecutor executor = new ParserExecutor(p);
        executor.run();
    }
}
