package org.aicodinglab.backendhanbi.controller

import org.aicodinglab.backendhanbi.entity.User
import org.aicodinglab.backendhanbi.service.UserService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(val userService: UserService) {

    @GetMapping("/all")
    fun findAll():  List<User> {
        return userService.findAll()
    }

    @GetMapping("/email/{email}")
    fun findByEmail(@PathVariable email: String):  User? {
        return userService.findByEmail(email)
    }

    @PostMapping("/save")
    fun save(@RequestBody user: User):  User? {
        return userService.save(user)
    }

    @PutMapping("/update")
    fun update(@RequestBody user: User):  User? {
        return userService.update(user)
    }

    @DeleteMapping("/delete/{userId}")
    fun deleteByUserId(@PathVariable userId: Long) {
        userService.deleteByUserId(userId)
    }
}
