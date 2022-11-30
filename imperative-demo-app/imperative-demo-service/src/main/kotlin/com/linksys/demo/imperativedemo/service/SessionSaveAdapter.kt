package com.linksys.demo.imperativedemo.service

import com.linksys.demo.imperativedemo.model.SessionCreatingModel
import com.linksys.demo.imperativedemo.model.SessionModel

interface SessionSaveAdapter {
  /**
   * Save the sessions
   */
  fun save(m: SessionCreatingModel): SessionModel
}
