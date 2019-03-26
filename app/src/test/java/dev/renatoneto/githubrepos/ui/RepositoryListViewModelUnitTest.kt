package dev.renatoneto.githubrepos.ui

import dev.renatoneto.githubrepos.R
import dev.renatoneto.githubrepos.base.BaseUnitTest
import dev.renatoneto.githubrepos.extension.toDeferred
import dev.renatoneto.githubrepos.network.github.GithubTestService
import dev.renatoneto.githubrepos.ui.repositorylist.RepositoryListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withContext
import org.junit.Assert.assertEquals
import org.junit.Test
import org.koin.test.inject
import org.mockito.Mockito.*
import java.io.IOException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RepositoryListViewModelUnitTest : BaseUnitTest() {

    private val viewModel by inject<RepositoryListViewModel>()

    @Test
    fun testeBeforeRequest() {
        assertEquals(viewModel.currentPage, 1)
        assertEquals(viewModel.lastPageLoaded, 0)
    }

    @Test
    fun testLastPageLoadedAfterSamePageRequest() {
        viewModel.lastPageLoaded = 1
        viewModel.loadRepositories()

        assertEquals(viewModel.lastPageLoaded, 1)
    }

    @Test
    fun testGetRepositoriesSuccesss() {

        runBlocking {
            Dispatchers.setMain(Dispatchers.IO)

            `when`(viewModel.repository.getRepositoriesList(1))
                .thenReturn(GithubTestService.listResponse.toDeferred())
        }

        viewModel.loadRepositories()
        assertEquals(viewModel.lastPageLoaded, 1)
        assertEquals(viewModel.repositoriesList, GithubTestService.listResponse.items)
    }

    /*@Test
    fun testGetRepositoriesEmptyResponse() {

        runBlocking {
            `when`(viewModel.repository.getRepositoriesList(1))
                .thenReturn(GithubTestService.listEmptyResponse.toDeferred())
        }

        viewModel.loadRepositories()
        assertEquals(viewModel.repositoriesList, GithubTestService.listEmptyResponse.items)
    }

    @Test
    fun testNetworkError() {
        val exception = mock(IOException::class.java)

        runBlocking {

            `when`(viewModel.repository.getRepositoriesList(1))
                .thenThrow(exception)
        }

        viewModel.loadRepositories()
        assertEquals(viewModel.error.value, R.string.error_connection)
    }

    @Test
    fun testUnexpectedError() {
        val exception = mock(Exception::class.java)

        runBlocking {
            `when`(viewModel.repository.getRepositoriesList(1))
                .thenThrow(exception)
        }

        viewModel.loadRepositories()
        assertEquals(viewModel.error.value, R.string.error_unexpected)
    }*/

}
