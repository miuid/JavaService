package com.hackerrank.sample.controller;

import java.util.List;
import java.util.Optional;

import com.hackerrank.sample.dto.StringRequest;
import com.hackerrank.sample.entity.RecordEntity;
import com.hackerrank.sample.service.RecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
    static private final Logger logger = LoggerFactory.getLogger(SampleController.class);

    final RecordService recordService;

    @Autowired
    public SampleController(final RecordService recordService){
        this.recordService = recordService;
    }

    public List<RecordEntity> getRecords(){
        logger.info("Select all records.");
        return this.recordService.getAllRecords();
    }

    public Optional<RecordEntity> getRecordById(final Long id) {
        logger.info("Select recode by id " + id);
        return this.recordService.getRecordById(id);
    }

    public RecordEntity insert(final StringRequest request) {
        logger.info("Insert record " + request.getName());
        final RecordEntity recordEntity = new RecordEntity();
        recordEntity.setName(request.getName());
        recordEntity.setMessage(request.getMessage());
        return this.recordService.insertRecord(recordEntity);
    }

    public void deleteById(final Long id) {
        logger.info("Delete record by id " + id);
        this.recordService.deleteRecord(id);
    }
}
