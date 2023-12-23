package com.example.springbootquerydsl.locker.entity

import org.springframework.data.jpa.repository.JpaRepository

interface LockerRepository: JpaRepository<Locker, Long>