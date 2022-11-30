package com.linksys.demo.reactivedemo.model

import java.util.*
import javax.validation.constraints.NotNull

data class IdentityModel(
  @NotNull
  val id: UUID
)
