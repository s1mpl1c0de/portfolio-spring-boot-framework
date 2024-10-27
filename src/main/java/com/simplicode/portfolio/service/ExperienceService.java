package com.simplicode.portfolio.service;

import com.simplicode.portfolio.dto.request.ExperienceRequest;
import com.simplicode.portfolio.dto.response.ExperienceResponse;
import com.simplicode.portfolio.dto.response.GlobalResponse;
import com.simplicode.portfolio.exception.NotFoundException;
import com.simplicode.portfolio.model.Experience;
import com.simplicode.portfolio.repository.ExperienceRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExperienceService {

    private final ModelMapper modelMapper;
    private final ExperienceRepository experienceRepository;
    private final CalendarService calendarService;

    public void save(ExperienceRequest experienceRequest) {
        experienceRepository.save(modelMapper.map(experienceRequest, Experience.class));
    }

    public GlobalResponse findAll() {
        List<ExperienceResponse> experienceResponses = experienceRepository.findAll().stream()
           .map(experience -> {
               ExperienceResponse experienceResponse = modelMapper.map(experience, ExperienceResponse.class);
               experienceResponse.setStartedMonth(calendarService.getMonthAbbr(experience.getStartedMonth()));
               experienceResponse.setEndedMonth(calendarService.getMonthAbbr(experience.getEndedMonth()));
               return experienceResponse;
           }).toList();

        return new GlobalResponse()
           .setCount(experienceResponses.size())
           .setResults(experienceResponses);
    }

    public ExperienceResponse findById(Long id) {
        Experience experience = experienceRepository.findById(id)
           .orElseThrow(() -> new NotFoundException("Experience not found"));

        return modelMapper.map(experience, ExperienceResponse.class)
           .setPeriod(getPeriod(experience));
    }

    public void updateById(Long id, ExperienceRequest experienceRequest) {
        ExperienceResponse experienceResponse = findById(id);

        experienceRepository.updateById(
           experienceResponse.getId(),
           modelMapper.map(experienceRequest, Experience.class)
        );
    }

    public void deleteById(Long id) {
        ExperienceResponse experienceResponse = findById(id);
        experienceRepository.deleteById(experienceResponse.getId());
    }

}
