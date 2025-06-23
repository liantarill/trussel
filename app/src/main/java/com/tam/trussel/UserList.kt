package com.tam.trussel

object UserList {
    val users = mutableListOf(
        User("lian@gmail.com", "lian123"),
        User("alka@gmail.com", "alka123")
    )

    fun addUser(user: User) {
        users.add(user)
    }

    fun findUser(email: String, password: String): User? {
        return users.find { it.email == email && it.password == password }
    }


}