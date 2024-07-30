// package com.taskMasterApi.controller;


// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.taskMasterApi.model.User;
// import com.taskMasterApi.service.impl.UserServiceImpl;

// @RestController
// @RequestMapping("/users")
// public class UserController {

//     @Autowired
//     private UserServiceImpl userService;

//     @Autowired
//     private PasswordEncoder passwordEncoder;

//     @GetMapping
//     public List<User> getAllUsers() {
//         return userService.getAllUsers();
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<User> getUserById(@PathVariable Long id) {
//         Optional<User> user = userService.getUserById(id);
//         return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//     }

//     @PostMapping
//     public User createUser(@RequestBody User user) {
//         return userService.saveUser(user);
//     }

//     @PutMapping("/{id}")
//     public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
//         Optional<User> existingUser = userService.getUserById(id);
//         if (existingUser.isPresent()) {
//             user.setId(id);
//             user.setPassword(passwordEncoder.encode(user.getPassword()));
//             return ResponseEntity.ok(userService.saveUser(user));
//         } else {
//             return ResponseEntity.notFound().build();
//         }
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//         userService.deleteUser(id);
//         return ResponseEntity.noContent().build();
//     }
// }





//     // @ResponseBody
// 	// @PostMapping("**/buscarInstrutor")
// 	// // @RequestMapping(method = RequestMethod.POST , value = "**/buscarInstrutor", produces = "application/json")
// 	// public String buscarInstrutor(@RequestParam("idInstrutor") String idInstrutor) throws JsonProcessingException {
// 	// 	Instrutor instrutor = instrutorRepository.findInstrutorById(Long.parseLong(idInstrutor));
// 	// 	ObjectMapper mapper = new ObjectMapper();
// 	// 	String json = mapper.writeValueAsString(instrutor);
// 	// 	return json;
// 	// }


