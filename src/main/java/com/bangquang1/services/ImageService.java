package com.bangquang1.services;

import com.bangquang1.models.Image;
import com.bangquang1.models.Post;
import com.bangquang1.repositories.ImageRepository;
import com.bangquang1.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private PostRepository postRepository;

    public Image saveImage(MultipartFile image, Long postID) throws IOException {
        Optional<Post> post = postRepository.findById(postID);
        Image img = new Image(image.getOriginalFilename(), image.getContentType(), image.getBytes(), post.get());
        return imageRepository.save(img);
    }

    public List<Image> getImagesByPostId(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        List<Image> images = imageRepository.findAllByPost(post.get());
        return images;
    }

}
