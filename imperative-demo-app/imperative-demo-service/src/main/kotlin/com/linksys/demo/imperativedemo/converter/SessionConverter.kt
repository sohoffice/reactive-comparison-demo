package com.linksys.demo.imperativedemo.converter

import com.linksys.demo.imperativedemo.entity.PostgresRefreshEntity
import com.linksys.demo.imperativedemo.entity.PostgresSessionEntity
import com.linksys.demo.imperativedemo.model.IdentityModel
import com.linksys.demo.imperativedemo.model.Site
import com.linksys.demo.imperativedemo.model.TokenModel

fun PostgresSessionEntity.toModel(): TokenModel {
  return TokenModel(Site(this.siteId), this.token, IdentityModel(this.identityId), this.expiryTime)
}

fun PostgresRefreshEntity.toModel(): TokenModel {
  return TokenModel(Site(this.siteId), this.token, IdentityModel(this.identityId), this.expiryTime)
}
