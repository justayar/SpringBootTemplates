package com.justayar.springboot;

import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(consumes = {MediaType.ALL_VALUE})
public class FileStorageController {

    private final FileStorageService fileStorageService;

    @GetMapping(value="/getStorageNames")
    public ResponseEntity<List<String>> getStorageNames() {
        return ResponseEntity.ok(fileStorageService.getStorageNames());
    }

    @PostMapping(value="/createNewStorage")
    public void createNewStorage(@RequestParam(name="storageName") String storageName) {
         fileStorageService.createNewStorage(storageName);
    }

    @DeleteMapping(value="/deleteStorage")
    public void deleteStorage(@RequestParam(name="storageName") String storageName) {
        fileStorageService.deleteStorage(storageName);
    }

    @PostMapping(value="/createFolder")
    public void createFolder(@RequestParam(name="storageName") String storageName,
                             @RequestParam(name="folderName") String folderName) {
        fileStorageService.createFolder(storageName, folderName);
    }

    @DeleteMapping(value="/removeFolder")
    public void removeFile(@RequestParam(name="storageName") String storageName,
                           @RequestParam(name="folderName", required = false) String folderName) {
        fileStorageService.removeFolder(storageName, folderName);
    }


    @PostMapping(value="/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadFile(@RequestParam(name="storageName") String storageName,
                           @RequestParam(name="folderName", required = false) String folderName,
                           @RequestPart(name="file") MultipartFile file) {
        fileStorageService.addNewFile(storageName, folderName, file);
    }

    @GetMapping(value="/downloadFile")
    public ResponseEntity<Resource> downloadFile(@RequestParam(name="storageName") String storageName,
                                                 @RequestParam(name="folderName", required = false) String folderName,
                                                 @RequestParam(name="fileName") String fileName) {
        return ResponseEntity.ok(new InputStreamResource(fileStorageService.getFile(storageName, folderName, fileName)));
    }

    @DeleteMapping(value="/removeFile")
    public void removeFile(@RequestParam(name="storageName") String storageName,
                           @RequestParam(name="folderName", required = false) String folderName,
                           @RequestParam(name="fileName") String fileName) {
        fileStorageService.removeFile(storageName, folderName, fileName);
    }


    @DeleteMapping(value="/removeFiles")
    public void removeFile(@RequestParam(name="storageName") String storageName,
                           @RequestParam(name="folderName", required = false) String folderName,
                           @RequestParam(name="fileNames") List<String> fileNames) {
        fileStorageService.removeFiles(storageName, folderName, fileNames);
    }
}
