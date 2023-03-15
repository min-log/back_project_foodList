package com.example.foodlist.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchImageRes {

    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<SearchImageItem> items;


    //item 안에 데이터들이 들어있음 리스트 형태
    //내부 클래스로
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchImageItem{
        private String title;
        private String link;
        private String thumbnail;
        private String sizeheight;
        private String sizewidth;

    }





}
