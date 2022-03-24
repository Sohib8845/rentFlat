package com.company.olx.controller;

import com.company.olx.dto.address.CityCustomDTO;
import com.company.olx.dto.address.CityDTO;
import com.company.olx.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {
    @Autowired
    private CityService cityService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody CityCustomDTO dto){
        return ResponseEntity.ok(cityService.create(dto));
    }

    @GetMapping("/list")
    public ResponseEntity<?> list(@RequestParam("language") String language){
        return ResponseEntity.ok(cityService.list(language));
    }


    @PutMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("id") Integer id){
        return ResponseEntity.ok(cityService.delete(id));
    }

}
