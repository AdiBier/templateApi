package com.ab.templateApi.service;

import com.ab.templateApi.dao.entity.Room;
import com.ab.templateApi.dao.entity.WorkHour;
import com.ab.templateApi.dao.repository.WorkHourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkHourService {

    private final WorkHourRepository workHourRepository;

    @Autowired
    public WorkHourService(WorkHourRepository workHourRepository) {
        this.workHourRepository = workHourRepository;
    }

    public Optional<WorkHour> findById(Long id) {
        return workHourRepository.findById(id);
    }

    public Iterable<WorkHour> findAll() {
        return workHourRepository.findAll();
    }

    public WorkHour save(WorkHour newWorkHour){
        WorkHour workHour = new WorkHour(newWorkHour.getDay(), newWorkHour.getOpeningTime(), newWorkHour.getClosingTime());
        return workHourRepository.save(workHour);
    }

    public WorkHour update(WorkHour workHour){
        return workHourRepository.save(workHour);
    }

    public void delete(Long id){
        workHourRepository.deleteById(id);
    }

    @EventListener(ApplicationEvent.class)
    public void fillDb(){
        save(new WorkHour(1, "12:00", "23:30"));
        save(new WorkHour(2, "10:00", "22:00"));
        save(new WorkHour(3, "09:00", "23:00"));
        save(new WorkHour(4, "09:00", "23:00"));
        save(new WorkHour(5, "13:00", "23:30"));
        save(new WorkHour(6, "09:00", "23:00"));
        save(new WorkHour(7, "09:00", "23:00"));
    }
}
