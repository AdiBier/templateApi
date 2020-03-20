package com.ab.templateApi.dao.repository;

import com.ab.templateApi.dao.entity.WorkHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkHourRepository extends JpaRepository<WorkHour, Long> {
}
