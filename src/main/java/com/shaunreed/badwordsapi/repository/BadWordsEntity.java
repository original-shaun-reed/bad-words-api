package com.shaunreed.badwordsapi.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "badwords")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BadWordsEntity {
    @Id
    private Long id;

    private String word;
}
