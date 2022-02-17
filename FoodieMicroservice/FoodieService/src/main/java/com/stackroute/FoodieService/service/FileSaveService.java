package com.stackroute.FoodieService.service;

import com.stackroute.FoodieService.exception.DirectoryAlreadyExistException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface FileSaveService {
    public void init() throws DirectoryAlreadyExistException;

    public String save(MultipartFile file);

    public Resource load(String filename);

    public void deleteAll();

}
