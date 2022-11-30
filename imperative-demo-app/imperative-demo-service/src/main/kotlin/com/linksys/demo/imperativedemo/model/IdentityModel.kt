package com.linksys.demo.imperativedemo.model

import java.util.*
import javax.validation.constraints.NotNull

data class IdentityModel(
  @NotNull
  val id: UUID
)
