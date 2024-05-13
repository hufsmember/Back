package com.example.hufs.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class SoftDeleteBaseTimeEntity {

    @Column(insertable = false)
    protected LocalDateTime deletedAt;

    //삭제 요청이 들어오면
    protected void delete(LocalDateTime currentTime){
        if (deletedAt==null){
            deletedAt = currentTime;
        }
    }

    public boolean isDeleted(){return deletedAt!=null;}

    protected void restore() {
        deletedAt = null;
    }
}
