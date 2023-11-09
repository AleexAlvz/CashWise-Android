package com.aleexalvz.cashwise.data.model.auth

class UserNotFoundException(override val message: String) : Exception(message)
class InvalidUserPasswordException(override val message: String) : Exception(message)
class SignUpInvalidException(override val message: String) : Exception(message)
