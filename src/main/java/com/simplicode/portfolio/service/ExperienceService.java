package com.simplicode.portfolio.service;

import com.simplicode.portfolio.dto.request.ExperienceRequest;
import com.simplicode.portfolio.dto.response.ExperienceResponse;
import com.simplicode.portfolio.dto.response.PageNumberPaginationResponse;
import com.simplicode.portfolio.exception.NotFoundException;
import com.simplicode.portfolio.helper.AuthenticationHelper;
import com.simplicode.portfolio.helper.ExperienceHelper;
import com.simplicode.portfolio.model.Experience;
import com.simplicode.portfolio.model.Page;
import com.simplicode.portfolio.repository.ExperienceRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExperienceService {

    private final AuthenticationHelper authenticationHelper;
    private final ExperienceHelper experienceHelper;
    private final ExperienceRepository experienceRepository;
    private final ModelMapper modelMapper;

    public void save(ExperienceRequest experienceRequest) {
        Experience experience = modelMapper.map(experienceRequest, Experience.class)
           .setCreatedDate(LocalDateTime.now())
           .setLastModifiedDate(null)
           .setUserId(authenticationHelper.getRequestUserId());

        experienceRepository.save(experience);
    }

    public Integer countAllByUserId() {
        return experienceRepository.countAllByUserId(authenticationHelper.getRequestUserId());
    }

    public PageNumberPaginationResponse<ExperienceResponse> findAll(Page page) {
        List<ExperienceResponse> results = experienceRepository
           .findAllByUserId(authenticationHelper.getRequestUserId(), page).stream()
           .map(experience -> modelMapper.map(experience, ExperienceResponse.class)
              .setPeriod(experienceHelper.getPeriod(experience))
           ).toList();

        return new PageNumberPaginationResponse<ExperienceResponse>()
           .setCount(results.size())
           .setNext(page.getNext())
           .setPrevious(page.getPrevious())
           .setResults(results);
    }

    public ExperienceResponse findById(Long id) {
        Experience experience = experienceRepository.findById(id)
           .orElseThrow(() -> new NotFoundException("Experience not found"));

        return modelMapper.map(experience, ExperienceResponse.class)
           .setPeriod(experienceHelper.getPeriod(experience));
    }

    public void updateById(Long id, ExperienceRequest experienceRequest) {
        ExperienceResponse experienceResponse = findById(id);

        Experience experience = modelMapper.map(experienceRequest, Experience.class)
           .setLastModifiedDate(LocalDateTime.now());

        experienceRepository.updateById(experienceResponse.getId(), experience);
    }

    public void deleteById(Long id) {
        ExperienceResponse experienceResponse = findById(id);
        experienceRepository.deleteById(experienceResponse.getId());
    }

}
