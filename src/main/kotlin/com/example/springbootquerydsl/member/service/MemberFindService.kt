package com.example.springbootquerydsl.member.service

import com.example.springbootquerydsl.member.dto.MemberDto
import com.example.springbootquerydsl.member.factory.MemberDtoFactory
import com.example.springbootquerydsl.member.repository.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberFindService(
    private val memberRepository: MemberRepository,
) {

    fun findById(memberId: Long): MemberDto {
        val member = memberRepository.findByIdOrNull(memberId) ?: throw IllegalArgumentException("회원을 찾을 수 없습니다.")

        return MemberDtoFactory.of(member)
    }

}
