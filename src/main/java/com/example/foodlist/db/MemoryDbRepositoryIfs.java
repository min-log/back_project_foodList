package com.example.foodlist.db;

import java.util.List;
import java.util.Optional;

public interface MemoryDbRepositoryIfs<T> {
    // 제네릭

    // 제네릭 타입 : 해당 타입에 대해 옵셔널하게 값을 리턴해준다. -> 데이터 타입 리턴
    Optional<T> findById(int index);

    // 저장
    T save(T entity);

    //삭제
    void deleteById(int index);

    //전체 리스트
    List<T> fintAll();


}
