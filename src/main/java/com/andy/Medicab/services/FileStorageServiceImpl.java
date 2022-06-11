package com.andy.Medicab.services;

import com.andy.Medicab.dao.IFileInfoDao;
import com.andy.Medicab.model.FileInfo;
import com.andy.Medicab.services.interfaces.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

@Service("filesService")
@Transactional
public class FileStorageServiceImpl implements FileStorageService {
    private final Path root = Paths.get("uploads");
    @Autowired
    private IFileInfoDao infoDao;
    @Override
    public void init() {
        try {
            Files.createDirectory(root);
        }catch (IOException ex){
            throw new RuntimeException("Impossibles d'initialiser le fichier de telechargement");
        }
    }

    @Override
    public long save(MultipartFile file) throws Exception {
        return 0;
    }

    @Override
    public long save(MultipartFile file, Long id) throws Exception {
      String exts = file.getOriginalFilename();
      String fe ="";
      int i = exts.lastIndexOf('.');
        if (i>0)
            fe=exts.substring(i+1);
        else
            throw new Exception("Fichier incorrect");
        try {
            FileInfo fileInfo = new FileInfo();
            if (id==null)
                fileInfo.setId(id);
            fileInfo.setId(id);
            fileInfo.setName(getNewFileName()+"."+fe);
            Files.copy(file.getInputStream(),this.root.resolve(fileInfo.getName()));
            try {
                System.out.println(fileInfo.getName());
                return infoDao.save(fileInfo).getId();
            }catch (Exception ex){
                throw new RuntimeException("Erreur survenue lors de la sauvegarde dans la Base de données");
            }

        }catch (IOException ex){
            throw new RuntimeException("Ne peux pas sauvegarder le fichier. Error: " + ex.getMessage());
        }
    }
    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Impossible de lire le fichier!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Erreur: " + e.getMessage());
        }
    }

    public Resource loadById(long id){
        try {
            FileInfo fileInfo = infoDao.getById(id);
            if (fileInfo !=null){
                return load(fileInfo.getName());
            }else
                throw new Exception("Fichier introuvables");
        }catch (Exception ex){
            throw new RuntimeException("Impossible de lire le fichier!");
        }

    }
    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Impossible de charger les fichiers!");
        }
    }
    private String getNewFileName(){
        String format = "dd_MM_yyyy_HH_mm_ss_SS";
        String format_ = "yyyyMMdd_HHmmss_SS";
        DateTimeFormatter formatter =DateTimeFormatter.ofPattern(format_);
        LocalDateTime now = LocalDateTime.now();
        return "MDCB_"+formatter.format(now);
    }
    public List<FileInfo> findAllInDb() throws IOException {
       List<FileInfo> list= infoDao.findAll();
       for (FileInfo fi:list){
           fi.setUrl(this.load(fi.getName()).getURL().toString());
       }
       return list;
    }
}
