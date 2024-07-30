// package com.taskMasterApi.controller;


// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import com.taskMasterApi.service.impl.PasswordResetServiceImpl;

// @RestController
// @RequestMapping("/password")
// public class PasswordController {

//     @Autowired
//     private PasswordResetServiceImpl passwordResetService;

//     @PostMapping("/reset-request")
//     public void requestPasswordReset(@RequestParam String email) {
//         passwordResetService.sendPasswordResetEmail(email);
//     }

//     @PostMapping("/reset")
//     public void resetPassword(@RequestParam String token, @RequestParam String newPassword) {
//         passwordResetService.resetPassword(token, newPassword);
//     }
// }
