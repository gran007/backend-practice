package org.aicodinglab.backendhanbi.service

import org.aicodinglab.backendhanbi.common.search.BeSpecification
import org.aicodinglab.backendhanbi.common.search.SearchCriteria
import org.aicodinglab.backendhanbi.common.search.SpecificationsBuilder
import org.springframework.context.ApplicationContext
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
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
            SpecificationsBuilder<BeSpecification<E>, E>(Function { criteria: SearchCriteria? -> BeSpecification(criteria!!) })
        val specification: Specification<E>? = spBuilder.parseSearch(search)
        val repository: R = context.getBean(rClass)
        return repository.findAll(specification, pageable).map {
            return@map tClass.getConstructor(it!!.javaClass).newInstance(it)
        }
    }
}