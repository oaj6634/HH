package com.example.hh.aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
public class AwsS3Config {

    @Value("${cloud.aws.credentials.access-key:AKIAVMSYND4UKYORD6FH}")
    private String awsId;

    @Value("${cloud.aws.credentials.secret-key:WLOE0iFQShyyKB7lnfPAuBHysNHquJM436V0sAWf}")
    private String awsKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Bean
    public AmazonS3 s3client() {

        BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(awsId,awsKey);
        AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.fromName(region))
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                .build();

        return amazonS3;
    }


}
