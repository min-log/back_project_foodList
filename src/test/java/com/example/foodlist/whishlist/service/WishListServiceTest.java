package com.example.foodlist.whishlist.service;

import com.example.foodlist.wishlist.service.WishListService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WishListServiceTest {
    @Autowired
    private WishListService wishListService;


    //리스트 검색
    @Test
    public void searchTest(){
        var result = wishListService.search("갈비집");

        System.out.println(result);
        Assertions.assertNotNull(result);
    }


}
