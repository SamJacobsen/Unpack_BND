import java.util.*;
import java.util.logging.Filter;
import java.util.stream.Collectors;

public class FileTypeChecker {
    Map<String, Parser> types;
    public FileTypeChecker() {
        types = new HashMap<>();
        types.put("bnd", new BNDParser());
        types.put("tpf", new TPFParser());
        types.put("dcx", new BNDParser());
    }

    public Parser check(String extensionIn) {
        final String extension = extensionIn.toLowerCase();
        Parser typeParser;

         typeParser = types.entrySet().stream().filter(
                typesEntry -> extension.contains(typesEntry.getKey())
                ).map(e -> e.getValue()).findFirst().orElse(null);


//        for (Map.Entry<String, Parser> entry : types.entrySet()) {
//            String key = entry.getKey();
//            Parser value = entry.getValue();
//            if (extension.contains(key)) {
//                typeParser = value;
//                break;
//            }
//        }
        return typeParser;
    }

}
