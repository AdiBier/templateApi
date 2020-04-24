package com.ab.templateApi.api;

import com.ab.templateApi.dao.entity.WorkHour;
import com.ab.templateApi.service.WorkHourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/workHour")
public class WorkHourApi {

    private final WorkHourService workHourService;

    @Autowired
    public WorkHourApi(WorkHourService workHourService) {
        this.workHourService = workHourService;
    }

    @PostMapping
    public WorkHour addWorkHour(@Valid @RequestBody WorkHour workHour){
        return workHourService.save(workHour);
    }

    @PutMapping
    public WorkHour updateWorkHour(@Valid @RequestBody WorkHour workHour){
        return workHourService.update(workHour);
    }
}
