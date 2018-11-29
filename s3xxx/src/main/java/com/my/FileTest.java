package com.my;

import java.io.File;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class FileTest {
	
	public static AmazonS3 amazonS3 = null;
	public static AWSCredentials credentials = null;
	
	 static {
		 String accessKey = "IOCUMFPQ2WP113OU10DJ";
	        String secretKey = "DqOOPgdhaiDt0rRmZUoGUMz6RDm62yo8B0EfnkaJ";
	        
	         credentials = new BasicAWSCredentials(accessKey, secretKey);
	        amazonS3 = AmazonS3ClientBuilder.standard()
	                .withCredentials(new AWSStaticCredentialsProvider(credentials))
	                .withEndpointConfiguration(
	                        new AwsClientBuilder.EndpointConfiguration(
	                                "http://192.168.4.237:7480",
	                                ""))
	                .build();
	 }
		 
	
	
	public static void main(String[] args) {
			
		
		List<Bucket> listBuckets = amazonS3.listBuckets();
        for (Bucket bucket : listBuckets) {
            System.out.println(bucket.getName());
        }
        System.out.println("---------------owncloud--------------------");
        ObjectListing objects = amazonS3.listObjects("owncloud");
        do 
        {
            for (S3ObjectSummary objectSummary : objects.getObjectSummaries()) 
            {
            	System.out.println("Object: " + objectSummary.getKey());            	
            }                            	
            objects = amazonS3.listNextBatchOfObjects(objects);
        } while (objects.isTruncated());
	        
	        System.out.println("---------------nextcloud--------------------");
	        ObjectListing objects2 = amazonS3.listObjects("nextcloud");
	        do 
	        {
	            for (S3ObjectSummary objectSummary : objects2.getObjectSummaries()) 
	            {
	            	System.out.println("Object: " + objectSummary.getKey());            	
	            }                            	
	            objects2 = amazonS3.listNextBatchOfObjects(objects);
	        } while (objects2.isTruncated());
	        
	        
	        
	    //    AmazonS3Client client = new AmazonS3Client(credentials);
	        
	     //   S3Object o = client.getObject("", "");
	     //   S3ObjectInputStream s3is = o.getObjectContent();
	       // body = new byte[(int)o.getObjectMetadata().getContentLength()];
	    //    s3is.read(body);
	     //   s3is.close();



	        
	      //  DeleteObjectRequest request = new DeleteObjectRequest("nextcloud", "8sd8fhdfwefsfsfjs");
	     //   amazonS3.deleteObject(request);
	        
	        
	}
	 /*
     * 上传文件
     */
	public static void   upload(String sourceFilepath,String buckName,String s3path) {
	    
        File file = new File(sourceFilepath);        
        
        // 默认添加public权限
        amazonS3.putObject(new PutObjectRequest(buckName, s3path, file)
        	.withCannedAcl(CannedAccessControlList.PublicRead));
	}
	
	/*
	 * 查询
	 */
	public void query() {
		
	}
	
	
	 
	
	
	
}
