package com.example.foodlist.wishlist.entity;

//데이터베이스에 저장할 것들 정의

import com.example.foodlist.db.MemoryDbEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.PrivateKey;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishListEntity extends MemoryDbEntity {
    private String title;//가게명
    private String category; //어디서 왔는지
    private String address;//주소
    private String reedAddress;//도로명주소
    private String homePageLink; // 홈페이지
    private String imageLink; //이미지
    private boolean isVisite; //방문여부
    private int visiteCount; //방문 숫자
    private LocalDateTime lastVisitDate; //마지막 방문일자

}
