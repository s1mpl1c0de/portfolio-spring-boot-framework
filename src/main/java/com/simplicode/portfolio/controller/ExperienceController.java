package com.simplicode.portfolio.controller;

import com.simplicode.portfolio.dto.request.ExperienceRequest;
import com.simplicode.portfolio.dto.response.ExperienceResponse;
import com.simplicode.portfolio.dto.response.PageNumberPaginationResponse;
import com.simplicode.portfolio.model.Page;
import com.simplicode.portfolio.service.ExperienceService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/experiences")
public class ExperienceController {

    private final ExperienceService experienceService;

    @PostMapping
    public ResponseEntity<Void> save(
       @Valid @RequestBody ExperienceRequest experienceRequest
    ) {
        experienceService.save(experienceRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PageNumberPaginationResponse<ExperienceResponse>> findAll(
       @RequestParam(defaultValue = "1") @Min(1) Integer pageNumber,
       @RequestParam(defaultValue = "30") @Min(1) Integer pageSize,
       HttpServletRequest httpRequest
    ) {
        Page page = new Page()
           .setCount(experienceService.countAllByUserId())
           .setRequestUrl(httpRequest.getRequestURL().toString())
           .setPageNumber(pageNumber)
           .setPageSize(pageSize);

        return new ResponseEntity<>(experienceService.findAll(page), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExperienceResponse> findById(@PathVariable Long id) {
        return new ResponseEntity<>(experienceService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateById(
       @PathVariable Long id,
       @Valid @RequestBody ExperienceRequest experienceRequest
    ) {
        experienceService.updateById(id, experienceRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        experienceService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
