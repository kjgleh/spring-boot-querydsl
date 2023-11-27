package com.example.springbootquerydsl.init

import com.example.springbootquerydsl.member.entity.Member
import com.example.springbootquerydsl.member.repository.MemberRepository
import java.util.UUID
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
class DefaultDataInitializer(
    private val memberRepository: MemberRepository,
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        val members = List(100) {
            Member(
                name = UUID.randomUUID().toString(),
                age = Random.nextInt(1, 200)
            )
        }
        memberRepository.saveAll(members)
    }
}