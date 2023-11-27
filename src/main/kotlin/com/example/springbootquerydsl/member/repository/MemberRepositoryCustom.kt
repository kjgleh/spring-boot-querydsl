package com.example.springbootquerydsl.member.repository

import com.example.springbootquerydsl.member.dto.SearchFilter
import com.example.springbootquerydsl.member.entity.Member
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface MemberRepositoryCustom {
    fun findList(filter: SearchFilter): List<Member>
    fun findPage(filter: SearchFilter, pageable: Pageable): Page<Member>
}