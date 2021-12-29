package com.justayar.springboot;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.justayar.springboot.exception.NotExistBucketException;

@Service
public class AmazonS3ClientService implements InitializingBean {

    private static final String SUFFIX = "/";
    private static final Logger LOGGER = Logger.getLogger(AmazonS3ClientService.class.getName());

    @Value("${s3.region}")
    private String region;

    @Value("${s3.accessKey}")
    private String accessKey;

    @Value("${s3.secretKey}")
    private String secretKey;

    private AmazonS3 amazonS3Client;

    @Override
    public void afterPropertiesSet() {
        AWSCredentials credentials = new BasicAWSCredentials(
                accessKey,
                secretKey);
        amazonS3Client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.fromName(region))
                .build();
    }

    public void createBucket(String bucketName) {
        if(amazonS3Client.doesBucketExistV2(bucketName)) {
            LOGGER.log(Level.SEVERE, "Bucket with name {0} is already exist", bucketName);
            return;
        }

        amazonS3Client.createBucket(bucketName);
    }

    public void deleteBucket(String bucketName) {
        if(!amazonS3Client.doesBucketExistV2(bucketName)) {
            LOGGER.log(Level.SEVERE, "Bucket with name {0} does not exist", bucketName);
            throw new NotExistBucketException("Bucket with name "+ bucketName +"does not exist");
        }

        amazonS3Client.deleteBucket(bucketName);
    }

    public List<Bucket> listBuckets() {
        return amazonS3Client.listBuckets();
    }

    public void createFolder(String bucketName, String folderName) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(0);
        InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,
                folderName + SUFFIX, emptyContent, metadata);
        amazonS3Client.putObject(putObjectRequest);
    }

    public void removeFolder(String bucketName, String folderName) {
        amazonS3Client.deleteObject(bucketName, folderName);
    }

    public void addObjectToBucket(String bucketName, String objectKey, File object) {
        amazonS3Client.putObject(new PutObjectRequest(bucketName, objectKey, object));
    }

    public S3ObjectInputStream getObjectFromBucket(String bucketName, String objectKey) {
        return amazonS3Client.getObject(bucketName, objectKey).getObjectContent();
    }

    public void removeObjectFromBucket(String bucketName, String objectKey) {
        amazonS3Client.deleteObject(bucketName, objectKey);
    }

    public void removeObjectsFromBucket(String bucketName, List<String> objectKeys) {
        DeleteObjectsRequest deleteObjectsRequest= new DeleteObjectsRequest(bucketName)
                .withKeys(objectKeys.toArray(new String[0]));
        amazonS3Client.deleteObjects(deleteObjectsRequest);
    }
}
