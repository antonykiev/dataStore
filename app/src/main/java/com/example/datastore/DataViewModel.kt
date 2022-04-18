package com.example.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import com.example.datastore.serializers.CatListSerializer
import com.example.datastore.serializers.OwnerSerializer
import com.example.datastore.utils.TestGenerate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataViewModel: ViewModel() {

    private val catListName = "catList.pb"
    private val ownerName = "ownerName.pb"

    private val Context.prefStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    private val EXAMPLE_COUNTER = intPreferencesKey("counter")

    private val Context.catDataStore: DataStore<CatList> by dataStore(
        fileName = catListName,
        serializer = CatListSerializer()
    )

    private val Context.ownerDataStore: DataStore<Owner> by dataStore(
        fileName = ownerName,
        serializer = OwnerSerializer()
    )

    suspend fun loadQuantity(context: Context): Flow<Int> {
        incrementQuantity(context)
        return context.prefStore.data
            .map { it[EXAMPLE_COUNTER] ?: 1 }
    }

    private suspend fun incrementQuantity(context: Context) {
        context.prefStore.edit { settings ->
            val currentCounterValue = settings[EXAMPLE_COUNTER] ?: 0
            settings[EXAMPLE_COUNTER] = currentCounterValue + 1
        }
    }

    suspend fun loadOwner(context: Context): Flow<Owner> {
        return context.ownerDataStore.data
    }

    suspend fun loadCatList(context: Context): Flow<List<Cat>> {
        context.catDataStore.updateData { store ->
            val builder = store.toBuilder()

            if (store.catListCount < 1) {
                builder
                    .clearCatList()
                    .addAllCatList(TestGenerate.generateCats())
            }
            return@updateData builder.build()
        }

        return context.catDataStore.data.map { it.catListList }
    }
}