package com.andy.Medicab.controller;

import com.andy.Medicab.model.Account;
import com.andy.Medicab.model.FileInfo;
import com.andy.Medicab.model.ResponseMessage;
import com.andy.Medicab.services.Accountservice;
import com.andy.Medicab.services.FileStorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

import static com.andy.Medicab.controller.Outils.*;

/**
 * @author Ir Andy
 */
@CrossOrigin({BASE_URL})
@RestController
@RequestMapping("/rest/files/")
public class FileController {

    @Autowired
    FileStorageServiceImpl storageService;
    @Autowired
    Accountservice accountservice;

    @Autowired
    private void init(){
        try{
            storageService.init();
            System.out.println("Initialisation effectuée avec success");
        }catch (Exception ex){
            System.out.println("Erreur de l'initialisation + "+ex.getMessage());
        }

    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.save(file);
            message = "Fichier telechargé avec succes" ;
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Impossible de telecharger le fichier: " + e.getMessage() +"!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
    @GetMapping("/allFiles")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FileController.class, "getFile", path.getFileName().toString()).build().toString();
            return new FileInfo(filename, url);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }
    @GetMapping("/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<FileInfo>> getAllFiles() {
        try {
            return new ResponseEntity<List<FileInfo>>(storageService.findAllInDb(),HttpStatus.OK);
        }catch (Exception ex){
            throw new RuntimeException("Impossible d'acceder à la Base de données");
        }
    }
    @PostMapping("/profil/{id_account}")
    public ResponseEntity addUserProfil(@PathVariable Long id_account,@RequestParam("file") MultipartFile file ){
        try {
            Account account = accountservice.findById(id_account);

            storageService.save(file, account.getProfil().getId());

            return buildSuccessMessage("Mise à jour effectué avec success");
        } catch (Exception e) {
            e.printStackTrace();
            return buildErrorMessage(e,"Echec de mise à jour");
        }
    }
}
