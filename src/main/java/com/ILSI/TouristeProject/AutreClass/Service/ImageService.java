package com.ILSI.TouristeProject.AutreClass.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ImageService {

    @Value("${upload.dir}")
    private String uploadDir;

    public String saveImage(MultipartFile file) throws IOException {
        String fileName =  file.getOriginalFilename();
        Path path = Paths.get(uploadDir);
        Files.createDirectories(path);
        assert fileName != null;
        Files.copy(file.getInputStream(), path.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        return "images/" + fileName;
    }


}

