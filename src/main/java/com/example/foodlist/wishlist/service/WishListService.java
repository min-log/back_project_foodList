package com.example.foodlist.wishlist.service;

import com.example.foodlist.naver.NaverClient;
import com.example.foodlist.naver.dto.SearchImageReq;
import com.example.foodlist.naver.dto.SearchLocalReq;
import com.example.foodlist.wishlist.dto.WishListDto;
import com.example.foodlist.wishlist.entity.WishListEntity;
import com.example.foodlist.wishlist.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishListService {

    //
    private final NaverClient naverClient;

    //데이터 베이스
    private final WishListRepository wishListRepository;


    // 검색
    public WishListDto search(String query){
        // 지역검색 >> 이미지 검색 >> 결과

        // 지역검색
        var searchLocalReq = new SearchLocalReq();
        searchLocalReq.setQuery(query);

        var searchLocalRes = naverClient.searchLocal(searchLocalReq);
        if (searchLocalRes.getTotal() > 0){
            //갯수가 있을때
            var localItem = searchLocalRes.getItems().stream().findFirst().get(); //첫번째 아이탬

            //이미지 쿼리
            var imageQuery = localItem.getTitle().replace("<[^>]*>",""); // 문자열 괄호 없애기
            var searchImageReq = new SearchImageReq();
            searchImageReq.setQuery(imageQuery);

            // 이미지 검색
            var searchImageRes = naverClient.searchImage(searchImageReq);

            if (searchLocalRes.getTotal()>0){
                var imageItem = searchImageRes.getItems().stream().findFirst().get();

                // 결과 리턴
                var result = new WishListDto();
                result.setTitle(localItem.getTitle());
                result.setCategory(localItem.getCategory());
                result.setAddress(localItem.getAddress());
                result.setRoadAddress(localItem.getRoadAddress());
                result.setHomePageLink(localItem.getLink());

                result.setImageLink(imageItem.getLink());


                return result;

            }

        }

        return new WishListDto(); //없을때 빈 값
    }

    //데이터 베이스 저장
    public WishListDto add(WishListDto wishListDto) {
        //
        var entity = dtoToEntity(wishListDto);
        var saveEntity = wishListRepository.save(entity);

        return entityToDto(saveEntity);
    }


    //변환 메서드 : Dto > entity
    private WishListEntity dtoToEntity(WishListDto wishListDto){
        var entity = new WishListEntity();
        entity.setIndex(wishListDto.getIndex());
        entity.setTitle(wishListDto.getTitle());
        entity.setCategory(wishListDto.getCategory());
        entity.setAddress(wishListDto.getAddress());
        entity.setRoadAddress(wishListDto.getRoadAddress());
        entity.setHomePageLink(wishListDto.getHomePageLink());
        entity.setImageLink(wishListDto.getImageLink());
        entity.setVisite(wishListDto.isVisite());
        entity.setVisiteCount(wishListDto.getVisiteCount());
        entity.setLastVisitDate(wishListDto.getLastVisitDate());

        return entity;
    }
    //변환 메서드 : entity > Dto
    private WishListDto entityToDto(WishListEntity wishListEntity){
        var dto = new WishListDto();
        dto.setIndex(wishListEntity.getIndex());
        dto.setTitle(wishListEntity.getTitle());
        dto.setCategory(wishListEntity.getCategory());
        dto.setAddress(wishListEntity.getAddress());
        dto.setRoadAddress(wishListEntity.getRoadAddress());
        dto.setHomePageLink(wishListEntity.getHomePageLink());
        dto.setImageLink(wishListEntity.getImageLink());
        dto.setVisite(wishListEntity.isVisite());
        dto.setVisiteCount(wishListEntity.getVisiteCount());
        dto.setLastVisitDate(wishListEntity.getLastVisitDate());

        return dto;
    }

    public List<WishListDto> findAll() {

        return wishListRepository.fintAll()
                .stream()
                .map(it -> entityToDto(it))
                .collect(Collectors.toList());
    }

    //db 삭제
    public void delete(int index) {
        wishListRepository.deleteById(index);
    }

    public void addVisit(int index){
        var wishItem = wishListRepository.findById(index);
        if(wishItem.isPresent()){
            //값이 있으면 업데이트
            var item = wishItem.get();
            item.setVisite(true);
            item.setVisiteCount(item.getVisiteCount()+1);
        }

    }
}
