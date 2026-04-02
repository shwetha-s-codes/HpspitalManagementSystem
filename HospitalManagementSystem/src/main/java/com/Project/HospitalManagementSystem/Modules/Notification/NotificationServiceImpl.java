package com.Project.HospitalManagementSystem.Modules.Notification;

import com.Project.HospitalManagementSystem.Modules.DTO.NotificationPayload;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class NotificationServiceImpl implements NotificationService{

    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();

    public SseEmitter subscribe(String doctorId) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emitters.put(doctorId, emitter);

        emitter.onCompletion(() -> emitters.remove(doctorId));
        emitter.onTimeout(() -> emitters.remove(doctorId));
        emitter.onError(e -> emitters.remove(doctorId));

        return emitter;
    }

    public void notifyDoctor(String doctorId, NotificationPayload payload) {
        SseEmitter emitter = emitters.get(doctorId);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event()
                        .name("notification")
                        .data(payload));
            } catch (IOException e) {
                emitters.remove(doctorId);
            }
        }
    }
    public SseEmitter subscribeAdmin(String adminId) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emitters.put("ADMIN_" + adminId, emitter);

        emitter.onCompletion(() -> emitters.remove("ADMIN_" + adminId));
        emitter.onTimeout(() -> emitters.remove("ADMIN_" + adminId));
        emitter.onError(e -> emitters.remove("ADMIN_" + adminId));

        return emitter;
    }

    public void notifyAdmin(String adminId, NotificationPayload payload) {
        SseEmitter emitter = emitters.get("ADMIN_" + adminId);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event()
                        .name("notification")
                        .data(payload));
            } catch (IOException e) {
                emitters.remove("ADMIN_" + adminId);
            }
        }
    }
}
