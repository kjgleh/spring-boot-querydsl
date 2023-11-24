package com.example.springbootquerydsl.member.service

import com.example.springbootquerydsl.member.dto.MemberDto
import com.example.springbootquerydsl.member.factory.MemberDtoFactory
import com.example.springbootquerydsl.member.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberListupService(
    private val memberRepository: MemberRepository,
) {

    fun listup(): List<MemberDto> {
        val members = memberRepository.findAll()

        return members.map { MemberDtoFactory.of(it) }
    }
}