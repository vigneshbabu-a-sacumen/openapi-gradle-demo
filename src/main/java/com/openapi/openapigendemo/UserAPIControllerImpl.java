package com.openapi.openapigendemo;

import com.demo.petstore.api.UserApiDelegate;
import com.demo.petstore.model.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/api/user")
public class UserAPIControllerImpl implements UserApiDelegate {

    private List<UserDTO> userDTOList = new ArrayList<UserDTO>();

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") UUID id) {
        System.out.println("UserAPIControllerImpl - getUserById");
        UserDTO userDTO = userDTOList.stream().filter( usrData -> usrData.getId().equals(id)).findFirst().orElse(null);

        if(Objects.nonNull(userDTO)){
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Override
    public ResponseEntity<UserDTO> saveUser( @RequestBody UserDTO userDTO) {
        System.out.println("UserAPIControllerImpl - saveUser");
        userDTO.setId(UUID.randomUUID());
        userDTOList.add(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping
    @Override
    public ResponseEntity<List<UserDTO>> getAll() {
        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }
}
