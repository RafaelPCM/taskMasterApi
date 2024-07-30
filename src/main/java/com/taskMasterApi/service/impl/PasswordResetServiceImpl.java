// package com.taskMasterApi.service.impl;


// import java.util.Optional;
// import java.util.UUID;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.mail.SimpleMailMessage;
// import org.springframework.mail.javamail.JavaMailSender;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// import com.taskMasterApi.model.User;
// import com.taskMasterApi.repository.AuditRepository;
// import com.taskMasterApi.repository.UserRepository;

// @Service
// public class PasswordResetServiceImpl {

//     @Autowired
//     private UserRepository userRepository;

//     @Autowired
//     private JavaMailSender mailSender;

//     @Autowired
//     private PasswordEncoder passwordEncoder;

//     @Autowired
//     private AuditServiceImpl auditService;

//     public void sendPasswordResetEmail(String email) {
//         Optional<User> userOptional = userRepository.findByEmail(email);
//         if (userOptional.isPresent()) {
//             User user = userOptional.get();
//             String token = UUID.randomUUID().toString();
//             user.setResetToken(token);
//             userRepository.save(user);

//             SimpleMailMessage mailMessage = new SimpleMailMessage();
//             mailMessage.setTo(user.getEmail());
//             mailMessage.setSubject("Password Reset Request");
//             mailMessage.setText("To reset your password, click the link below:\n"
//                     + "http://localhost:8080/reset?token=" + token);
//             mailSender.send(mailMessage);
            
//             auditService.logAction("User", "Password reset email sent", user.getUsername());
//         }
//     }


//     public void resetPassword(String token, String newPassword) {
//         Optional<User> userOptional = userRepository.findByResetToken(token);
//         if (userOptional.isPresent()) {
//             User user = userOptional.get();
//             user.setPassword(passwordEncoder.encode(newPassword));
//             user.setResetToken(null);
//             userRepository.save(user);
            
//             auditService.logAction("User", "Password reset", user.getUsername());
//         }
//     }
// }
