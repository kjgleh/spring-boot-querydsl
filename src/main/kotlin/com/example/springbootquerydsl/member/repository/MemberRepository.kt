package com.example.springbootquerydsl.member.repository

import com.example.springbootquerydsl.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<Member, Long> {
}