package com.linksys.demo.asyncdemo.service

import com.linksys.demo.asyncdemo.Constants.TASK_EXECUTOR_SERVICE
import com.linksys.demo.asyncdemo.model.TokenModel
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.CompletableFuture

@Service
class SessionService(
  private val sessionGetAdapter: SessionGetAdapter
) {

  @Async(TASK_EXECUTOR_SERVICE)
  @Transactional
  fun getSession(token: String): CompletableFuture<TokenModel> {
    return sessionGetAdapter.get(token)
  }

}
