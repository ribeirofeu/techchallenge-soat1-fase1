package com.fiap.techfood.application.dto.request;

import com.fiap.techfood.domain.order.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;

@Data
public class SearchOrdersRequestDTO {
    @NotNull
    private OrderStatus status;

    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime startDatetime;

    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime endDatetime;
}
