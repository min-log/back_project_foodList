package com.example.foodlist.controller;

import com.example.foodlist.wishlist.dto.WishListDto;
import com.example.foodlist.wishlist.service.WishListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
public class ApiController {

    private final WishListService wishListService;

    //검색
    @GetMapping("/search")
    public WishListDto search(@RequestParam String query){
        return wishListService.search(query);
    }

    @PostMapping("")
    public WishListDto add(@RequestBody WishListDto wishListDto){
        log.info("{}",wishListDto);
        return wishListService.add(wishListDto);
    }

    //전체 리스트
    @GetMapping("/all")
    public List<WishListDto> findAll(){
        return wishListService.findAll();
    }

    //삭제
    @DeleteMapping("/{index}")
    public void delete(@PathVariable int index){
        wishListService.delete(index);

    }

    @PostMapping("/{index}")
    public void addVisit(@PathVariable int index){
        wishListService.addVisit(index);

    }

}
