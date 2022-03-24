package com.company.olx.controller;

import com.company.olx.dto.address.DistrictCustomDTO;
import com.company.olx.service.DistrictService;
import com.company.olx.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/district")
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody DistrictCustomDTO dto){
        return ResponseEntity.ok(districtService.create(dto));
    }

    @GetMapping("/list")
    public ResponseEntity<?> list(@RequestParam("cityId") Integer cityId,
                                  @RequestParam("language") String language
                                  ){
        return ResponseEntity.ok(districtService.getByCityId(cityId,language));
    }

    @PutMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("id") Integer id){
        return ResponseEntity.ok(districtService.delete(id));
    }
}
