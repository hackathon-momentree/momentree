package com.dteam.momentree.domain;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

@Getter
@MappedSuperclass
public abstract class UserBaseEntity extends TimeBaseEntity {
    @CreatedBy
    private Long createUser;
    @LastModifiedBy
    private Long lastModifyingUser;

}
