package com.example.springbootquerydsl.member.repository

import com.example.springbootquerydsl.member.dto.SearchFilter
import com.example.springbootquerydsl.member.entity.Member
import com.example.springbootquerydsl.member.entity.QMember.member
import com.example.springbootquerydsl.locker.entity.QLocker.locker
import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.PathBuilder
import com.querydsl.jpa.JPAExpressions
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport
import org.springframework.data.jpa.repository.support.Querydsl
import org.springframework.data.querydsl.SimpleEntityPathResolver
import org.springframework.data.support.PageableExecutionUtils

class MemberRepositoryImpl(em: EntityManager) : MemberRepositoryCustom {

    private val queryFactory = JPAQueryFactory(em)
    private val querydsl: Querydsl by lazy {
        val entityInfo = JpaEntityInformationSupport.getEntityInformation(Member::class.java, em)
        val entityPath = SimpleEntityPathResolver.INSTANCE.createPath(entityInfo.javaType)
        val pathBuilder = PathBuilder(entityPath.type, entityPath.metadata)
        Querydsl(em, pathBuilder)
    }

    override fun findList(filter: SearchFilter): List<Member> {
        return queryFactory
            .select(member)
            .from(member)
            .where(filter.toPredicate())
            .fetch()
    }

    override fun findPage(filter: SearchFilter, pageable: Pageable): Page<Member> {
        val booleanBuilder = filter.toPredicate()
        val contentQuery = queryFactory
            .select(member)
            .from(member)
            .where(booleanBuilder)

        val content = querydsl.applyPagination(pageable, contentQuery).fetch()

        return PageableExecutionUtils.getPage(content, pageable) { getTotalCount(booleanBuilder) }
    }

    override fun existByAge(age: Int): Boolean {
        return queryFactory
            .select(member)
            .from(member)
            .where(eqAge(age))
            .fetchFirst() != null
    }

    override fun findByLockerIsNotNull(): List<Member> {
        return queryFactory
            .select(member)
            .from(member)
            .where(
                JPAExpressions
                    .selectFrom(locker)
                    .where(locker.memberId.eq(member.id))
                    .exists()
            )
            .fetch()
    }

    private fun SearchFilter.toPredicate(): BooleanBuilder {
        val booleanBuilder = BooleanBuilder()

        booleanBuilder.and(eqName(this.name))
        booleanBuilder.and(eqAge(this.age))

        return booleanBuilder
    }

    private fun eqName(name: String?): BooleanExpression? {
        return name?.let { member.name.eq(name) }
    }

    private fun eqAge(age: Int?): BooleanExpression? {
        return age?.let { member.age.eq(age) }
    }

    private fun getTotalCount(booleanBuilder: BooleanBuilder): Long {
        return queryFactory
            .select(member.count())
            .from(member)
            .where(booleanBuilder)
            .fetchOne() ?: 0

    }
}