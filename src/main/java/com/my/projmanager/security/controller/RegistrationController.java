//package com.my.projmanager.security.controller;
//
//import com.my.projmanager.model.impl.Employee;
//import com.noirix.controller.request.UserCreateRequest;
//import com.noirix.domain.User;
//import com.noirix.service.UserService;
//import io.swagger.annotations.ApiOperation;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.sql.Timestamp;
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/registration")
//@RequiredArgsConstructor
//public class RegistrationController {
//
//    private final EmployeeService userService;
//    private final PasswordEncoder passwordEncoder;
//
//    @ApiOperation(value = "Endpoint for registration")
//    @PostMapping
//    public ResponseEntity<Map<String, Object>> registration(@RequestBody EmployeeCreateRequest userCreateRequest) {
//        //converters
//        Employee user = new Employee();
//        user.setGender(userCreateRequest.getGender());
//        user.setName(userCreateRequest.getName());
//        user.setSurname(userCreateRequest.getSurname());
//        user.setBirthDate(userCreateRequest.getBirthDate());
//        user.setCreated(new Timestamp(System.currentTimeMillis()));
//        user.setChanged(new Timestamp(System.currentTimeMillis()));
//        user.setWeight(userCreateRequest.getWeight());
//        user.setLogin(userCreateRequest.getLogin());
//        user.setPassword(passwordEncoder.encode(userCreateRequest.getPassword()));
//        Employee savedUser = userService.save(user);
//
//        Map<String, Object> result = new HashMap<>();
//
//        result.put("id", savedUser.getId());
//        result.put("login", savedUser.getMail());
//
//        return new ResponseEntity<>(result, HttpStatus.CREATED);
//    }
//}
