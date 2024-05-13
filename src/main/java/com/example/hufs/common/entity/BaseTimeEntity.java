package com.example.hufs.common.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

//Auditing:  Spring Data JPA에서 시간에 대해서 자동으로 값을 넣어주는 기능
@Getter
@MappedSuperclass //자식 클래스에 부모클래스의 컬럼만 매핑
@EntityListeners(AuditingEntityListener.class)//해당 클래스에 Auditing 기능을 포함한다.
public abstract class BaseTimeEntity {

    @CreationTimestamp
    protected LocalDateTime createdAt;

    @UpdateTimestamp
    protected LocalDateTime updatedAt;

}
