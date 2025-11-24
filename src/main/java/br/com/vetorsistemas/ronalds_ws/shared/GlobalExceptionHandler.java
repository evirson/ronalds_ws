package br.com.vetorsistemas.ronalds_ws.shared;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String, String> details = new LinkedHashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            details.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ErrorResponse body = ErrorResponse.builder()
                .timestamp(OffsetDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .path(request.getDescription(false))
                .details(details)
                .message("Falha de valida\u00E7\u00E3o")
                .build();
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorResponse> handleAppException(AppException ex, HttpServletRequest req) {
        HttpStatus status = ex.getStatus();
        ErrorResponse body = ErrorResponse.builder()
                .timestamp(OffsetDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(ex.getMessage())
                .path(req.getRequestURI())
                .build();
        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex, HttpServletRequest req) {
        Map<String, String> details = new LinkedHashMap<>();
        for (ConstraintViolation<?> v : ex.getConstraintViolations()) {
            details.put(v.getPropertyPath().toString(), v.getMessage());
        }
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse body = ErrorResponse.builder()
                .timestamp(OffsetDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .path(req.getRequestURI())
                .details(details)
                .message("Falha de valida\u00E7\u00E3o")
                .build();
        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex, HttpServletRequest req) {
        String rootMsg = rootMessage(ex);
        String constraint = extractConstraintName(ex);
        String field = extractFieldFromMessage(rootMsg);
        String sqlState = findSqlState(ex);
        String paramIndex = extractParamIndex(rootMsg);
        String offendingValue = extractOffendingValue(rootMsg);

        String type = inferConstraintType(rootMsg, constraint);
        if ("22001".equals(sqlState) || (rootMsg != null && rootMsg.toLowerCase().contains("data truncation"))) {
            type = "data_truncation";
        }

        HttpStatus status = chooseStatus(type);
        String message = switch (type) {
            case "unique" -> "Viola\u00E7\u00E3o de chave \u00FAnica (valor duplicado)";
            case "not_null" -> "Campo obrigat\u00F3rio ausente ou nulo";
            case "foreign_key" -> "Viola\u00E7\u00E3o de chave estrangeira (refer\u00EAncia inv\u00E1lida)";
            case "check" -> "Viola\u00E7\u00E3o de restri\u00E7\u00E3o de dom\u00EDnio (CHECK)";
            case "data_truncation" -> "Valor excede o tamanho permitido para o campo";
            default -> "Viola\u00E7\u00E3o de integridade de dados";
        };
        Map<String, String> details = new LinkedHashMap<>();
        if (field != null && !field.isBlank()) details.put("field", field);
        if (constraint != null && !constraint.isBlank()) details.put("constraint", constraint);
        if (paramIndex != null) details.put("parameterIndex", paramIndex);
        if (offendingValue != null && !offendingValue.isBlank()) details.put("value", offendingValue);
        if (sqlState != null) details.put("sqlState", sqlState);
        if (rootMsg != null && !rootMsg.isBlank()) details.put("cause", rootMsg);

        ErrorResponse body = ErrorResponse.builder()
                .timestamp(OffsetDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .path(req.getRequestURI())
                .details(details)
                .build();
        return new ResponseEntity<>(body, status);
    }

    private String findSqlState(Throwable t) {
        Throwable cur = t;
        while (cur != null) {
            if (cur instanceof java.sql.SQLException se) {
                String s = se.getSQLState();
                if (s != null && !s.isBlank()) return s;
            }
            cur = cur.getCause();
        }
        return null;
    }

    private String extractParamIndex(String message) {
        if (message == null) return null;
        Matcher m = Pattern.compile("(?i)parameter\s*#(\\d+)").matcher(message);
        if (m.find()) return m.group(1);
        return null;
    }

    private String extractOffendingValue(String message) {
        if (message == null) return null;
        Matcher m = Pattern.compile("(?i)parameter\s*#\\d+\s*-\s*(.*?)\s*(?:\\[|$)").matcher(message);
        if (m.find()) return m.group(1);
        return null;
    }

    private String inferConstraintType(String message, String constraintName) {
        String m = message != null ? message.toLowerCase() : "";
        String c = constraintName != null ? constraintName.toLowerCase() : "";
        if (m.contains("unique") || m.contains("primary") || c.startsWith("uk_") || c.startsWith("pk_")) return "unique";
        if (m.contains("not null") || c.startsWith("nn_")) return "not_null";
        if (m.contains("foreign key") || c.startsWith("fk_")) return "foreign_key";
        if (m.contains("check constraint") || c.startsWith("ck_")) return "check";
        return "integrity";
    }

    private HttpStatus chooseStatus(String type) {
        return switch (type) {
            case "unique", "foreign_key" -> HttpStatus.CONFLICT; // 409
            case "not_null", "check", "integrity" -> HttpStatus.BAD_REQUEST; // 400
            default -> HttpStatus.BAD_REQUEST;
        };
    }

    private String extractConstraintName(Throwable t) {
        Throwable cur = t;
        while (cur != null) {
            // Prefer Hibernate's exception when present
            if (cur instanceof org.hibernate.exception.ConstraintViolationException h) {
                if (h.getConstraintName() != null && !h.getConstraintName().isBlank()) {
                    return h.getConstraintName();
                }
            }
            cur = cur.getCause();
        }
        String msg = rootMessage(t);
        if (msg == null) return null;
        // Try to parse: ... constraint "NAME" ... or ... CONSTRAINT NAME ...
        Pattern pQuoted = Pattern.compile("(?i)constraint\\s+\"?([\\w$]+)\"?");
        Matcher mq = pQuoted.matcher(msg);
        if (mq.find()) return mq.group(1);
        return null;
    }

    private String extractFieldFromMessage(String message) {
        if (message == null) return null;
        Pattern p = Pattern.compile("(?i)(column|field|property)\\s+\"?([\\w$]+)\"?");
        Matcher m = p.matcher(message);
        if (m.find()) return m.group(2);
        return null;
    }

    private String rootMessage(Throwable t) {
        Throwable r = getRootCause(t);
        String msg = r != null ? r.getMessage() : null;
        return msg != null ? msg : t.getMessage();
    }

    private Throwable getRootCause(Throwable t) {
        Throwable r = t;
        while (r.getCause() != null && r.getCause() != r) {
            r = r.getCause();
        }
        return r;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex, HttpServletRequest req) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse body = ErrorResponse.builder()
                .timestamp(OffsetDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(ex.getMessage())
                .path(req.getRequestURI())
                .build();
        return new ResponseEntity<>(body, status);
    }
}


