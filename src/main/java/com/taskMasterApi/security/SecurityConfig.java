// package com.taskMasterApi.security;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.core.authority.AuthorityUtils;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// import com.taskMasterApi.repository.UserRepository;


// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     @Autowired
//     private final PasswordEncoder passwordEncoder;

//     @Autowired
//     public SecurityConfig(PasswordEncoder passwordEncoder) {
//         this.passwordEncoder = passwordEncoder;
//     }

//     // @Bean
//     // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//     //     http
//     //         .csrf(csrf -> csrf.disable())
//     //         .authorizeHttpRequests(auth -> auth
//     //             .requestMatchers("/auth/login").permitAll()
//     //             .anyRequest().authenticated()
//     //         )
//     //         .formLogin(form -> form
//     //             .defaultSuccessUrl("/home", true)
//     //         )
//     //         .httpBasic();
//     //     return http.build();
//     // }

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http
//             .csrf(csrf -> csrf.disable())
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/auth/login").permitAll()
//                 .anyRequest().authenticated()
//             )
//             .addFilterBefore(new JwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);
//         return http.build();
//     }

//     @Autowired
//     public void configureGlobal(AuthenticationManagerBuilder auth, UserDetailsService userDetailsService) throws Exception {
//         auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
//     }

//     @Bean
//     public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
//         return http.getSharedObject(AuthenticationManagerBuilder.class).build();
//     }
// }

// @Configuration
// class EncoderConfig {
//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }

