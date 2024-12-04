package by.trofimov.time_management_system.dto;

import java.time.LocalDateTime;

public record BaseResponse(String message, LocalDateTime timestamp) {
}
