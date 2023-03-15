package com.example.foodlist.wishlist.dto;

//데이터베이스에 저장할 것들 정의

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishListDto {

    //데이터 배이스의 Entity를 사용하면 변수 명이 변할 시 프론트까지 변수명을 변환해야할 수 있다. 그런부분을 없애기 위해 따로 프론트 앤드에서 사용할 dto 생성

    private Integer index;
    private String title;//가게명
    private String category; //어디서 왔는지
    private String address;//주소
    private String roadAddress;//도로명주소
    private String homePageLink; // 홈페이지
    private String imageLink; //이미지
    private boolean isVisite; //방문여부
    private int visiteCount; //방문 숫자
    private LocalDateTime lastVisitDate; //마지막 방문일자

}
