package aniul.tutorial_spring.dto;

import aniul.tutorial_spring.entity.StockType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StockRequestDto {
    private Long itemId;
    private Long quantity;
    private StockType type;
    private String reason;
}