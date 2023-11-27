package com.example.springbootquerydsl.member.controller

import com.example.springbootquerydsl.common.dto.PageResult
import com.example.springbootquerydsl.common.toPageResult
import com.example.springbootquerydsl.member.dto.MemberCreateRequest
import com.example.springbootquerydsl.member.dto.MemberListResponse
import com.example.springbootquerydsl.member.dto.MemberPageResponse
import com.example.springbootquerydsl.member.dto.MemberResponse
import com.example.springbootquerydsl.member.dto.SearchFilter
import com.example.springbootquerydsl.member.factory.MemberListResponseFactory
import com.example.springbootquerydsl.member.factory.MemberPageResponseFactory
import com.example.springbootquerydsl.member.factory.MemberResponseFactory
import com.example.springbootquerydsl.member.service.MemberCreateService
import com.example.springbootquerydsl.member.service.MemberFindService
import com.example.springbootquerydsl.member.service.MemberListupService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
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

    /**
     * 목록 조회
     *
     * @param filter
     * @return List 타입
     */
    @GetMapping("/members")
    fun findList(filter: SearchFilter): List<MemberListResponse> {
        return memberListupService.findList(filter).map {
            MemberListResponseFactory.of(it)
        }
    }

    /**
     * 페이지 조회
     *
     * @param filter
     * @param pageable
     * @return Page 타입
     */
    @GetMapping("/members/page")
    fun findPage(filter: SearchFilter, pageable: Pageable): Page<MemberPageResponse> {
        return memberListupService.findPage(filter, pageable).map {
            MemberPageResponseFactory.of(it)
        }
    }

    /**
     * 페이지 조회
     *
     * 페이징 결과에 필요한 값만 반환 PageResult
     *
     * @param filter
     * @param pageable
     * @return PageResult 타입
     */
    @GetMapping("/members/page-result")
    fun findPageResult(filter: SearchFilter, pageable: Pageable): PageResult<MemberPageResponse> {
        return memberListupService.findPage(filter, pageable).map {
            MemberPageResponseFactory.of(it)
        }.toPageResult()
    }

    @GetMapping("/members/{memberId}")
    fun find(@PathVariable memberId: Long): MemberResponse {
        return memberFindService.findById(memberId).let {
            MemberResponseFactory.of(it)
        }
    }

    @PostMapping("/members")
    fun create(request: MemberCreateRequest) {
        memberCreateService.create(request)
    }
}