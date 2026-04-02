package com.Project.HospitalManagementSystem.Modules.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationPayload {
    private String message;
    private String type;
    private String doctorId;
    private String shiftId;
    private String day;
    private String startTime;
    private String endTime;
    private LocalDateTime timestamp;
}
