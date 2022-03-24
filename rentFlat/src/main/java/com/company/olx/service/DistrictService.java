package com.company.olx.service;

import com.company.olx.dto.address.DistrictCustomDTO;
import com.company.olx.dto.address.DistrictDTO;
import com.company.olx.entity.CityEntity;
import com.company.olx.entity.DistrictEntity;
import com.company.olx.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class DistrictService {
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private CityService cityService;

    public DistrictCustomDTO create(DistrictCustomDTO dto){
        DistrictEntity entity = new DistrictEntity();
        entity.setName_en(dto.getName_en());
        entity.setName_ru(dto.getName_ru());
        entity.setName_uz(dto.getName_uz());
        entity.setCity(cityService.getById(dto.getCity_id()));
        districtRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<DistrictDTO> getByCityId(Integer cityId,String language){
        List<DistrictEntity> entityList =
                districtRepository.findDistrictEntityByCityId(cityId);

        List<DistrictDTO> dtoList = new LinkedList<>();
        for(DistrictEntity entity:entityList){
            dtoList.add(toDto(entity,language));
        }
        return dtoList;
    }

    public String delete(Integer id){
        DistrictEntity entity = districtRepository.getById(id);
        districtRepository.delete(entity);
        return "Deleted";
    }

    public DistrictEntity getById(Integer id){
        return districtRepository.getById(id);
    }

    public DistrictDTO toDto(DistrictEntity entity,String language){
        DistrictDTO dto = new DistrictDTO();
        dto.setId(entity.getId());
        dto.setCityDTO(cityService.toDtoLang(entity.getCity(),language));
        switch (language){
            case "EN":
                dto.setName(entity.getName_en());
                break;
            case "RU":
                dto.setName(entity.getName_ru());
                break;
            case "UZ":
                dto.setName(entity.getName_uz());
                break;
        }
        return dto;
    }
}
