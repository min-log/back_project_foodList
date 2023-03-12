package com.example.foodlist.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

abstract public class MemoryDbRepositoryAbstract<T extends MemoryDbEntity> implements MemoryDbRepositoryIfs<T>{
    private final List<T> db = new ArrayList<>(); // 디비
    private int index = 0; // primary key

    @Override
    public Optional<T> findById(int index) {
        return db.stream().filter(it-> it.getIndex() == index).findFirst();
    }

    @Override
    public T save(T entity) {
        // 받아온 entity 값과 db entity의 index가 일치하면 처음 값을 가져온다.
        var optionalEntity = db.stream().filter(it->it.getIndex() == entity.getIndex()).findFirst();

        if(optionalEntity.isEmpty()){
            // 디비에 데이터가 없는 경우
            index++; //
            entity.setIndex(index);
            db.add(entity); //디비에 값을 저장

            return entity;

        }else {
            // 디비에 이미 데이터가 있는경우
            // 데이터 없데이트
            var preIndex = optionalEntity.get().getIndex(); // 기존에 있던 db index 값
            entity.setIndex(preIndex); // 받아온 데이터 entity에 기존 index 값 초기화

            deleteById(preIndex); //기존 파일 제거
            db.add(entity); //새로운 정보 저장
            return entity;

        }

    }

    @Override
    public void deleteById(int index) {
        var optionalEntity = db.stream().filter(it->it.getIndex()== index).findFirst() ; //findFirst -> stream 에서 가장 앞에 있는 값을 리턴 index 번호

        if(optionalEntity.isPresent()){ //Optional 객체가 값을 가지고 있다면 true, 값이 없다면 false 리
            //데이터가 있는경우
            db.remove(optionalEntity.get());
        }

    }

    @Override
    public List<T> listAll() {
        return db;
    }
}
