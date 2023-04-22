package com.jit.rec.recipetoria.service;

import com.jit.rec.recipetoria.dto.TagDTO;
import com.jit.rec.recipetoria.entity.Tag;
import com.jit.rec.recipetoria.repository.TagRepository;
import com.jit.rec.recipetoria.security.applicationUser.ApplicationUser;
import com.jit.rec.recipetoria.security.applicationUser.ApplicationUserService;
import jakarta.persistence.EntityManager;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;
    ApplicationUserService applicationUserService;
    EntityManager entityManager;



    public TagDTO createNewTag(TagDTO tagDTO) {
        Tag newTag = new Tag();
        newTag.setName(tagDTO.name());
        newTag.setApplicationUser((ApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        return TagDTO.convertToTagDto(tagRepository.save(newTag));
    }

    public TagDTO getTagDTOById(Long tagId){
        return TagDTO.convertToTagDto(tagRepository.findById(tagId).orElseThrow(()-> new IllegalStateException("TAG_NOT_FOUND")));
    }

    public Tag getTagById(Long tagId){
        return tagRepository.findById(tagId).orElseThrow(()->new IllegalStateException("TAG_NOT_FOUND"));
    }

    public List<TagDTO> getAllTagsOfUser(){
        List<TagDTO> tagDTOList = new ArrayList<>();
        ApplicationUser currAppUser = (ApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Tag>allTags = tagRepository.findTagsByApplicationUser_Id(currAppUser.getId());
        for(Tag tag : allTags){
            tagDTOList.add(TagDTO.convertToTagDto(tag));
        }
        return tagDTOList;
    }

    public void deleteById(Long id){
        tagRepository.deleteById(id);
    }

}
