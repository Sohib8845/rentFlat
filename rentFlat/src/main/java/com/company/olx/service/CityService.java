package com.company.olx.service;

import com.company.olx.dto.address.CityCustomDTO;
import com.company.olx.dto.address.CityDTO;
import com.company.olx.entity.CityEntity;
import com.company.olx.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public CityCustomDTO create(CityCustomDTO dto){
        CityEntity entity = new CityEntity();
        entity.setName_uz(dto.getName_uz());
        entity.setName_en(dto.getName_en());
        entity.setName_ru(dto.getName_ru());
        cityRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<CityDTO> list(String language){
        List<CityEntity> entityList = cityRepository.findAll();
        List<CityDTO> dtoList = new LinkedList<>();

        for(CityEntity entity:entityList){
            dtoList.add(toDtoLang(entity,language));
        }
        return dtoList;
    }

    public String delete(Integer id){
        CityEntity entity = cityRepository.getById(id);
        cityRepository.delete(entity);
        return "Deleted";
    }

    public CityEntity getById(Integer id){
        return cityRepository.getById(id);
    }


    public CityDTO toDtoLang(CityEntity entity,String language){
        CityDTO dto = new CityDTO();
        dto.setId(entity.getId());
        switch (language){
            case "EN":dto.setName(entity.getName_en());
                break;
            case "RU":dto.setName(entity.getName_ru());
                break;
            case "UZ":dto.setName(entity.getName_uz());
                break;
            default:break;
        }
        return dto;
    }

}
