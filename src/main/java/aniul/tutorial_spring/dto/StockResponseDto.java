package aniul.tutorial_spring.dto;

import aniul.tutorial_spring.entity.StockLog;
import aniul.tutorial_spring.entity.StockType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class StockResponseDto {
    private Long id;
    private Long itemId;
    private Long quantity;
    private StockType type;
    private String reason;
    private LocalDateTime createdAt;

    public StockResponseDto(StockLog stockLog) {
        this.id = stockLog.getId();
        this.itemId = stockLog.getItemId();
        this.quantity = stockLog.getQuantity();
        this.type = stockLog.getType();
        this.reason = stockLog.getReason();
        this.createdAt = stockLog.getCreatedAt();
    }
}
