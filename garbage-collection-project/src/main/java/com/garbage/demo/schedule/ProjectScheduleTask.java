package com.garbage.demo.schedule;

import com.garbage.demo.service.IGarbageService;
import com.garbage.demo.service.ITransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@EnableScheduling
@EnableAsync
public class ProjectScheduleTask {
    @Autowired
    ITransportService transportService;
    @Autowired
    IGarbageService garbageService;
    @Async
    @Scheduled(fixedDelay = 500)
    public void updateProjectStatus(){
        transportService.UpdateStatus();
        garbageService.removeAllTransportId();
    }

}