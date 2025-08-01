package org.aicodinglab.kotlinspring.common.search

import org.springframework.context.ApplicationContext
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Component
import java.util.function.Function

@Component
class SearchService(val context: ApplicationContext) {

    fun <E, T, R : JpaSpecificationExecutor<E>> getSearch(
        search: String?,
        pageable: Pageable,
        rClass: Class<R>,
        tClass: Class<T>
    ): Page<T> {
        val spBuilder =
            SpecificationsBuilder<BeSpecification<E>, E>(Function { criteria: SearchCriteria? ->
                BeSpecification(
                    criteria!!
                )
            })
        val specification: Specification<E>? = spBuilder.parseSearch(search)
        if(specification == null) {
            return Page.empty<T>()
        }
        val repository: R = context.getBean(rClass)
        return repository.findAll(specification, pageable).map {
            return@map tClass.getConstructor(it!!.javaClass).newInstance(it)
        }
    }
}