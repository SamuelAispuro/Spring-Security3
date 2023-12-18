package com.example.Controller;

import com.example.Entities.ERole;
import com.example.Entities.RoleEntity;
import com.example.Entities.UserEntity;
import com.example.Repositories.UserRepository;
import com.example.Request.CreateUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class PrincipalController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

@GetMapping("/hello")
    public String hello(){
    return "Hello World No Seguro";
}

@GetMapping("/helloSecured")
    public String helloSecured(){
    return "Hello World Secured";
}

@PostMapping("/createUser")
public ResponseEntity<?> createUser(@RequestBody CreateUserDTO createUserDTO) {

    Set<RoleEntity> roles = createUserDTO.getRoles().stream()
            .map(role -> RoleEntity.builder()
                    .name(ERole.valueOf(role))
                    .build())
            .collect(Collectors.toSet());


    UserEntity userEntity = UserEntity.builder()
            .nombre(createUserDTO.getNombre())
            .email(createUserDTO.getEmail())
            .password(passwordEncoder.encode(createUserDTO.getPassword()))
            .roles(roles)
            .build();

    userRepository.save(userEntity);

    return ResponseEntity.ok(userEntity);
}

@DeleteMapping("/deleteUser")
public String deleteUser(@RequestParam String id){

    userRepository.deleteById(Integer.parseInt(id));
    return "Se ha borrado el user con id".concat(id);

}

}
