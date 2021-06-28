import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PackedFileWriter {
    public void write(PackedFile file) {
        System.out.println("File Size: " + file.getFileSize());
        System.out.println("Start Index: " + file.getStartIndexInFile());
        System.out.println("Directory: " + file.getDirectory());

        Path path = Paths.get(FilenameUtils.getPath(file.getDirectory().toString()));

        if(!Files.exists(path)) {
            path.toFile().mkdirs();
        }

        String outPutDir = FilenameUtils.getPath(file.getDirectory().toString()) + FilenameUtils.getName(file.getDirectory().toString());

        try(FileOutputStream outputStream = new FileOutputStream(outPutDir)) {
            outputStream.write(file.getData());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private void writeFile(byte[] data, String FileName) {
//        try(FileOutputStream outputStream = new FileOutputStream(outputPath + FileName)) {
//            outputStream.write(data);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
