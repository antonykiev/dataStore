package com.example.datastore.serializers

import androidx.datastore.core.Serializer
import com.example.datastore.CatList
import java.io.InputStream
import java.io.OutputStream

class CatListSerializer : Serializer<CatList> {

    override val defaultValue: CatList = CatList.getDefaultInstance()

    override suspend fun writeTo(t: CatList, output: OutputStream) = t.writeTo(output)

    override suspend fun readFrom(input: InputStream): CatList =
        runCatching { CatList.parseFrom(input) }.getOrThrow()

}