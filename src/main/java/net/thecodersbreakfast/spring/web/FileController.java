package net.thecodersbreakfast.spring.web;

import net.thecodersbreakfast.spring.model.FileInfo;
import net.thecodersbreakfast.spring.service.FileService;
import net.thecodersbreakfast.spring.web.util.Base64PathVariable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/fileinfo/{path}")
    public HttpEntity<FileInfo> fileInfo(@Base64PathVariable("path") String path) {
        return fileService.getFileInfo(path)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

}
