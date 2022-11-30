package linksys.core.model.error

enum class ErrorCodes {
  RESOURCE_NOT_FOUND
}

data class ErrorModel(
  val code: ErrorCodes,
  val message: String? = null
)
