package org.aicodinglab.kotlinspring.common.search

import jakarta.persistence.criteria.*
import org.springframework.data.jpa.domain.Specification
import java.math.BigInteger
import java.util.function.Function

class BeSpecification<T>(val criteria: SearchCriteria) : Specification<T> {

    override fun toPredicate(
        root: Root<T>,
        query: CriteriaQuery<*>?,
        builder: CriteriaBuilder
    ): Predicate? {
        val path = root.get<T>(criteria.key) as Expression<out Comparable<*>>
        query!!.distinct(true)
        if (criteria.operation.equals(":", true)) {
            val value: Any? = convertValidValue(path.getJavaType().getSimpleName(), criteria.value as String?)
            return if (path.getJavaType() === String::class.java) {
                builder.like(path as Expression<String?>, "%" + value + "%")
            } else {
                builder.equal(path, value)
            }
        }
        return null
    }

    private fun convertValidValue(typeName: String, value: String?): Any? {
        value ?: return null
        return when (typeName) {
            "int" -> value.toInt()
            "Integer" -> value.checkNull { it!!.toInt() }
            "BigInteger" -> value.checkNull { BigInteger(it!!) }

            "long" -> value.toLong()
            "Long" -> value.checkNull { it!!.toLong() }
            "short" -> value.toShort()
            "Short" -> value.checkNull { it!!.toShort() }
            "float" -> value.toFloat()
            "Float" -> value.checkNull { it!!.toFloat() }
            "double" -> value.toDouble()
            "Double" -> value.checkNull { it!!.toDouble() }
            "boolean", "Boolean" -> "true".equals(criteria.value)
            "String" -> value
            else -> throw RuntimeException("Unknown Typename: " + typeName)
        }
    }
}

private fun String.checkNull(parser: Function<String?, Any?>): Any? {
    if (this.isEmpty() || this.equals("null", ignoreCase = true)) {
        return null
    }
    return parser.apply(this)
}