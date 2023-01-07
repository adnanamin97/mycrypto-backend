package com.mycrypto.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "assets")
public class UserAssets {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String assetId;

    @Column(name = "date_purchased")
    private LocalDateTime datePurchased;

    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

}
