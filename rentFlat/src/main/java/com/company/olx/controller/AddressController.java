package com.company.olx.controller;

import com.company.olx.dto.address.AddressDTO;
import com.company.olx.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody AddressDTO dto){
        return ResponseEntity.ok(addressService.create(dto));
    }

    @GetMapping("/list")
    public ResponseEntity<?> list(@RequestParam("/language") String language){
        return ResponseEntity.ok(addressService.list(language));
    }

}
