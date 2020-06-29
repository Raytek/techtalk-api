package co.excentri.techtalk.api.exception

import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.server.ResponseStatusException

class EntityNotFoundException(reason: String) : ResponseStatusException(NOT_FOUND, reason)