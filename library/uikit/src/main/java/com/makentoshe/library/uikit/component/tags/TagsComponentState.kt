package com.makentoshe.library.uikit.component.tags

data class TagsComponentState(
    /** State for all general tags */
    val generalTagsContentState: TagsContentState,
    /** State for all character tags */
    val characterTagsContentState: TagsContentState,
    /** State for all artist tags */
    val artistTagsContentState: TagsContentState,
    /** State for all copyright tags */
    val copyrightTagsContentState: TagsContentState,
    /** State for all metadata tags */
    val metadataTagsContentState: TagsContentState,
)

