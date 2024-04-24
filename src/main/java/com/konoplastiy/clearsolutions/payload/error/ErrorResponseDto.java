package com.konoplastiy.clearsolutions.payload.error;

import com.konoplastiy.clearsolutions.config.handler.GlobalExceptionHandler;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * This class represents an error response DTO (Data Transfer Object) used to convey
 * information about errors that occur during API requests.
 *
 * <p>The ErrorResponseDto contains fields such as apiPath, errorCode, errorMessage,
 * and errorTime, providing details about the API path invoked, the error code,
 * the error message, and the time when the error occurred, respectively.
 *
 * @see GlobalExceptionHandler
 */
@Data
@AllArgsConstructor
@Schema(
        name = "ErrorResponse",
        description = "Schema to hold error response information"
)
public class ErrorResponseDto {

    @Schema(
            description = "API path invoked by client"
    )
    private String apiPath;

    @Schema(
            description = "Error code representing the error happened"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "Error message representing the error happened"
    )
    private String errorMessage;

    @Schema(
            description = "Time representing when the error happened"
    )
    private LocalDateTime errorTime;

}
