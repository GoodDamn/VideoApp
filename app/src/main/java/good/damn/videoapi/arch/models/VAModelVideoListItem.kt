package good.damn.videoapi.arch.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "videoList")
@Serializable
data class VAModelVideoListItem(

    @PrimaryKey(autoGenerate = true)
    @SerialName("id")
    val id: Long?,

    @SerialName("title")
    val title: String?,

    @SerialName("thumbnail")
    val thumbnail: String?,

    @SerialName("subtitle")
    val subtitle: String?
)