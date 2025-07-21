package org.aicodinglab.backendhanbi.controller

import org.aicodinglab.backendhanbi.entity.User
import org.aicodinglab.backendhanbi.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(val userService: UserService) {

    @GetMapping("/all")
    fun getAllUser():  List<User> {
        return userService.findAll()
    }

    @GetMapping("/email/{email}")
    fun getAllUser(@PathVariable email: String):  User? {
        return userService.findByEmail(email)
    }

    @PostMapping("/save")
    fun getAllUser(@RequestBody user: User):  User? {
        return userService.save(user)
    }
}