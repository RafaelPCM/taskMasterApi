// package com.taskMasterApi.controller;


// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.BadCredentialsException;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.taskMasterApi.model.User;
// import com.taskMasterApi.security.AuthenticationRequest;
// import com.taskMasterApi.security.AuthenticationResponse;
// import com.taskMasterApi.security.JwtUtil;
// import com.taskMasterApi.service.impl.UserServiceImpl;

// @RestController
// @RequestMapping("/auth")
// public class AuthenticationController {

//     private final AuthenticationManager authenticationManager;

//     @Autowired
//     public AuthenticationController(AuthenticationManager authenticationManager) {
//         this.authenticationManager = authenticationManager;
//     }


//     @Autowired
//     private UserServiceImpl userService;

//     @Autowired
//     private JwtUtil jwtUtil;

//     // @PostMapping("/login")
//     // public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
//     //     authenticationManager.authenticate(
//     //             new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
//     //     );

//     //     final UserDetails userDetails = userService
//     //             .loadUserByUsername(authenticationRequest.getUsername());

//     //     final String jwt = jwtUtil.generateToken(userDetails);

//     //     return new AuthenticationResponse(jwt);
//     // }

//     // @PostMapping("/login")
//     // public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
//     //     try {
//     //         authenticationManager.authenticate(
//     //             new UsernamePasswordAuthenticationToken(
//     //                 authenticationRequest.getUsername(),
//     //                 authenticationRequest.getPassword()
//     //             )
//     //         );
//     //     } catch (Exception e) {
//     //         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//     //     }

//     //     final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());
//     //     final String jwt = jwtUtil.generateToken(userDetails);

//     //     return ResponseEntity.ok(new AuthenticationResponse(jwt));
//     // }

//     @PostMapping("/login")
//     public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
//         try {
//             authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                     authenticationRequest.getUsername(), 
//                     authenticationRequest.getPassword()
//             ));
//         } catch (BadCredentialsException e) {
//             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erro: Credenciais inválidas");
//         } catch (Exception e) {
//             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erro: Falha na autenticação");
//         }

//         final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());
//         final String jwt = jwtUtil.generateToken(userDetails);
//         return ResponseEntity.ok(new AuthenticationResponse(jwt));
//     }


//     @PostMapping("/register")
//     public User registerUser(@RequestBody User user) {
//         return userService.saveUser(user);
//     }
// }
