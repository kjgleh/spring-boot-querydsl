package com.example.springbootquerydsl.member.factory

import com.example.springbootquerydsl.member.dto.MemberDto
import com.example.springbootquerydsl.member.dto.MemberListResponse
import com.example.springbootquerydsl.member.dto.MemberPageResponse
import com.example.springbootquerydsl.member.entity.Member

object MemberPageResponseFactory {

    fun of(memberDto: MemberDto): MemberPageResponse {
        return MemberPageResponse(
            id = memberDto.id,
            name = memberDto.name,
            age = memberDto.age,
        )
    }
}