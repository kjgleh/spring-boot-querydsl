package com.example.springbootquerydsl.member.repository

import com.example.springbootquerydsl.member.dto.SearchFilter
import com.example.springbootquerydsl.member.entity.Member
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface MemberRepositoryCustom {
    fun findList(filter: SearchFilter): List<Member>
    fun findPage(filter: SearchFilter, pageable: Pageable): Page<Member>
    fun existByAge(age: Int): Boolean

    // Locker 가 존재하는 회원 조회
    fun findByLockerIsNotNull(): List<Member>
}