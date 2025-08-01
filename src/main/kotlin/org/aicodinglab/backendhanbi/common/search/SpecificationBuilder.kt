package org.aicodinglab.backendhanbi.common.search

import org.springframework.data.jpa.domain.Specification
import java.util.function.Function
import java.util.regex.Pattern
import java.util.stream.Collectors

class SpecificationsBuilder<T : Specification<E>?, E>(private val func: Function<SearchCriteria?, T?>) {

    private val params: MutableList<SearchCriteria?> = ArrayList()
    private val pattern: Pattern = Pattern.compile("([\\w.]+?)(:|<|>)([가-힣a-zA-Z@.\\w]+?),")

    fun parseSearch(search: String?): Specification<E>? {
        // 정규식으로 search 필드 파싱한다.
        val matcher = pattern.matcher("$search,")

        while (matcher.find()) {
            params.add(SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3) as Object))
        }

        if (params.isEmpty()) {
            return null
        }

        val specs: MutableList<Specification<E>?> = params.stream()
            .map<T?> { f: SearchCriteria? -> func.apply(f) }
            .collect(Collectors.toList())

        var result = specs[0]

        for (i in 1..<params.size) {
            specs[0]!!.and(specs[i])
            result = result?.and(specs[i])
//                Specification.where<Any?>(result).and(specs[i])
        }
        return result
    }
}
