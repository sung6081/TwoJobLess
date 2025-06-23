package opgg.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "post")
public class InvenPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    public InvenPost() {
        this.createdAt = new Date(); // 현재 시간 자동 설정
    }

    // 추가 생성자나 getter/setter 필요 시 여기에 작성
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public Date getCreatedAt() { return createdAt; }

    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }
}