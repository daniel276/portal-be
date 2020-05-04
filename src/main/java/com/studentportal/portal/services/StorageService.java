package com.studentportal.portal.services;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.studentportal.portal.domain.FaceBiometric;
import com.studentportal.portal.domain.UserLogin;
import com.studentportal.portal.repositories.FaceBiometricsRepository;
import com.studentportal.portal.repositories.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@Service
public class StorageService {

    @Autowired
    FaceBiometricsRepository faceBiometricsRepository;

    @Autowired
    UserLoginRepository userRepository;

    private AmazonS3 amazonS3Client;

    // take the constant values from application.yml
    private String endpointUrl = "https://portal-fr-bucket.s3.eu-west-2.amazonaws.com";

    private String bucketName = "portal-fr-bucket";
    @Value("${amazonProperties.accessKey}")
    private String accessKey = "AKIAJBRGKCOGTTQQHCXA";

    private String secretKey = "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";

    @PostConstruct
    private void initializeAmazon(){
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        amazonS3Client = new AmazonS3Client(credentials);
    }

    //separate multipart into each file, to allow multi-file uploading
    //convert MutliPart file to File
    private File convertMultipartToFile(MultipartFile file) throws IOException {
        File file1 = new File(file.getOriginalFilename());
        FileOutputStream fo = new FileOutputStream(file1);
        fo.write(file.getBytes());
        fo.close();
        return file1;
    }

    private String generateUserFileName(String username){
        return username + "-" + new Date().getTime(); // filename will be constructed with username and followed by current time to give unique identifier
    }

    private void s3ProfilePictureUploader(String fileName, File file) {
        amazonS3Client.putObject(
                new PutObjectRequest(bucketName, "profile_picture/"+fileName + ".jpg", file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    private void s3BiometricsPictureUploader(String username, String fileName, File file) {
        amazonS3Client.putObject(
                new PutObjectRequest(bucketName, "biometrics/" + username + "/" + fileName + ".jpg", file)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public String uploadProfilePicture(MultipartFile multipartFile, String username){
        String fileUrl = "";

        try {
            File file = convertMultipartToFile(multipartFile);
            String fileName = generateUserFileName(username);
            fileUrl = endpointUrl + "/profile_picture/" + fileName + ".jpg"; // create a folder for each user to contain their image separately
            s3ProfilePictureUploader(fileName, file);
            file.delete();
        }catch (Exception e){
            e.printStackTrace();
        }
        return fileUrl;
    }

    public String uploadBiometricsPicture(MultipartFile multipartFile, String username){
        String fileUrl = "";

        try{
            File file = convertMultipartToFile(multipartFile);
            String fileName = generateUserFileName(username);
            fileUrl = endpointUrl + "/biometrics/" + username + "/" + fileName + ".jpg";
            s3BiometricsPictureUploader(username, fileName, file);
        }catch (Exception e){
            e.printStackTrace();
        }

        return fileUrl;
    }

    public FaceBiometric saveBiometricData(FaceBiometric faceBiometric, String username){
        UserLogin user = userRepository.findByUsername(username);
        faceBiometric.setUserLogin(user);

        return faceBiometricsRepository.save(faceBiometric);
    }

    public Iterable<FaceBiometric> findAllByUsername(String username){
        return faceBiometricsRepository.findAllByUserLoginUsername(username);
    }

    public String deleteBiometricFile(String username, Long id){
        FaceBiometric faceBiometric = faceBiometricsRepository.findFaceBiometricById(id); // Get FaceBiometric by id
        String fileUrl = faceBiometric.getUrl();
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1); // get filename, since s3 can't delete by url
        amazonS3Client.deleteObject(new DeleteObjectRequest(bucketName + "/biometrics/"+ username + "/", fileName + ".jpg"));
        faceBiometricsRepository.delete(faceBiometric); // delete saved entry in db
        return "Item Successfully Deleted";
    }


}
