package com.company.olx.service;

import com.company.olx.dto.address.AddressDTO;
import com.company.olx.entity.AddressEntity;
import com.company.olx.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private DistrictService districtService;

    public AddressDTO create(AddressDTO dto){
        AddressEntity entity = new AddressEntity();
        entity.setStreet(dto.getStreet());
        entity.setDistrict(districtService.getById(dto.getDistrict_id()));
        addressRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<AddressDTO> list(String language){
        List<AddressDTO> dtoList = new LinkedList<>();
        List<AddressEntity> entityList = addressRepository.findAll();
        for(AddressEntity entity:entityList){
            dtoList.add(toDto(entity,language));
        }
        return dtoList;
    }

    public AddressEntity getById(Integer id){
       return addressRepository.getById(id);
    }

    public AddressDTO toDto(AddressEntity entity,String language){
        AddressDTO dto = new AddressDTO();
        dto.setId(entity.getId());
        dto.setStreet(entity.getStreet());
        dto.setDistrictDTO(districtService.toDto(entity.getDistrict(),language));
        return null;
    }
}
