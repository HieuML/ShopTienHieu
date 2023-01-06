package com.example.shoptienhieu.service.fileService;

import com.example.shoptienhieu.entities.FileDB;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public interface FileStorageService {


    FileDB store(MultipartFile file) throws IOException;

    FileDB getFile(String id);
    Stream<FileDB> getAllFiles();


}
