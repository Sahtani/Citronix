package com.youcode.citronix.common.controller;

import com.youcode.citronix.common.service.GenericService;
import com.youcode.citronix.farm.application.dto.PagedResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor

public abstract class GenericController<RequestDTO, ResponseDTO, ID> {

    protected final GenericService<RequestDTO, ResponseDTO, ID> service;


    @PostMapping
    public ResponseEntity<ResponseDTO> create(@RequestBody RequestDTO requestDto) {
        ResponseDTO responseDto = service.save(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable ID id) {
        ResponseDTO responseDto = service.findById(id);
        return responseDto != null ? ResponseEntity.ok(responseDto) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<PagedResponse<ResponseDTO>> getAll(Pageable pageable) {
        PagedResponse<ResponseDTO> responsePage = service.findAll(pageable);
        return ResponseEntity.ok(responsePage);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> update(@PathVariable ID id, @RequestBody @Valid RequestDTO requestDto) {
        ResponseDTO updatedDto = service.update(id, requestDto);
        return updatedDto != null ? ResponseEntity.ok(updatedDto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}