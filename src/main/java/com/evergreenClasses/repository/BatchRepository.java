package com.evergreenClasses.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evergreenClasses.model.Batch;

public interface BatchRepository extends JpaRepository<Batch,Long>{
   List<Batch> findByBatchNameContainingIgnoreCase(String batchName);
   Batch findByBatchId(Long batchId);

}
