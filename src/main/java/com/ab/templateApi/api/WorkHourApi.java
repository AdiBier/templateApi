package com.ab.templateApi.api;

import com.ab.templateApi.dao.entity.WorkHour;
import com.ab.templateApi.dao.handler.WorkHourDto;
import com.ab.templateApi.response.ServerResponse;
import com.ab.templateApi.response.ServerResponseConstants;
import com.ab.templateApi.service.WorkHourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static com.ab.templateApi.dao.handler.WorkHourDto.toWorkHourDto;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/workHour")
public class WorkHourApi {

    private final WorkHourService workHourService;

    @Autowired
    public WorkHourApi(WorkHourService workHourService) {
        this.workHourService = workHourService;
    }

    @GetMapping("/all")
    public Iterable<WorkHourDto> getAllWorkHours() {
        Iterable<WorkHour> workHourIterable = workHourService.findAll();
        if (workHourIterable.iterator().hasNext()) {
            return StreamSupport.stream(workHourIterable.spliterator(), false)
                    .map(workHour -> {
                        return new WorkHourDto(workHour.getWorkHourId(), workHour.getRoom(), workHour.getDay(), workHour.getOpeningTime(), workHour.getClosingTime(),
                                ServerResponseConstants.OK_MSG, ServerResponseConstants.OK_CODE);
                    }).collect(toList());
        }
        List<WorkHourDto> workHourDtoList = new ArrayList<>();
        workHourDtoList.add(new WorkHourDto(null, null, null, null, null, ServerResponseConstants.TAE1004_MSG, ServerResponseConstants.TAE1004_CODE));
        return workHourDtoList;
    }

    @GetMapping
    public WorkHourDto getById(@RequestParam Long id) {
        Optional<WorkHour> workHour = workHourService.findById(id);
        if(workHour.isPresent()){
            return toWorkHourDto(workHour.get(), ServerResponseConstants.OK_MSG, ServerResponseConstants.OK_CODE);
        }
        return new WorkHourDto(null, null, null, null, null, ServerResponseConstants.TAE1004_MSG, ServerResponseConstants.TAE1004_CODE);
    }

    @PostMapping
    public WorkHour addWorkHour(@Valid @RequestBody WorkHour workHour){
        return workHourService.save(workHour);
    }

    @PutMapping
    public WorkHour updateWorkHour(@Valid @RequestBody WorkHour workHour){
        return workHourService.update(workHour);
    }

    @DeleteMapping
    public ServerResponse deleteWorkHourById(@RequestParam Long id) {
        if (workHourService.findById(id).isPresent()){
            workHourService.delete(id);
            return new ServerResponse(ServerResponseConstants.OK_MSG, ServerResponseConstants.OK_CODE);
        }
        return new ServerResponse(ServerResponseConstants.TAE1004_MSG, ServerResponseConstants.TAE1004_CODE);
    }
}
