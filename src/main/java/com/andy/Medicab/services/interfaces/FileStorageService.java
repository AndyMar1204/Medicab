package com.andy.Medicab.services.interfaces;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileStorageService{
    public void init();
    public long save(MultipartFile file) throws Exception;

    long save(MultipartFile file, Long id) throws Exception;

    public Resource load(String filename);
    public void deleteAll();
    public Stream<Path> loadAll();
}
