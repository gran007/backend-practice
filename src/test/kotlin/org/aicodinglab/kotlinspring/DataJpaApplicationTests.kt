package org.aicodinglab.kotlinspring

import org.aicodinglab.kotlinspring.entity.User
import org.aicodinglab.kotlinspring.repository.UserRepository
import org.aicodinglab.kotlinspring.service.UserService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource

//@SpringBootTest
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = ["classpath:application-test.yml"])

class DataJpaApplicationTests @Autowired constructor(
    private val userRepository: UserRepository
) {

    private val userService = UserService(userRepository)

    @Test
    fun CRUDTest2() {
        assert(userService.findAll().isEmpty())
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
