package com.example.datastore.serializers

import androidx.datastore.core.Serializer
import com.example.datastore.CatList
import com.example.datastore.Owner
import com.example.datastore.utils.TestGenerate
import java.io.InputStream
import java.io.OutputStream

class OwnerSerializer : Serializer<Owner> {

    override val defaultValue: Owner = TestGenerate.generateOwner()

    override suspend fun writeTo(t: Owner, output: OutputStream) = t.writeTo(output)

    override suspend fun readFrom(input: InputStream): Owner =
        runCatching { Owner.parseFrom(input) }.getOrThrow()

}