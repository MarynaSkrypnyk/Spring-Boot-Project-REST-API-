package org.example.chinesfoodcafe.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.chinesfoodcafe.entity.Menu;
import org.example.chinesfoodcafe.model.DishRequest;
import org.example.chinesfoodcafe.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("/create")
    public ResponseEntity<Menu> create(@RequestBody @Valid DishRequest dishRequest) {
        Menu menu = getMenu(dishRequest);
        log.info("create menu : {}", menu);
        menuService.create(menu);
        return new ResponseEntity<>(menu, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteDishById(@PathVariable Long id) {
        log.info("User: {}", id);

        log.info("delete dish from menu : {}", id);
        menuService.deleteDishById(id);
        return "Dish delete from menu";
    }

    @PutMapping("/{id}")
    public ResponseEntity<Menu> updateDish(@RequestBody Menu menu, @PathVariable Long id) {

        log.info("update dish in menu : {}", id);
        Menu menu1 = menuService.updateDish(menu, id);
        return new ResponseEntity<>(menu1, HttpStatus.CREATED);
    }


    @GetMapping("/list")
    public List<Menu> getAllDish(@RequestParam(required = false, defaultValue = "0") int page,
                                 @RequestParam(required = false, defaultValue = "5") int size
    ) {
        log.info("getAllDish : {}", page);
        return menuService.getAllDish(PageRequest.of(page, size));

    }

    private static Menu getMenu(DishRequest dishRequest) {
        String dish = dishRequest.getDish();
        String description = dishRequest.getDescription();
        double price = dishRequest.getPrice();
        Menu menu = new Menu(dish, description, price);
        return menu;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }
}
