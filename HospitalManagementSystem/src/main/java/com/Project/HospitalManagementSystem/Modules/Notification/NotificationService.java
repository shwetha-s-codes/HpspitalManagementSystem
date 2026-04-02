package com.Project.HospitalManagementSystem.Modules.Notification;

import com.Project.HospitalManagementSystem.Modules.DTO.NotificationPayload;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface NotificationService {

    public SseEmitter subscribe(String doctorId);
    public void notifyDoctor(String doctorId, NotificationPayload payload);
    public SseEmitter subscribeAdmin(String adminId);
    public void notifyAdmin(String adminId, NotificationPayload payload);
}
