package com.example.hh.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
public class AwsService {

    private Logger logger = LoggerFactory.getLogger(AwsService.class);

    @Autowired
    private AmazonS3 amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    //InputStream으로는 byte만 전달되기 때문에 Metadata를 써서 정보를 같이준다.
    public String uploadFile(String keyName, MultipartFile file){
        try{
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            amazonS3Client.putObject(bucketName, keyName, file.getInputStream(), metadata);
            return "File upload: " + keyName;
        } catch (IOException ioe){
            logger.error("IOException: " + ioe.getMessage());
        } catch (AmazonServiceException amazonServiceException) {
            logger.info("AmazonServiceException Message: " + amazonServiceException.getMessage());
            throw amazonServiceException;
        }catch (AmazonClientException clientException){
            logger.info("AmazonClientException Message: " + clientException.getMessage());
            throw clientException;
        }
        return "File not uploaded: " + keyName;
    }
}
