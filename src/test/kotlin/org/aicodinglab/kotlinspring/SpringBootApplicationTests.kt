package org.aicodinglab.kotlinspring

import jakarta.transaction.Transactional
import org.aicodinglab.kotlinspring.entity.User
import org.aicodinglab.kotlinspring.service.UserService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@Transactional
class SpringBootApplicationTests(
) {

    @Autowired
    lateinit var userService: UserService

    @Test
    fun DBTest() {
        assert(userService.findAll().isNotEmpty())
        userService.save(User("6@6.com", "John", "12345"))
    }

    @Test
    fun CRUDTest() {
        assert(userService.findAll().isEmpty())
        userService.save(User("1@1.com", "John", "12345"))
        assert(userService.findAll().size == 1)
        var user = userService.findByEmail("1@1.com")
        assert(user != null)
        user?.let {
            assert(it.userName == "John")
            assert(it.password == "12345")
            it.userName = "Jack"
            it.password = "22222"
            userService.update(it)
        }

        user = userService.findByEmail("1@1.com")
        assert(user != null)
        user?.let {
            assert(it.userName == "Jack")
            assert(it.password == "22222")
            userService.deleteByUserId(it.userId)
        }
        assert(userService.findAll().isEmpty())
    }
}
