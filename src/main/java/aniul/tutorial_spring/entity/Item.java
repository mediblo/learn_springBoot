package aniul.tutorial_spring.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="item")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 자재 ID
    @Column(nullable = false, unique = true)
    private String name; // 자재명
    @Column(nullable = true)
    private long quantity; // 자재 현 수량
    private String standard; // 자재 규격
    @Column(name="alert_quantity")
    private Long alertQuantity; // 알람 최소 자재 수량
    private LocalDateTime createdAt; // 생성일
    private LocalDateTime updatedAt; // 수정일

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // 신규 자재 등록을 위한 생성자
    public Item(String name, Long quantity, String standard, Long alertQuantity) {
        this.name = name;
        this.quantity = (quantity != null) ? quantity : 0L;
        this.standard = standard;
        this.alertQuantity = (alertQuantity != null) ? alertQuantity : 0L;
    }

    public void update(String name, String standard, Long alertQuantity) {
        this.name = name;
        this.standard = standard;
        this.alertQuantity = alertQuantity != null ? alertQuantity : 0L;
    }
}
