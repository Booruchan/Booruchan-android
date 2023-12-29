package com.makentoshe.booruchan.feature.mapper

import com.makentoshe.booruchan.feature.entity.TagType
import org.booruchan.extension.sdk.entity.NetworkTagType
import javax.inject.Inject

class NetworkTagType2TagTypeMapper @Inject constructor() {
    fun map(networkTagType: NetworkTagType) = when (networkTagType) {
        NetworkTagType.General -> TagType.General
        NetworkTagType.Artist -> TagType.Artist
        NetworkTagType.Character -> TagType.Character
        NetworkTagType.Copyright -> TagType.Copyright
        NetworkTagType.Metadata -> TagType.Metadata
        NetworkTagType.Other -> TagType.Other
    }
}
