package com.example.datastore.utils

import com.example.datastore.Cat
import com.example.datastore.CatList
import com.example.datastore.Owner

object TestGenerate {

    fun generateCats() =
        listOf(
            Cat.newBuilder()
                .setName("TOM")
                .setSex(Cat.Sex.MALE)
                .setWeight(13)
                .setLength(10)
                .build(),
            Cat.newBuilder()
                .setName("VASKA")
                .setSex(Cat.Sex.MALE)
                .setWeight(13)
                .setLength(10)
                .build(),
            Cat.newBuilder()
                .setName("RYJYK")
                .setSex(Cat.Sex.MALE)
                .setWeight(13)
                .setLength(10)
                .build(),
            Cat.newBuilder()
                .setName("ASYA")
                .setSex(Cat.Sex.FEMALE)
                .setWeight(13)
                .setLength(10)
                .build()
        )

    fun generateOwner(): Owner {
        return Owner.newBuilder()
            .setAddress(
                Owner.Address.newBuilder()
                    .setStreet("Westminster, London, England")
                    .setIndex("888888")
                    .build()
            )
            .setName("Sherlock Holmes")
            .setCats(CatList.newBuilder()
                .addAllCatList(generateCats())
                .build()
            )
            .build()
    }
}