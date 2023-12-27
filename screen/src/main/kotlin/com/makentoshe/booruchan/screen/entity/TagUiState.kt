package com.makentoshe.booruchan.screen.entity


enum class TagUiStateType {
    General, Artist, Character, Copyright, Metadata, Other
}

data class TagUiState(val tag: String, val type: TagUiStateType) {

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