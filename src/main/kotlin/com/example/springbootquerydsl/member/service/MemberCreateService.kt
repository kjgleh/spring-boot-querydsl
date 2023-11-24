package com.example.springbootquerydsl.member.service

import com.example.springbootquerydsl.member.dto.MemberCreateRequest
import com.example.springbootquerydsl.member.factory.MemberFactory
import com.example.springbootquerydsl.member.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberCreateService(
    private val memberRepository: MemberRepository,
) {

    fun create(request: MemberCreateRequest) {
        memberRepository.save(MemberFactory.of(request))
    }
}
