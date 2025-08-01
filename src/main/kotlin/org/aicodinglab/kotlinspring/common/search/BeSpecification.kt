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
    ) : Predicate? {
        val path = makePath(root, criteria) as Expression<out Comparable<*>>
        query!!.distinct(true)
        if (criteria.operation.equals(":", true)) {
            val value: Any? = convertValidValue(path.getJavaType().getSimpleName(), criteria.value)
            return if(path.getJavaType() === String::class.java) {
                builder.like(path as Expression<String?>, "%" + value + "%")
            } else {
                builder.equal(path, value)
            }
        }
        return null
    }

    private fun makePath(root: Root<T>, criteria: SearchCriteria): Path<T> {
//        if (criteria.isJoin()) {
//            val fields: Array<Any?> = criteria.getFieldAndAttribute() as Array<Any?>
//            val join: Join<T?, Any?> = root.join(fields[0], JoinType.INNER)
//            return join.get(fields[1])
//        }
        return root.get(criteria.key)
    }

    private fun convertValidValue(typeName: String, value: Any?): Any? {
        var value = value
        if (value == null) {
            return null
        } else if (value is String) {
            val stringValue = value

            when (typeName) {
                "int" -> value = stringValue.toInt()
                "Integer" -> value = this.parseNullableNumber(stringValue, Function { s: String? -> s!!.toInt() })
                "BigInteger" -> value =
                    this.parseNullableNumber(stringValue, Function { `val`: String? -> BigInteger(`val`) })

                "long" -> value = stringValue.toLong()
                "Long" -> value = this.parseNullableNumber(stringValue, Function { s: String? -> s!!.toLong() })
                "short" -> value = stringValue.toShort()
                "Short" -> value = this.parseNullableNumber(stringValue, Function { s: String? -> s!!.toShort() })
                "float" -> value = stringValue.toFloat()
                "Float" -> value = this.parseNullableNumber(stringValue, Function { s: String? -> s!!.toFloat() })
                "double" -> value = stringValue.toDouble()
                "Double" -> value = this.parseNullableNumber(stringValue, Function { s: String? -> s!!.toDouble() })
                "boolean", "Boolean" -> value = "true".equals(criteria.value)
                "String" -> {}
                else -> throw RuntimeException("Unknown Typename: " + typeName)
            }
        } else {
            value = value.toString()
        }
        return value
    }

    private fun parseNullableNumber(value: String, parser: Function<String?, Any?>): Any? {
        if (value.isEmpty() || value.equals("null", ignoreCase = true)) {
            return null
        }
        return parser.apply(value)
    }
}