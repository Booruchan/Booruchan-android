package com.makentoshe.booruchan.danbooru.tag

import com.makentoshe.booruchan.core.Time
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import com.makentoshe.booruchan.core.tag.Tag
import com.makentoshe.booruchan.core.tag.Type
import com.makentoshe.booruchan.core.time

interface DanbooruTag : Tag {
    val creationTime: Time
    val updationTime: Time?
    val isLocked: Boolean
    val count: Int
}

@JacksonXmlRootElement(localName = "tags")
data class XmlDanbooruTag(
    @JsonProperty("id", required = true)
    @JacksonXmlProperty(localName = "id")
    override val tagId: Int,
    @JacksonXmlProperty(localName = "name")
    override val text: String,
    @JacksonXmlProperty(localName = "type")
    val rawType: Int,
    @JacksonXmlProperty(localName = "created-at")
    val rawCreationTime: String,
    @JacksonXmlProperty(localName = "updated-at")
    val rawUpdationTime: String?,
    @JacksonXmlProperty(localName = "is-locked")
    override val isLocked: Boolean,
    @JacksonXmlProperty(localName = "post-count")
    override val count: Int
) : DanbooruTag {
    override val creationTime = rawCreationTime.let(::time)
    override val updationTime = rawUpdationTime?.let(::time)

    @JsonIgnore
    override val type = when (rawType) {
        else -> Type.UNDEFINED
    }
}

data class JsonDanbooruTag(
    @JsonProperty("id", required = true)
    override val tagId: Int,
    @JsonProperty("name")
    override val text: String,
    @JsonProperty("category")
    val rawType: Int,
    @JsonProperty("created_at")
    val rawCreationTime: String,
    @JsonProperty("updated_at")
    val rawUpdationTime: String?,
    @JsonProperty("is_locked")
    override val isLocked: Boolean,
    @JsonProperty("post_count")
    override val count: Int
) : DanbooruTag {
    override val creationTime = rawCreationTime.let(::time)
    override val updationTime = rawUpdationTime?.let(::time)

    @JsonIgnore
    override val type: Type = when (rawType) {
        else -> Type.UNDEFINED
    }
}