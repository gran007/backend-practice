package org.aicodinglab.kotlinspring.common.search

import org.springframework.data.jpa.domain.Specification
import java.util.function.Function
import java.util.regex.Pattern

class SpecificationBuilder<T : Specification<E>?, E>(private val func: Function<SearchCriteria?, T?>) {

    private val specs: MutableList<Specification<E>?> = ArrayList()
    private val pattern: Pattern = Pattern.compile("([\\w.]+?)(:|<|>)([가-힣a-zA-Z@.\\w]+?),")

    fun parseSearch(search: String?): Specification<E>? {
        val matcher = pattern.matcher("$search,")

        while (matcher.find()) {
            specs.add(
                func.apply(
                    SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3) as Object)
                )
            )
        }

        if (specs.isEmpty()) {
            return null
        }

        var result = specs[0]

        for (i in 1..<specs.size) {
            specs[0]!!.and(specs[i])
            result = result?.and(specs[i])
        }
        return result
    }
}
