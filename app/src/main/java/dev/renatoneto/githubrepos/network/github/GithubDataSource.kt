package dev.renatoneto.githubrepos.network.github

import dev.renatoneto.githubrepos.model.github.GithubPullRequest
import dev.renatoneto.githubrepos.network.github.data.GithubRepositoriesResponse
import kotlinx.coroutines.Deferred

interface GithubDataSource {

    suspend fun getRepositoriesList(page: Int): Deferred<GithubRepositoriesResponse>

    suspend fun getPullRequestsList(owner: String, repository: String): Deferred<ArrayList<GithubPullRequest>>

}