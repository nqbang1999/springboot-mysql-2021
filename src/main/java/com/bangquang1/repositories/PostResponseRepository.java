package com.bangquang1.repositories;

import com.bangquang1.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostResponseRepository extends PagingAndSortingRepository<Post, Long> {

    @Override
    Page<Post> findAll(Pageable pageable);
}
