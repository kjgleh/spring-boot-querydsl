package com.example.springbootquerydsl.member.factory

import com.example.springbootquerydsl.member.dto.MemberDto
import com.example.springbootquerydsl.member.dto.MemberListResponse
import com.example.springbootquerydsl.member.entity.Member

object MemberListResponseFactory {

    fun of(memberDto: MemberDto): MemberListResponse {
        return MemberListResponse(
            id = memberDto.id,
            name = memberDto.name,
        )
    }
}