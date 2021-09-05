package com.bangquang1.repositories;

import com.bangquang1.models.Image;
import com.bangquang1.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
    List<Image> findAllByPost(Post post);
}
