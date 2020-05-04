package com.studentportal.portal.web;

import com.studentportal.portal.domain.FaceBiometric;
import com.studentportal.portal.domain.UserLogin;
import com.studentportal.portal.repositories.FaceBiometricsRepository;
import com.studentportal.portal.repositories.UserLoginRepository;
import com.studentportal.portal.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/storage")
@CrossOrigin
public class StorageController {

    private StorageService storageService;

    @Autowired
    private UserLoginRepository userLoginRepository;

    @Autowired
    StorageController(StorageService storageService){
        this.storageService = storageService;
    }

    @PostMapping("/upload/biometric/{username}")
    public ResponseEntity<?> uploadUserBiometrics(@RequestPart(value = "file") MultipartFile file, @PathVariable String username){
        String url = this.storageService.uploadBiometricsPicture(file, username);

        FaceBiometric face = new FaceBiometric();
        face.setUrl(url);
        storageService.saveBiometricData(face, username);

        return new ResponseEntity<>(url, HttpStatus.OK);
    }

    @PostMapping("/upload/profile/{username}")
    public ResponseEntity<?> uploadUserProfilePicture(@RequestPart(value = "file") MultipartFile file, @PathVariable String username){
       String url = this.storageService.uploadProfilePicture(file, username);

        UserLogin user = userLoginRepository.findByUsername(username); // find user by username
        // set new url to the field
        user.setProfile_picture(url);
        userLoginRepository.save(user);

        return new ResponseEntity<>(url, HttpStatus.OK);
    }

    @GetMapping("/biometrics/{username}")
    public Iterable<FaceBiometric> getAllBiometricLink(@PathVariable String username){
        return storageService.findAllByUsername(username);
    }

    @DeleteMapping("/biometric/{username}/{id}")
    public String deleteFile(@PathVariable String username, @PathVariable Long id){
        return this.storageService.deleteBiometricFile(username, id);
    }



}
