package com.hackerrank.sample.restController;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.hackerrank.sample.controller.SampleController;
import com.hackerrank.sample.dto.StringRequest;
import com.hackerrank.sample.entity.RecordEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Sample")
@RequestMapping(
    value = "/endpoint",
    produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class SampleRestController {
    private static final Logger logger = LoggerFactory.getLogger(SampleRestController.class);

    private final SampleController sampleController;

    @Autowired
    public SampleRestController(final SampleController sampleController) {
        this.sampleController = sampleController;
    }

    @ApiOperation(
        value = "Get all records",
        nickname = "GetRecords"
    )
    @GetMapping("/select")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<RecordEntity>> getRecords() {
        logger.info("Endpoint select called.");
        final List<RecordEntity> results =  sampleController.getRecords();

        if(results.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(results, HttpStatus.OK);
        }
    }

    @ApiOperation(
            value = "Get a record by id",
            nickname = "GetRecordById"
    )
    @GetMapping("/select/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RecordEntity> getRecordById(@PathVariable @NotBlank final Long id) {
        logger.info("Endpoint select called.");
        final Optional<RecordEntity> record = sampleController.getRecordById(id);

        if(!record.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(record.get(), HttpStatus.OK);
        }
    }

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RecordEntity> insertRecord(@RequestBody @NotBlank @Valid final StringRequest request){
        logger.info("Endpoint insert called");
        final RecordEntity record = sampleController.insert(request);
        return new ResponseEntity<>(record, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity deleteById(@PathVariable @NotBlank final Long id){
        logger.info("Endpoint delete called");
        sampleController.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
