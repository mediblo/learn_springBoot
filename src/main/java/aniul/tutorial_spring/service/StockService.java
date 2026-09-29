package aniul.tutorial_spring.service;

import aniul.tutorial_spring.dto.StockRequestDto;
import aniul.tutorial_spring.dto.StockResponseDto;
import aniul.tutorial_spring.entity.Item;
import aniul.tutorial_spring.entity.StockLog;
import aniul.tutorial_spring.entity.StockType;
import aniul.tutorial_spring.repository.ItemRepository;
import aniul.tutorial_spring.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StockService {
    private final ItemRepository itemRepository;
    private final StockRepository stockRepository;

    @Transactional
    public StockResponseDto processStock(StockRequestDto requestDto) {
        Item item = itemRepository.findById(requestDto.getItemId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 자재입니다."));

        if (requestDto.getType() == StockType.IN) {
            item.increaseQuantity(requestDto.getQuantity());
        } else if ( requestDto.getType() == StockType.OUT) {
            item.decreaseQuantity(requestDto.getQuantity());
        }

        StockLog stockLog = new StockLog(
                item.getId(),
                requestDto.getQuantity(),
                requestDto.getType(),
                requestDto.getReason()
        );
        StockLog savedLog = stockRepository.save(stockLog);

        return new StockResponseDto(savedLog);
    }

    public List<StockResponseDto> getLogByItemId(Long itemId) {
        List<StockLog> logs = stockRepository.findByItemIdOrderByCreatedAtDesc(itemId);
        return logs.stream().map(StockResponseDto::new).toList();
    }
}
