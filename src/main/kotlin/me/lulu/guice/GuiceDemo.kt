package me.lulu.guice

import com.google.inject.AbstractModule
import com.google.inject.Guice
import com.google.inject.Inject
import com.google.inject.Provides

class Printer {

    @Inject
    lateinit var applicationService: ApplicationService

    fun print() {
        println(applicationService.getAppName())
    }
}


class MessageModule : AbstractModule() {
    @Provides
    fun provideApplicationService(): ApplicationService {
        return ApplicationService()
    }

    @Provides
    fun provideDatabase(): Database {
        return Database()
    }
}

class ApplicationService {
    @Inject
    lateinit var database: Database

    fun getAppName(): String = "Guice Demo" + " " + database.getDatabaseName()
}

class Database {
    fun getDatabaseName(): String = "Guice Demo Database"
}

fun main() {
    val injector = Guice.createInjector(MessageModule())
    val printer = injector.getInstance(Printer::class.java)

    printer.print()
}