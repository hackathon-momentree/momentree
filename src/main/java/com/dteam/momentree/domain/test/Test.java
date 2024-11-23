package com.dteam.momentree.domain.test;

import com.dteam.momentree.domain.UserBaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Test extends UserBaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value;

    public static Test of(String value){
        Test test = new Test();
        test.value = value;
        return test;
    }
}
