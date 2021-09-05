package com.bangquang1.controllers;

import com.bangquang1.models.Image;
import com.bangquang1.repositories.ImageRepository;
import com.bangquang1.responses.ResponseMessage;
import com.bangquang1.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/bangquang")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @PostMapping("/upload-image/{postID}")
    public ResponseEntity<?> uploadImage(@RequestParam("imageInput") MultipartFile image, @PathVariable Long postID) throws IOException {
        System.out.println("vao upload r");
        System.out.println("lay dc image truyen vao: " + image.getOriginalFilename());

        imageService.saveImage(image, postID);
        System.out.println("image info: " + image.getContentType());
        return ResponseEntity.ok("Upload image success");
    }

//    @PostMapping("/test-upload-image")
//    public ResponseEntity<?> uploadImageTest(@RequestParam("imageInput") MultipartFile image) throws IOException {
//        System.out.println("image.fileName: " + image.getOriginalFilename());
//        System.out.println("image.name: " + image.getName());
//        System.out.println("image.byte: " + image.getBytes());
//        return ResponseEntity.ok(image);
//
//    }

    @Autowired
    ImageRepository imageRepository;

    @GetMapping("/image/{id}")
    public ResponseEntity<?> getFile(@PathVariable String id) {
        Image image = imageRepository.getById(id);

        return ResponseEntity.ok(image);
    }

    @GetMapping("/image/1")
    public ResponseEntity<?> testGetOne() {
        Image image = imageRepository.getById("1");

        return ResponseEntity.ok(image);
    }


    @GetMapping("/images")
    public ResponseEntity<?> getAll() {
        List<Image> images = imageRepository.findAll();

        return ResponseEntity.ok(images);
    }

    @GetMapping("/get-image-by-post-id/{postId}")
    public ResponseEntity<?> getImageByPostId(@PathVariable Long postId) {
        Image image = imageService.getImagesByPostId(postId).get(0);

        if (image == null) {
            return ResponseEntity.badRequest().body("No image with this post id");
        }

        return ResponseEntity.ok(image);
    }
}
