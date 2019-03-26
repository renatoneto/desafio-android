package dev.renatoneto.githubrepos.di

import dev.renatoneto.githubrepos.network.github.GithubDataSource
import org.koin.dsl.module
import org.mockito.Mockito

object TestModule {

    val testDataSourceModule = module {

        single { Mockito.mock(GithubDataSource::class.java)}

    }

}