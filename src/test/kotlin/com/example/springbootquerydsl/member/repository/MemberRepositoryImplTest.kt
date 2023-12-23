package com.example.springbootquerydsl.member.repository

import com.example.springbootquerydsl.locker.entity.Locker
import com.example.springbootquerydsl.locker.entity.LockerRepository
import com.example.springbootquerydsl.member.entity.Member
import com.example.springbootquerydsl.testsupport.Support.fixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@ActiveProfiles("test")
class MemberRepositoryImplTest @Autowired constructor(
    val sut: MemberRepository,
    val lockerRepository: LockerRepository,
) {

    @Test
    @DisplayName("이름으로 회원의 존재 여부를 확인한다.")
    fun `sut should check existence of member when a name is given`() {
        // Arrange
        val member = fixture<Member>()
        sut.save(member)

        // Act
        val actual = sut.existsByName(member.name)

        // Assert
        assertThat(actual).isTrue()
    }

    @Test
    @DisplayName("나이가 주어지면 회원의 존재 여부를 확인한다.")
    fun `sut should check existence of member when age is given`() {
        // Arrange
        sut.save(fixture { property(Member::age) { 30 } })

        // Act
        val actual = sut.existByAge(30)

        // Assert
        assertThat(actual).isTrue()
    }

    @Test
    @DisplayName("Locker 가 존재하는 회원을 조회한다.")
    fun `sut should find members who have a locker`() {
        // Arrange
        val memberWithLocker = fixture<Member>()
        val memberWithoutLocker = fixture<Member>()
        sut.saveAll(
            listOf(memberWithLocker, memberWithoutLocker)
        )
        lockerRepository.saveAll(
            listOf(
                fixture { property(Locker::memberId) { memberWithLocker.id } },
            )
        )

        // Act
        val actual = sut.findByLockerIsNotNull()

        // Assert
        assertThat(actual).hasSize(1)
        assertThat(actual[0].id).isEqualTo(memberWithLocker.id)
    }
}