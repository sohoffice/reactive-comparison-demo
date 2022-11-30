package com.linksys.demo.reactivedemo.converter

import com.linksys.demo.reactivedemo.model.Site
import com.linksys.demo.reactivedemo.model.TokenModel
import com.linksys.demo.reactivedemo.mongo.entity.MongoRefreshEntity
import com.linksys.demo.reactivedemo.mongo.entity.MongoSessionEntity

fun MongoSessionEntity.toModel(): TokenModel {
  return TokenModel(Site(this.siteId), this.token, this.identity, this.expiryTime)
}

fun MongoRefreshEntity.toModel(): TokenModel {
  return TokenModel(Site(this.siteId), this.token, this.identity, this.expiryTime)
}
