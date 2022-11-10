package com.boot.member.domain;

import javax.persistence.*;

//JPA는 인터페이스이고 많은 구현체가 있지만 hibernate을 사용해보자.
//애노테이션으로 매핑을 진행

@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)//DB가 알아서 자동으로 설정
    private Long id;
    @Column(name="name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
