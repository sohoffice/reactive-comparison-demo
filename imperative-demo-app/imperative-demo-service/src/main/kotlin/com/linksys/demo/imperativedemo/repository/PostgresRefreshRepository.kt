package com.linksys.demo.imperativedemo.repository

import com.linksys.demo.imperativedemo.entity.PostgresRefreshEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PostgresRefreshRepository : JpaRepository<PostgresRefreshEntity, Long> {
}
