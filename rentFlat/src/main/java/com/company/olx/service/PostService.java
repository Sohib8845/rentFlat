package com.company.olx.service;

import com.company.olx.dto.RegistrationDTO;
import com.company.olx.dto.post.PostDTO;
import com.company.olx.dto.post.PostFilterDTO;
import com.company.olx.entity.AddressEntity;
import com.company.olx.entity.PostEntity;
import com.company.olx.entity.ProfileEntity;
import com.company.olx.repository.PostCustomRepository;
import com.company.olx.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private PostCustomRepository customRepository;

    //TODO post create bolayotganda ham language keladimi
    //shuni global qilib qoysa bolmaydimi ?
    //alohida api qilinadi
    public PostDTO creat(PostDTO dto){
        PostEntity entity = new PostEntity();
        entity.setRooms(dto.getRooms());
        entity.setFloor(dto.getFloor());
        entity.setPrice(dto.getPrice());
        AddressEntity address =
                addressService.getById(dto.getAddress_id());
        entity.setAddress(address);
        ProfileEntity profile =
                profileService.getById(dto.getProfile_id());
        entity.setProfile(profile);
        postRepository.save(entity);
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public PageImpl<PostDTO> filter(int page, int size, PostFilterDTO filterDTO) {
        PageImpl<PostEntity> entityPage = customRepository.filter(page, size, filterDTO);
        //TODO stream kn map nima bu
        List<PostDTO> postDTOList = entityPage.stream().map(postEntity -> {
            PostDTO dto = toDto(postEntity);
            return dto;
        }).collect(Collectors.toList());

        return new PageImpl<>(postDTOList, entityPage.getPageable(), entityPage.getTotalElements());
    }

    public PageImpl<PostDTO> filterCriteriaBuilder(int page,int size,PostFilterDTO filterDTO){
        PageImpl<PostEntity> entityPage = customRepository.filterCriteriaBuilder(page,size,filterDTO);

        List<PostDTO> postDTOList = entityPage.stream().map(postEntity -> {
            PostDTO dto = toDto(postEntity);
            return dto;
        }).collect(Collectors.toList());

        return new PageImpl<>(postDTOList,entityPage.getPageable(),entityPage.getTotalElements());
    }

    public PostDTO toDto(PostEntity entity){
        PostDTO dto = new PostDTO();
        dto.setId(entity.getId());
        dto.setRooms(entity.getRooms());
        dto.setFloor(entity.getFloor());
        dto.setPrice(entity.getPrice());
        dto.setAddressDTO(addressService.toDto(entity.getAddress(),"EN"));
        dto.setProfileDTO(profileService.toDto(entity.getProfile()));
        dto.setArea(entity.getArea());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

}
