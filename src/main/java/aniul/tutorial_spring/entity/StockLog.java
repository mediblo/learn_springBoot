package aniul.tutorial_spring.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "stock_logs")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 로그 id

    @Column
    private Long itemId; // 자재 id

    @Column(nullable = false)
    private Long quantity; // 수량

    @Column
    @Enumerated(EnumType.STRING)
    private StockType type; // 입고, 출고

    @Column
    private String reason; // 사유

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

    public StockLog(Long itemId, Long quantity, StockType type, String reason) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.type = type;
        this.reason = reason;
    }
}
