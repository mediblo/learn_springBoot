package aniul.tutorial_spring.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemRequestDto {
    private String name;
    private String standard;
    private Long quantity;
    private Long alertQuantity;
}
