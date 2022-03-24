package com.company.olx.controller;

import com.company.olx.dto.*;
import com.company.olx.enums.ProfileRole;
import com.company.olx.service.ProfileService;
import com.company.olx.dto.RegistrationDTO;
import com.company.olx.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;



    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody RegistrationDTO dto){
        //TODO 404 not fount
        return ResponseEntity.ok(profileService.registration(dto));
    }

    @PostMapping("/authorization")
    public ResponseEntity<?> authorization(@RequestBody AuthorizationDTO dto){
        ProfileDTO responce = profileService.authorization(dto);
        return ResponseEntity.ok(responce);
    }

    @GetMapping("/verification/{jwt}")
    public ResponseEntity<?> verification(@PathVariable("jwt") String jwt){
        profileService.verification(jwt);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/userList")
    public ResponseEntity<?> userList(@RequestParam("page") int page,
                                      @RequestParam("size") int size,
                                      HttpServletRequest token){
        JwtUtil.checkProfile(token, ProfileRole.ADMIN);
        return ResponseEntity.ok(profileService.userList(page,size));
    }
    @PostMapping("/insertAdmin")
    public ResponseEntity<?> insertAdmin(@RequestBody RegistrationDTO dto,
                                         HttpServletRequest token){
        JwtUtil.checkProfile(token, ProfileRole.ADMIN);
        return ResponseEntity.ok(profileService.insertAdmin(dto));
    }

}
