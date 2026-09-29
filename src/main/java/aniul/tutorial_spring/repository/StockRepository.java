package aniul.tutorial_spring.repository;

import aniul.tutorial_spring.entity.StockLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<StockLog, Long> {
    List<StockLog> findByItemIdOrderByCreatedAtDesc(Long itemId);
}
