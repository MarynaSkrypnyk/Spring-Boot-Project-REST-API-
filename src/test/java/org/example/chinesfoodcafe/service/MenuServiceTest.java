package org.example.chinesfoodcafe.service;

import org.example.chinesfoodcafe.entity.Menu;
import org.example.chinesfoodcafe.repository.MenuRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MenuServiceTest {

    @Mock
    private MenuRepository menuRepository;

    @InjectMocks
    private MenuService menuService;

    @Test
    public void create_dish(){
        Menu menu = new Menu("Chiken","fresh",46.8);
        when(menuRepository.save(menu)).thenReturn(menu);
        Menu createdMenu = menuService.create(menu);
        assertEquals(menu, createdMenu);
    }
    @Test
    public void delete_dish(){
        Long id = 1L;
        menuService.deleteDishById(id);
        verify(menuRepository, times(1)).deleteById(id);
    }
    @Test
    public void update_dish(){
        Long id = 1L;
        Menu menu = new Menu("Chiken","fresh",46.8);
        Menu menu2 = new Menu("Beaf","fresh",100);
        when(menuRepository.findById(id)).thenReturn(Optional.of(menu));
        when(menuRepository.save(menu)).thenReturn(menu2);
        Menu result = menuService.updateDish(menu2, id);
        assertEquals(menu, result);
    }
    @Test
    public void get_all_dish(){
        PageRequest pageRequest = PageRequest.of(0, 10);
        List<Menu> menuList = new ArrayList<>();
        menuList.add(new Menu("Chiken","fresh",46.8));
        menuList.add(new Menu("Beaf","fresh",100));
        Page<Menu> page = new PageImpl<>(menuList);
        when(menuRepository.getAllDish(pageRequest)).thenReturn(page);
        List<Menu> result = menuService.getAllDish(pageRequest);
        assertEquals(menuList, result);

    }
}
