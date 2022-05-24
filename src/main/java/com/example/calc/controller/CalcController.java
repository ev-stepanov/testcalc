package com.example.calc.controller;

import com.example.calc.model.AddModel;
import com.example.calc.service.CalcService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CalcController {

    private final CalcService calcService;

    //get - получение, post - создание, put - обновление, delete - удаление
    @GetMapping("hello")
    public String hello() {

        return "hello";
    }

    public void calc() {

    }

    @GetMapping("add/{number1}/{number2}")
    public Integer add(@PathVariable Integer number1, @PathVariable Integer number2) {
        log.info("add method started with params number1 = {}, number2 = {}", number1, number2);

        return calcService.add(number1, number2);
    }

    @GetMapping("add2")
    public Integer add2(@RequestParam Integer number1, @RequestParam Integer number2) {
        log.info("add2");

        return number1 + number2;
    }

    @PostMapping("add3")
    public Integer add3(@RequestBody AddModel addModel) {

        return addModel.getNumber1() + addModel.getNumber2();
    }

    // 200 - OK, 201 - created, 5xx - ошибка на сервере
    // 3xx - редирект
    // 401 - не авторизован, 403 - нет разрешений, 400 неверный запрос, 404 страница не найдена, 405 - неправильный метод (вместо Get отправил Post)
    // 4xx - ошибки клиента

    //если передаешь строку вместо числа - 400, bad request

    @PostMapping("add4")
    public ResponseEntity<AddModel> add4(@RequestBody AddModel addModel) {

        addModel.setResult(addModel.getNumber1() + addModel.getNumber2());

        return ResponseEntity.ok(addModel);
    }

    @GetMapping("divide/{number1}/{number2}")
    public Integer divide(@PathVariable Integer number1, @PathVariable Integer number2) {

        return number1 / number2;
    }

    //warn - ошибка не критичная

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<String> message(ArithmeticException e) {
        log.error("error", e);

        return ResponseEntity.badRequest().body("На ноль делить нельзя");
    }
}
