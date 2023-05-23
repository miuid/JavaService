package com.hackerrank.sample.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.hackerrank.sample.dto.StringResponse;
import com.hackerrank.sample.entity.RecordEntity;
import com.hackerrank.sample.repository.RecordEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordService {
    final RecordEntityRepository recordEntityRepository;

    @Autowired
    public RecordService(final RecordEntityRepository recordEntityRepository) {
        this.recordEntityRepository = recordEntityRepository;
    }

    public List<RecordEntity> getAllRecords() {
        final List<RecordEntity> records = this.recordEntityRepository.findAll();
//        final String result = records.stream()
//                .map(RecordEntity::getName)
//                .collect(Collectors.joining(","));
        return records;
    }

    public Optional<RecordEntity> getRecordById(final Long id) {
        return this.recordEntityRepository.findById(id);
    }

    public RecordEntity insertRecord(final RecordEntity record) {
        return this.recordEntityRepository.save(record);
    }

    public void deleteRecord(final Long id) {
        this.recordEntityRepository.deleteById(id);
    }
}
