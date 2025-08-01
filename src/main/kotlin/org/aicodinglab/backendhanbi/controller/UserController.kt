package org.aicodinglab.backendhanbi.controller

import org.aicodinglab.backendhanbi.dto.UserDto
import org.aicodinglab.backendhanbi.entity.User
import org.aicodinglab.backendhanbi.repository.UserPageRepository
import org.aicodinglab.backendhanbi.repository.UserRepository
import org.aicodinglab.backendhanbi.service.SearchService
import org.aicodinglab.backendhanbi.service.UserService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.data.web.SortDefault
import org.springframework.data.web.SortDefault.SortDefaults
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
    val userService: UserService,
    val searchService: SearchService
) {

    @GetMapping("/select")

    fun searchPage(
        @PageableDefault(page = 0, size = 10) @SortDefaults(
        SortDefault(
            sort = ["createTime"],
            direction = Sort.Direction.DESC
        )
    ) pageable: Pageable,
        @RequestParam(value = "search", defaultValue = "") search: String
    ) : Page<UserDto> {
        return searchService.getSearch(search, pageable, UserPageRepository::class.java, UserDto::class.java)
    }

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
