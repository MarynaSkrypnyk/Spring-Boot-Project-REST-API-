package org.example.chinesfoodcafe.repository;

import org.example.chinesfoodcafe.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    @Query(nativeQuery = true, value = "select * from menu")
    Page<Menu> getAllDish(PageRequest pageRequest);
    boolean existsByDish(String dish);

}
