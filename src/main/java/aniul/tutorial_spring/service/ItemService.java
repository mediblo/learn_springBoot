package aniul.tutorial_spring.service;

import aniul.tutorial_spring.dto.ItemRequestDto;
import aniul.tutorial_spring.dto.ItemResponseDto;
import aniul.tutorial_spring.entity.Item;
import aniul.tutorial_spring.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public ItemResponseDto createItem(ItemRequestDto requestDto){
        if(itemRepository.existsByName(requestDto.getName())) {
            throw new IllegalArgumentException("이미 존재하는 자재명 : " + requestDto.getName());
        }
        else{
            Item item = new Item(
                    requestDto.getName(),
                    requestDto.getQuantity(),
                    requestDto.getStandard(),
                    requestDto.getAlertQuantity()
            );
            Item savedItem = itemRepository.save(item);
            return new ItemResponseDto(savedItem);
        }
    }

    public List<ItemResponseDto> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return items.stream().map(ItemResponseDto::new).toList();
    }

    public ItemResponseDto getItemById(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("자재를 찾을 수 없습니다."));
        return new ItemResponseDto(item);
    }

    @Transactional
    public ItemResponseDto updateItem(Long id, ItemRequestDto requestDto) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("자재를 찾을 수 없습니다."));
        item.update(requestDto.getName(), requestDto.getStandard(), requestDto.getAlertQuantity());
        return new ItemResponseDto(item);
    }

    @Transactional
    public void deleteItem(Long id){
        Item item = itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("자재를 찾을 수 없습니다."));
        itemRepository.deleteById(id);
    }
}
