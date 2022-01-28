package uz.texnopos.paging3.data.model.response

import uz.texnopos.paging3.data.model.Payload

data class GenericResponse<T>(
    val code: Int = 0,
    val message: String = "",
    val payload: Payload,
    val successful: Boolean
)