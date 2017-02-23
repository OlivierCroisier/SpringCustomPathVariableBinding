package net.thecodersbreakfast.spring.service;

import net.thecodersbreakfast.spring.model.FileInfo;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class FileService {

    public Optional<FileInfo> getFileInfo(String path) {
        Path filePath = Paths.get(path);
        if (Files.exists(filePath)) {
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(filePath.toString());
            fileInfo.setName(filePath.getFileName().toString());
            fileInfo.setParent(filePath.getParent().toString());
            fileInfo.setDirectory(Files.isDirectory(filePath));
            try {
                fileInfo.setLastModified(Files.getLastModifiedTime(filePath).toMillis());
                fileInfo.setContentType(Files.probeContentType(filePath));
            } catch (IOException e) {
            }
            return Optional.of(fileInfo);
        } else {
            return Optional.empty();
        }
    }

}
