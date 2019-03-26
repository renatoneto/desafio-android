package dev.renatoneto.githubrepos.ui.repositorydetails

import androidx.lifecycle.MutableLiveData
import dev.renatoneto.githubrepos.R
import dev.renatoneto.githubrepos.base.BaseViewModel
import dev.renatoneto.githubrepos.model.github.GithubPullRequest
import dev.renatoneto.githubrepos.model.github.GithubRepository
import dev.renatoneto.githubrepos.network.github.GithubDataSource
import kotlinx.coroutines.launch

class RepositoryDetailsViewModel(private val repository: GithubDataSource,
                                 val githubRepository: GithubRepository) : BaseViewModel() {

    var contentLoaded = false

    val pullRequests = MutableLiveData<ArrayList<GithubPullRequest>>()

    val pullRequestsList = ArrayList<GithubPullRequest>()

    fun loadPullRequests() {

        if (!contentLoaded) {

            contentLoaded = true
            error.value = null

            jobs add launch {

                loading.value = true

                try {

                    val pullRequestsResponse = repository.getPullRequestsList(
                        githubRepository.owner.login,
                        githubRepository.name
                    ).await()

                    pullRequestsList.addAll(pullRequestsResponse)

                    pullRequests.value = pullRequestsList

                    if (pullRequestsList.size == 0) {
                        error.value = R.string.error_no_pull_requests
                    }

                } catch(t: Throwable) {
                    showError(t)
                } finally {
                    loading.value = false
                }


            }

        }

    }

}
