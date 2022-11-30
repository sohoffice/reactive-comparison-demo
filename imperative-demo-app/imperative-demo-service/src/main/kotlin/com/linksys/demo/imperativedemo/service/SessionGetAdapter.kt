package com.linksys.demo.imperativedemo.service

import com.linksys.demo.imperativedemo.model.TokenModel

interface SessionGetAdapter {

  fun get(token: String): TokenModel?

}
