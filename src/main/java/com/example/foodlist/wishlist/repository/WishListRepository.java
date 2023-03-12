package com.example.foodlist.wishlist.repository;

import com.example.foodlist.db.MemoryDbRepositoryAbstract;
import com.example.foodlist.wishlist.entity.WishListEntity;
import org.springframework.stereotype.Repository;


@Repository //데이터베이스를 저장하는 곳
public class WishListRepository extends MemoryDbRepositoryAbstract<WishListEntity> {

}
