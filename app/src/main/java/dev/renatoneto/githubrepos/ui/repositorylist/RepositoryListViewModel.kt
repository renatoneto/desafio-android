package dev.renatoneto.githubrepos.ui.repositorylist

import androidx.lifecycle.MutableLiveData
import dev.renatoneto.githubrepos.base.BaseViewModel
import dev.renatoneto.githubrepos.model.github.GithubRepository
import dev.renatoneto.githubrepos.network.github.GithubDataSource
import kotlinx.coroutines.launch

class RepositoryListViewModel(val repository: GithubDataSource) : BaseViewModel() {

    var currentPage = 1

    var lastPageLoaded = 0

    val repositoriesList = ArrayList<GithubRepository>()

    val repositories = MutableLiveData<ArrayList<GithubRepository>>()

    fun loadRepositories() {

        if (currentPage != lastPageLoaded) {

            error.value = null

            jobs add launch {

                loading.value = true

                try {

                    val githubRepositoriesResponse = repository.getRepositoriesList(currentPage)
                        .await()

                    repositoriesList.addAll(githubRepositoriesResponse.items)

                    repositories.value = repositoriesList

                    lastPageLoaded = currentPage

                } catch(t: Throwable) {
                    showError(t)
                } finally {
                    loading.value = false
                }

            }

        }

    }

    fun goToNextPage() {
        currentPage += 1
        loadRepositories()
    }

}
