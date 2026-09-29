package aniul.tutorial_spring.controller;

import aniul.tutorial_spring.dto.StockRequestDto;
import aniul.tutorial_spring.dto.StockResponseDto;
import aniul.tutorial_spring.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    @PostMapping
    public ResponseEntity<StockResponseDto> processStock(@RequestBody StockRequestDto requestDto) {
        StockResponseDto response = stockService.processStock(requestDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/items/{itemId}")
    public ResponseEntity<List<StockResponseDto>> getLogsByItemId(@PathVariable Long itemId) {
        List<StockResponseDto> logs = stockService.getLogByItemId(itemId);
        return ResponseEntity.ok(logs);
    }

}
