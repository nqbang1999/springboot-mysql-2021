package com.bangquang1.controllers;

import com.bangquang1.fomatter.Utils;
import com.bangquang1.forms.ChangePasswordForm;
import com.bangquang1.forms.LoginForm;
import com.bangquang1.forms.RegisterForm;
import com.bangquang1.models.Image;
import com.bangquang1.models.Post;
import com.bangquang1.models.User;
import com.bangquang1.repositories.PostRepository;
import com.bangquang1.repositories.PostResponseRepository;
import com.bangquang1.repositories.UserRepository;
import com.bangquang1.responses.PostResponse;
import com.bangquang1.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/bangquang")
public class UserController {

    @Autowired
    UserRepository userRepository;

//    @PostMapping("/register")
//    public ResponseEntity<?> register(@Valid @RequestBody User user) {
//
//        if (userRepository.existsByUsername(user.getUsername())) {
//            System.out.println("register: trung username");
//            return ResponseEntity.badRequest().body("Register: duplicate username");
//        }
//
//        if (userRepository.existsByEmail(user.getEmail())) {
//            System.out.println("register: trung email");
//            return ResponseEntity.badRequest().body("Register: duplicate email");
//        }
//
//        User newUser = new User(user.getUsername(),
//                                user.getEmail(),
//                                user.getPassword());
//
//        userRepository.save(newUser);
//        System.out.println("register: gia tri of user: ");
//        System.out.println(user.getUsername());
//        System.out.println(user.getEmail());
//        System.out.println(user.getPassword());
//        System.out.println("register: da tao user thanh cong");
//        return ResponseEntity.ok("Register: register successfully");
//    }

    String usernameLogin;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginForm loginForm) {
        System.out.println("vao login chua");

        if (loginForm.getUsername().isEmpty()) {
            return ResponseEntity.badRequest().body("Login: Username is required");
        }
        if (loginForm.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("Login: Password is required");
        }
        if (!userRepository.existsByUsername(loginForm.getUsername())) {
            return ResponseEntity.badRequest().body("Login: Username incorrect");
        }
        if (!userRepository.existsByPassword(loginForm.getPassword())) {
            return ResponseEntity.badRequest().body("Login: Password incorrect");
        }

        User userLogin = userRepository.getUserByUsername(loginForm.getUsername());
        usernameLogin = loginForm.getUsername();

        return ok(userLogin);
    }

    @Autowired
    PostRepository postRepository;

    @PostMapping("/create-post")
    public ResponseEntity<?> createPost(@Valid @RequestBody Post postInput) {
        System.out.println("vao create-post chua");

        if (postInput.getTitle().isEmpty()) {
            return ResponseEntity.badRequest().body("Create post: Title is required");
        }
        if (postInput.getContent().isEmpty()) {
            return ResponseEntity.badRequest().body("Create post: Content is required");
        }

        LocalDateTime now = LocalDateTime.now();
        postInput.setDateTime(now);

        System.out.println("create post_usernameLogin: " + usernameLogin);

        User userLogin = userRepository.getUserByUsername(usernameLogin);
        postInput.setUser(userLogin);

        postRepository.save(postInput);

        return ok(postInput);
    }

    @GetMapping("/get-all-posts")
    public ResponseEntity<?> getAllPosts() {
        System.out.println("dm");


        List<Post> posts = postRepository.getAllByOrderByDateTimeDesc();

        for (Post post: posts) {
            System.out.println("den: " + post.getId());
            System.out.println("den: " + post.getTitle());
            System.out.println("den: " + post.getContent());
        }

        System.out.println("dm1");

        if (posts.isEmpty()) {
            return ResponseEntity.badRequest().body("Get all posts: Nothing to display");
        }

        System.out.println("dm2");

        return ok(posts);
    }

    @Autowired
    ImageService imageService;

