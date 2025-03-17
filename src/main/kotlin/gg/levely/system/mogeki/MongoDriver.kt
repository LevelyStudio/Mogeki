package gg.levely.system.mogeki

import com.mongodb.ConnectionString
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase
import org.slf4j.LoggerFactory

class MongoDriver(uri: String, defaultDatabase: String) {

    private lateinit var mongoClient: MongoClient
    private lateinit var mongoDatabase: MongoDatabase

    companion object {
        val LOGGER = LoggerFactory.getLogger(MongoDriver::class.java)
    }

    init {
        try {
            val connectionString = ConnectionString(uri)
            mongoClient = MongoClients.create(connectionString)
            val database = connectionString.database ?: defaultDatabase
            mongoDatabase = mongoClient.getDatabase(database)
            LOGGER.info("Connected to MongoDB successfully")
        } catch (e: RuntimeException) {
            LOGGER.error("Error while connecting to MongoDB: " + e.message)
        }
    }

    fun isConnected(): Boolean {
        if (!this::mongoClient.isInitialized || !this::mongoDatabase.isInitialized) {
            return false
        }
        return mongoClient.listDatabaseNames().firstOrNull() != null
    }

    fun getDatabase(): MongoDatabase {
        return mongoDatabase
    }

    fun getClient(): MongoClient {
        return mongoClient
    }

    fun close() {
        mongoClient.close()
    }

}