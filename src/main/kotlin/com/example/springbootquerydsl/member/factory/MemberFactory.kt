package com.example.springbootquerydsl.member.factory

import com.example.springbootquerydsl.member.dto.MemberCreateRequest
import com.example.springbootquerydsl.member.entity.Member

object MemberFactory {

    fun of(request: MemberCreateRequest): Member {
        return Member(
            name = request.name,
            age = request.age,
        )
    }
}