// package com.taskMasterApi.security;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Component;

// import com.taskMasterApi.model.User;
// import com.taskMasterApi.service.impl.UserServiceImpl;

// @Component
// public class DefaultUserInitializer implements CommandLineRunner {

//     @Autowired
//     private UserServiceImpl userService;

//     @Autowired
//     private PasswordEncoder passwordEncoder;

//     @Override
//     public void run(String... args) throws Exception {
//         if (userService.findByUsername("admin").isEmpty()) {
//             User user = new User();
//             user.setUsername("admin");
//             user.setEmail("admin@teste.com");
//             user.setPassword(passwordEncoder.encode("123"));
//             user.setRole("ROLE_ADMIN");
//             System.out.println(
//                 "Admin user created with username: " + user.getUsername() + ", password: " + user.getPassword() + ", and role: "
//                         + user.getRole()
//             );
//             userService.saveUser(user);
//         }
//     }
// }