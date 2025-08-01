package org.aicodinglab.kotlinspring.common.search

data class SearchCriteria(
    val key:String,
    val operation:String,
    val value: Object
) {
    fun isJoin(): Boolean {
        return key.contains(".")
    }

    fun getFieldAndAttribute(): Array<String> {
        return key.split("\\.").toTypedArray()
    }
}