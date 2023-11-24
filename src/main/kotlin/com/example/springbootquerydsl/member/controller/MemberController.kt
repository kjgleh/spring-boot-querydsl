package com.example.springbootquerydsl.member.controller

import com.example.springbootquerydsl.member.dto.MemberCreateRequest
import com.example.springbootquerydsl.member.dto.MemberDto
import com.example.springbootquerydsl.member.service.MemberCreateService
import com.example.springbootquerydsl.member.service.MemberFindService
import com.example.springbootquerydsl.member.service.MemberListupService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(
    private val memberListupService: MemberListupService,
    private val memberFindService: MemberFindService,
    private val memberCreateService: MemberCreateService,
) {

    @GetMapping("/members")
    fun listup(): List<MemberDto> {
        return memberListupService.listup()
    }

    @GetMapping("/members/{memberId}")
    fun find(
        @PathVariable memberId: Long
    ): MemberDto {
        return memberFindService.findById(memberId)
    }

    @PostMapping("/members")
    fun create(request: MemberCreateRequest) {
        memberCreateService.create(request)
    }
}