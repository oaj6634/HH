package com.example.hh.aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//AWS S3에 접근하기 위해 만든 IAM 계정의 정보를 가져오기 위한 클래스
@Configuration
public class AwsS3Config {

    @Value("${cloud.aws.credentials.access-key:AKIAVMSYND4UABGNXHOC}")
    private String awsId;

    @Value("${cloud.aws.credentials.secret-key:RfcaN81MPi6VfOTq1Lswf63EStG/cJJtbEdFH2IC}")
    private String awsKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Bean
    public AmazonS3Client s3client() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsId, awsKey);
        return (AmazonS3Client) AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
