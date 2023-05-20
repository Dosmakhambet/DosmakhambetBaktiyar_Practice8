package com.dosmakhambetbbaktiyar_practice8.service.impl;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.dosmakhambetbbaktiyar_practice8.service.AmazonService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

@Service
public class AmazonServiceImpl implements AmazonService {

    private AmazonS3 amazonS3;
    @Value("${aws.access_key}")
    private String ACCESS_KEY;
    @Value("${aws.secret_key}")
    private  String SECRET_KEY;
    @Value("${aws.url}")
    private String URL;
    @Value("${aws.bucket_name}")
    private String BUCKET_NAME;


    @PostConstruct
    private void init(){
        amazonS3 = new AmazonS3Client(new BasicAWSCredentials(ACCESS_KEY,SECRET_KEY));
    }

    @Override
    public String uploadFile(MultipartFile file) {

        File convertedFile = convertMultipartToFile(file);
        String fileName = file.getOriginalFilename().replace(' ', '_');
        String urlFile = URL+"/"+fileName;
        amazonS3.putObject(new PutObjectRequest(BUCKET_NAME,fileName,convertedFile));

        return urlFile;
    }

    private File convertMultipartToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());

        try(FileOutputStream fos = new FileOutputStream(convertedFile)){
            fos.write(file.getBytes());
            return convertedFile;
        }catch (Exception e){
            System.out.println("Error message : " + e.getMessage());
        }

        return null;
    }
}
