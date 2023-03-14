package com.example.foodlist.whishlist.repository;


import com.example.foodlist.wishlist.entity.WishListEntity;
import com.example.foodlist.wishlist.repository.WishListRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WhisListRepositoryTest {
    @Autowired
    private WishListRepository wishListRepository;

    private WishListEntity create(){
        var wishList = new WishListEntity();
        wishList.setTitle("title");
        wishList.setCategory("category");
        wishList.setAddress("Address");
        wishList.setReedAddress("ReadAddress");
        wishList.setHomePageLink("");
        wishList.setImageLink("");
        wishList.setVisite(false);
        wishList.setVisiteCount(0);
        wishList.setLastVisitDate(null);
        return wishList;
    }

    @Test
    public void saveTest(){

        var wishListEntity = create();
        var expected = wishListRepository.save(wishListEntity);

        Assertions.assertNotNull(expected); //데이터가 없으면 안됨
        Assertions.assertEquals(1,expected.getIndex());// 첫번째 인덱스가 있으면 정상 저장
        
    }

    @Test
    public void UpdateTest(){

        var wishListEntity = create();
        var expected = wishListRepository.save(wishListEntity);

        expected.setTitle("Update test");
        var saveEntity = wishListRepository.save(expected);
        Assertions.assertEquals("Update test",saveEntity.getTitle());
        Assertions.assertEquals(1,wishListRepository.listAll().size());
    }

    @Test
    public void findByIdTest(){
        var wishListEntity = create();
        wishListRepository.save(wishListEntity);

        var expected = wishListRepository.findById(1);
        Assertions.assertEquals(true,expected.isPresent());
        Assertions.assertEquals(1,expected.get().getIndex());


    }
    @Test
    public void deleteTest(){
        var wishListEntity = create();
        wishListRepository.save(wishListEntity);
        wishListRepository.deleteById(1);
        int count = wishListRepository.listAll().size();
        Assertions.assertEquals(0,count);
    }
    @Test
    public void listAllTest(){
        var wishListEntity1 = create();
        wishListRepository.save(wishListEntity1);
        var wishListEntity2 = create();
        wishListRepository.save(wishListEntity2);
        int count = wishListRepository.listAll().size();
        Assertions.assertEquals(2,count);
    }
}
