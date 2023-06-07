package com.openapi.openapigendemo;

import com.demo.petstore.api.UserApiDelegate;
import com.demo.petstore.model.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserApiDelegateImpl implements UserApiDelegate {

    private List<UserDTO> userDTOList = new ArrayList<UserDTO>();

    @Override
    public ResponseEntity<UserDTO> getUserById(UUID id) {
        System.out.println("UserAPIDelegateImpl - getUserById");
        UserDTO userDTO = userDTOList.stream().filter( usrData -> usrData.getId().equals(id)).findFirst().orElse(null);
//        return UserApi.super.getUserById(id);
        if(Objects.nonNull(userDTO)){
            return new ResponseEntity<>(userDTO,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<UserDTO> saveUser(UserDTO userDTO) {
        System.out.println("UserAPIDelegateImpl - saveUser");
        userDTO.setId(UUID.randomUUID());
        userDTOList.add(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
