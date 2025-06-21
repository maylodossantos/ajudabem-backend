package com.ajudabem.backend.model;

import com.ajudabem.backend.dto.news.request.RequestNewsDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "news")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_id")
    private User user;

    private String title;
    private String subtitle;
    private String content;
    private String city;
    private String country;

    @CreationTimestamp
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime last_update;

    private Boolean deleted;

    public News(User user, RequestNewsDTO requestProductDTO) {
        this.user = user;
        this.title = requestProductDTO.title();
        this.subtitle = requestProductDTO.subtitle();
        this.content = requestProductDTO.content();
        this.city = requestProductDTO.city();
        this.country = requestProductDTO.country();
        this.deleted = false;
    }
}
