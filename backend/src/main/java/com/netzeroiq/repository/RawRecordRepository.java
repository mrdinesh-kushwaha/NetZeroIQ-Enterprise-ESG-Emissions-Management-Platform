package com.netzeroiq.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.netzeroiq.model.RawRecord;
import com.netzeroiq.model.UploadBatch;

import java.util.UUID;

@Repository
public interface RawRecordRepository extends JpaRepository<RawRecord, UUID> {}
