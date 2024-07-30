// package com.taskMasterApi.service.impl;

// import java.util.Collection;
// import java.util.Collections;
// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// import com.taskMasterApi.model.User;
// import com.taskMasterApi.repository.UserRepository;
// import com.taskMasterApi.security.JwtUtil;

// @Service
// public class UserServiceImpl implements UserDetailsService {

//     private final UserRepository userRepository;
//     private final PasswordEncoder passwordEncoder;

//     @Autowired
//     public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//         this.userRepository = userRepository;
//         this.passwordEncoder = passwordEncoder;
//     }

//     public UserServiceImpl() {
//         this.userRepository = null;
//         this.passwordEncoder = null;
//         // this.jwtUtil = new JwtUtil();
//         // this.userService = new UserServiceImpl();
//     }

//     public List<User> getAllUsers() {
//         return userRepository.findAll();
//     }

//     public Optional<User> getUserById(Long id) {
//         return userRepository.findById(id);
//     }

//     public User saveUser(User user) {
//         user.setPassword(passwordEncoder.encode(user.getPassword()));
//         return userRepository.save(user);
//     }

//     public void deleteUser(Long id) {
//         userRepository.deleteById(id);
//     }

//     public Optional<User> findByUsername(String username) {
//         return userRepository.findByUsername(username);
//     }

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         User user = userRepository.findByUsername(username)
//             .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

//         return new org.springframework.security.core.userdetails.User(
//             user.getUsername(), 
//             user.getPassword(), 
//             getAuthorities(user)
//         );
//     }

//     private Collection<? extends GrantedAuthority> getAuthorities(User user){
//         return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
//     }
// }
