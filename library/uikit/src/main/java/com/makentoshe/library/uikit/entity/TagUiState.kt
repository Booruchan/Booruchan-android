package com.makentoshe.library.uikit.entity

data class TagUiState(val tag: String, val type: TagTypeUiState) {

    val string: String // prettified tag string for displaying
        get() = tag.replace("_", " ")

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TagUiState

        if (tag != other.tag) return false

        return true
    }

    override fun hashCode(): Int {
        return tag.hashCode()
    }

}