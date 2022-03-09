package com.shaunreed.badwordsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BadWordsRepository extends JpaRepository<BadWordsEntity, Long> {

}
