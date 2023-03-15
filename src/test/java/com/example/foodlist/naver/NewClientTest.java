package com.example.foodlist.naver;

import com.example.foodlist.naver.dto.SearchImageReq;
import com.example.foodlist.naver.dto.SearchImageRes;
import com.example.foodlist.naver.dto.SearchLocalReq;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NewClientTest {
    @Autowired
    private NaverClient naverClient;

    // 네이버 Api 지역검색 사용 확인
    @Test
    public void searchLocalTest(){
        var search = new SearchLocalReq();
        search.setQuery("갈비집");

        var result = naverClient.searchLocal(search);
        System.out.println(result);
    }

    // 네이버 Api 이미지 검색 사용 확인
    @Test
    public void searchImageTest(){
        var search = new SearchImageReq();
        search.setQuery("갈비집");
        var result = naverClient.searchImage(search);
        System.out.println(result);
    }


}
