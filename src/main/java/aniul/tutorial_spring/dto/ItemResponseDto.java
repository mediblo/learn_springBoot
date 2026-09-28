package aniul.tutorial_spring.dto;


import aniul.tutorial_spring.entity.Item;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ItemResponseDto {
    private Long id;
    private String name;
    private String standard;
    private Long quantity;
    private Long alertQuantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ItemResponseDto(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.standard = item.getStandard();
        this.quantity = item.getQuantity();
        this.alertQuantity = item.getAlertQuantity();
        this.createdAt = item.getCreatedAt();
        this.updatedAt = item.getUpdatedAt();
    }
}
