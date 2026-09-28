package aniul.tutorial_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import aniul.tutorial_spring.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    boolean existsByName(String name);


}
