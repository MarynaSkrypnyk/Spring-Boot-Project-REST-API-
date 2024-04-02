package org.example.chinesfoodcafe.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.chinesfoodcafe.entity.Menu;
import org.example.chinesfoodcafe.exeption.DishIsExistException;
import org.example.chinesfoodcafe.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@CacheConfig(cacheNames = "menu")
@RequiredArgsConstructor
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Cacheable(value = "menu", key = "#menu.dish")
    public Menu create(Menu menu) {
        if (menuRepository.existsByDish(menu.getDish())) {
            throw new DishIsExistException();
        }
        log.info("create menu: {}", menu);
        return menuRepository.save(menu);
    }

    @CacheEvict(value = "menu")
    public String deleteDishById(Long id) {
        menuRepository.deleteById(id);

        log.info("delete dish: {}", id);
        return "Dish delete from menu";
    }

    @CachePut(value = "menu", key = "#menu.dish")
    public Menu updateDish(Menu menu, Long id) {
        Menu updateDish = menuRepository.findById(id).orElseThrow();
        updateDish.setDish(menu.getDish());
        updateDish.setDescription(menu.getDescription());
        updateDish.setPrice(menu.getPrice());
        log.info("update dish: {}", id);
        return menuRepository.save(updateDish);
    }

    public List<Menu> getAllDish(PageRequest pageRequest) {
        Page<Menu> page = menuRepository.getAllDish(pageRequest);
        log.info("pageRequest getAllDish: {}", pageRequest);
        return page.getContent();
    }
}
