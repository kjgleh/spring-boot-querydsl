package com.example.springbootquerydsl.member.factory

import com.example.springbootquerydsl.member.dto.MemberDto
import com.example.springbootquerydsl.member.entity.Member

object MemberDtoFactory {

    fun of(member: Member): MemberDto {
        return MemberDto(
            id = member.id,
            name = member.name,
        )
    }
}