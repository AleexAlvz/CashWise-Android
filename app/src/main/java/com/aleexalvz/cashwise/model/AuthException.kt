package com.aleexalvz.cashwise.model

class UserNotFoundException(override val message: String) : Exception(message)
class SignUpInvalidException(override val message: String) : Exception(message)
