package com.senok.user.adapter.out.persistence.entity;

import com.senok.type.RoleType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String memberKey;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String profile;

    @Builder
    public MemberEntity(String memberKey, String name, String email, String profile) {
        this.name = name;
        this.email = email;
        this.profile = profile;
    }
}
