package com.example.springbootquerydsl.member.factory

import com.example.springbootquerydsl.member.dto.MemberDto
import com.example.springbootquerydsl.member.dto.MemberListResponse
import com.example.springbootquerydsl.member.dto.MemberResponse
import com.example.springbootquerydsl.member.entity.Member

object MemberResponseFactory {

    fun of(memberDto: MemberDto): MemberResponse {
        return MemberResponse(
            id = memberDto.id,
            name = memberDto.name,
            age = memberDto.age,
        )
    }
}