//    @Autowired
//    Utils utils;

    @GetMapping("/get-all-posts-with-image")
    public ResponseEntity<?> getAllPostsWithImage() {
        Utils utils = new Utils();

        List<Post> posts = postRepository.getAllByOrderByDateTimeDesc();

        if (posts.isEmpty()) {
            return ResponseEntity.badRequest().body("Get all posts: Nothing to display");
        }

        ArrayList<PostResponse> postResponses = new ArrayList<>();

        for (Post post: posts) {
            post.getId();
            post.getTitle();
            post.getContent();
            post.getUser();
            utils.formatLocalDateTime(post.getDateTime());
            Image image = imageService.getImagesByPostId(post.getId()).get(0);
            String imgURL = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(image.getData());

            String shortTitleResponse;
            String shortContentResponse;

            if (post.getTitle().length() > 90) {
                shortTitleResponse = post.getTitle().substring(0, 90);
            } else {
                shortTitleResponse = post.getTitle();
            }

            if (post.getContent().length() > 130) {
                shortContentResponse = post.getContent().substring(0, 130);
            } else {
                shortContentResponse = post.getContent();
            }

            PostResponse postResponse = new PostResponse(post.getId(),
                                                         shortTitleResponse,
                                                         shortContentResponse,
                                                         post.getUser(),
                                                         utils.formatLocalDateTime(post.getDateTime()),
                                                         image,
                                                         imgURL);
            postResponses.add(postResponse);
        }

        return ok(postResponses);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterForm registerForm) {
        if (registerForm.getUsernameInput().isEmpty()) {
            return ResponseEntity.badRequest().body("Register: Username is required");
        }
        if (registerForm.getEmailInput().isEmpty()) {
            return ResponseEntity.badRequest().body("Register: Email is required");
        }
        if (registerForm.getPasswordInput().isEmpty()) {
            return ResponseEntity.badRequest().body("Register: Password is required");
        }
        if (registerForm.getRe_passwordInput().isEmpty()) {
            return ResponseEntity.badRequest().body("Register: Confirm your password");
        }
        if (userRepository.existsByUsername(registerForm.getUsernameInput())) {
            return ResponseEntity.badRequest().body("Register: Username is already in use");
        }
//        if (registerForm.getEmailInput().) {
//            return ResponseEntity.badRequest().body("Register: Email is already in use");
//        }
        if (userRepository.existsByEmail(registerForm.getEmailInput())) {
            return ResponseEntity.badRequest().body("Register: Email is already in use");
        }
        if (!registerForm.getPasswordInput().equals(registerForm.getRe_passwordInput())) {
            return ResponseEntity.badRequest().body("Register: Password does not match");
        }

        User newUser = new User(registerForm.getUsernameInput(),
                                registerForm.getEmailInput(),
                                registerForm.getPasswordInput());

        userRepository.save(newUser);
        return ok(newUser);
    }

    @GetMapping("/get-user-info/{usernameInput}")
    public ResponseEntity<?> getUserInfo(@PathVariable String usernameInput) {
        User userLogin = userRepository.getUserByUsername(usernameInput);

        if (userLogin.equals(null)) {
            return ResponseEntity.badRequest().body("GetUserInfo: userLogin = null");
        }
        return ok(userLogin);
    }

    // test page
    @Autowired
    PostResponseRepository postResponseRepository;
    @GetMapping("/get-posts-page")
    public ResponseEntity<?> getPostsPage() {
        Pageable paging = PageRequest.of(0, 5);

        Page<Post> postPage = postResponseRepository.findAll(paging);

        return ok(postPage);
    }

    @PutMapping("/change-password/{loginUsername}")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordForm changePasswordForm, @PathVariable String loginUsername) {
        if (changePasswordForm.getCurrentPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("Change Password: Current password is required");
        }
        if (changePasswordForm.getNewPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("Change Password: New password is required");
        }
        if (changePasswordForm.getConfirmedNewPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("Change Password: Confirmed password is required");
        }

        User userLogin = userRepository.getUserByUsername(loginUsername);
        if (!userLogin.getPassword().equals(changePasswordForm.getCurrentPassword())) {
            return ResponseEntity.badRequest().body("Change Password: Current password is not correct");
        }
        if (!changePasswordForm.getNewPassword().equals(changePasswordForm.getConfirmedNewPassword())) {
            return ResponseEntity.badRequest().body("Change Password: Confirmed password is not matching");
        }

        userLogin.setPassword(changePasswordForm.getNewPassword());

        userRepository.save(userLogin);
        return ResponseEntity.ok(userLogin);
    }

    @GetMapping("/get-post-detail/{postId}")
    public ResponseEntity<?> getPostDetail(@PathVariable Long postId) {
        Post post = postRepository.findById(postId).get();

        Utils utils = new Utils();

        if (post == null) {
            return ResponseEntity.badRequest().body("GetPostDetail: null");
        }

        Image image = imageService.getImagesByPostId(post.getId()).get(0);
        String imgURL = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(image.getData());
        PostResponse postResponse = new PostResponse(post.getId(),
                                                     post.getTitle(),
                                                     post.getContent(),
                                                     post.getUser(),
                                                     utils.formatLocalDateTime(post.getDateTime()),
                                                     image,
                                                     imgURL);

        return ResponseEntity.ok(postResponse);
    }



}
