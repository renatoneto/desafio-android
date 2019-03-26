package dev.renatoneto.githubrepos.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.renatoneto.githubrepos.di.AppModule
import dev.renatoneto.githubrepos.di.TestModule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.mockito.MockitoAnnotations

abstract class BaseUnitTest : KoinTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)

        startKoin {

            modules(
                listOf(
                    AppModule.apiModule,
                    TestModule.testDataSourceModule,
                    AppModule.appModule
                )
            )

        }

    }

    @After
    fun tearDown() {
        stopKoin()
    }
}