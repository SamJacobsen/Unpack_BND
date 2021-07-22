import java.util.*;

public class FileTypeChecker {
    Map<String, Parser> types;
    public FileTypeChecker() {
        types = new HashMap<>();
        types.put("bnd", new BNDParser());
        types.put("tpf", new TPFParser());
        types.put("dcx", new DCXParser());
    }

    public Parser check(String extensionIn) {
        final String extension = extensionIn.toLowerCase();
        Parser typeParser;

        // Get parser for input extension
         typeParser = types.entrySet().stream().filter(
                typesEntry -> extension.contains(typesEntry.getKey())
                ).map(e -> e.getValue()).findFirst().orElse(null);

        return typeParser;
    }

}
