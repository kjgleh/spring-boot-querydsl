package com.example.springbootquerydsl.member.service

import com.example.springbootquerydsl.member.dto.MemberDto
import com.example.springbootquerydsl.member.dto.SearchFilter
import com.example.springbootquerydsl.member.factory.MemberDtoFactory
import com.example.springbootquerydsl.member.repository.MemberRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberListupService(
    private val memberRepository: MemberRepository,
) {

    fun findList(filter: SearchFilter): List<MemberDto> {
        val members = memberRepository.findList(filter)

        return members.map { MemberDtoFactory.of(it) }
    }

    fun findPage(filter: SearchFilter, pageable: Pageable): Page<MemberDto> {
        val memberPage = memberRepository.findPage(filter, pageable)

        return PageImpl(
            memberPage.content.map { MemberDtoFactory.of(it) }
        )
    }
}