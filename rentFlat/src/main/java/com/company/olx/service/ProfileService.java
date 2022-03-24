package com.company.olx.service;

import com.company.olx.dto.AuthorizationDTO;
import com.company.olx.dto.ProfileDTO;
import com.company.olx.dto.RegistrationDTO;
import com.company.olx.entity.ProfileEntity;
import com.company.olx.enums.ProfileStatus;
import com.company.olx.enums.ProfileRole;
import com.company.olx.exceptions.ItemNotFountException;
import com.company.olx.repository.ProfileRepository;
import com.company.olx.util.JwtUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private EmailService emailService;

    public RegistrationDTO insertAdmin(RegistrationDTO dto){
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setStatus(ProfileStatus.CREATED);
        entity.setRole(ProfileRole.ADMIN);
        profileRepository.save(entity);
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public RegistrationDTO registration(RegistrationDTO dto){
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());

        String pswd = DigestUtils.md5Hex(dto.getPassword());
        entity.setPassword(pswd);

        entity.setStatus(ProfileStatus.CREATED);
        entity.setRole(ProfileRole.USER);
        profileRepository.save(entity);
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        String jwt = JwtUtil.createJwt(entity.getId());
        emailService.sendEmail(dto.getEmail(),jwt);
        return dto;
    }

    public ProfileDTO authorization(AuthorizationDTO dto){
        Optional<ProfileEntity> optional =
                profileRepository.findByEmailAndPassword(dto.getEmail(),
                dto.getPassword());
        if(!optional.isPresent()){
            throw new RuntimeException("Login or Password incorrect");
        }

        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setName(optional.get().getName());
        profileDTO.setSurname(optional.get().getSurname());
        profileDTO.setEmail(optional.get().getEmail());
        profileDTO.setPhone(optional.get().getPhone());
        profileDTO.setJwt(JwtUtil.createJwt(optional.get().getId(),
                optional.get().getRole()));

        return profileDTO;
    }

    public void verification(String jwt){
        Integer id = JwtUtil.decodeJwtId(jwt);
        Optional<ProfileEntity> optional = profileRepository.findById(id);
        if(!optional.isPresent()){
            throw new ItemNotFountException("User not found");
        }
        if(optional.get().getStatus().equals(ProfileStatus.CREATED))
        {
            optional.get().setStatus(ProfileStatus.ACTIVE);
            profileRepository.save(optional.get());
        }else {
            throw new ItemNotFountException("User not created");

        }
    }

    public PageImpl<RegistrationDTO> userList(int page,int size){
        Pageable paging = PageRequest.of(page,size, Sort.Direction.DESC,"createdDate");
        Page<ProfileEntity> entityPage = profileRepository.findAll(paging);

        long totalElements = entityPage.getTotalElements();

        List<ProfileEntity> entityList = entityPage.getContent();
        List<RegistrationDTO> dtoList = new LinkedList<>();
        for(ProfileEntity entity:entityList){
            dtoList.add(toRegDto(entity));
        }

        return new PageImpl<>(dtoList,paging,totalElements);
    }

    public RegistrationDTO toRegDto(ProfileEntity entity){
        RegistrationDTO dto = new RegistrationDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setPassword(entity.getPassword());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public ProfileDTO toDto(ProfileEntity entity){
        ProfileDTO dto = new ProfileDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setPassword(entity.getPassword());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public ProfileEntity getById(Integer id){
       return profileRepository.getById(id);
    }



}
