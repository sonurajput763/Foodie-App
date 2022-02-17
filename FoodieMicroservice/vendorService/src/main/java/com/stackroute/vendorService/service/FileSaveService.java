package com.stackroute.vendorService.service;

import com.stackroute.vendorService.exception.DirectoryAlreadyExistException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileSaveService {
    public void init() throws DirectoryAlreadyExistException;

    public String save(MultipartFile file);

    public Resource load(String filename);

    public void deleteAll();
}
