package com.makentoshe.booruchan.screen.source.entity


enum class TagType {
    General, Artist, Character, Copyright, Metadata
}

data class TagUiState(val string: String, val type: TagType) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TagUiState

        if (string != other.string) return false

        return true
    }

    override fun hashCode(): Int {
        return string.hashCode()
    }

}