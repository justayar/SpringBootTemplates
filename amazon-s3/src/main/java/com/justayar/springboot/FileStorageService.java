package com.justayar.springboot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.Bucket;
import com.justayar.springboot.exception.FileWriteException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileStorageService {

    private static final String SUFFIX = "/";

    private final AmazonS3ClientService amazonS3ClientService;

    public List<String> getStorageNames() {
        log.info("Getting storage names from Amazon S3");
        return amazonS3ClientService.listBuckets().stream()
                .map(Bucket::getName)
                .collect(Collectors.toList());
    }

    public void createNewStorage(String storageName) {
        log.info("Creating a new storage with name {} on Amazon S3", storageName);
        amazonS3ClientService.createBucket(storageName);
    }

    public void deleteStorage(String storageName) {
        log.info("Deleting storage with name {} from Amazon S3", storageName);
        amazonS3ClientService.deleteBucket(storageName);
    }

    public void createFolder(String storageName, String folderName) {
        log.info("Creating new folder on storage {} with name {} from Amazon S3", storageName, folderName);
        amazonS3ClientService.createFolder(storageName, folderName);
    }

    public void removeFolder(String storageName, String folderName) {
        log.info("Removing folder on storage {} with name {} from Amazon S3", storageName, folderName);
        folderName = folderName.endsWith(SUFFIX) ? folderName : folderName + SUFFIX;
        amazonS3ClientService.removeFolder(storageName, folderName);
    }

    public void addNewFile(String storageName, String folderName, MultipartFile file) {
        log.info("Adding new file to storage {} with name {} at Amazon S3", storageName, file.getOriginalFilename());
        File newFile = new File("src/main/resources/targetFile.tmp");
        try (OutputStream os = new FileOutputStream(newFile)) {
            os.write(file.getBytes());
        } catch (IOException e) {
            throw new FileWriteException("An exception happened when converting multipart file to file");
        }

        String objectKey = Strings.isBlank(folderName) ? file.getOriginalFilename() : folderName + SUFFIX + file.getOriginalFilename();
        amazonS3ClientService.addObjectToBucket(storageName, objectKey, newFile);
    }

    public InputStream getFile(String storageName, String folderName, String fileName) {
        log.info("Getting file from storage {} with name {} from Amazon S3", storageName, fileName);
        String objectKey = Strings.isBlank(folderName) ? fileName : folderName + SUFFIX + fileName;
        return amazonS3ClientService.getObjectFromBucket(storageName, objectKey);
    }

    public void removeFile(String storageName, String folderName, String fileName) {
        log.info("Removing file from storage {} with name {} from Amazon S3", storageName, fileName);
        String objectKey = Strings.isBlank(folderName) ? fileName : folderName + SUFFIX + fileName;
        amazonS3ClientService.removeObjectFromBucket(storageName, objectKey);
    }

    public void removeFiles(String storageName, String folderName, List<String> fileNames) {
        log.info("Removing files from storage {} with names {} from Amazon S3", storageName, String.join(",", fileNames));
        if (!Strings.isBlank(folderName)) {
            fileNames.replaceAll(fileName -> folderName + "/" + fileName);
        }
        amazonS3ClientService.removeObjectsFromBucket(storageName, fileNames);
    }
}
