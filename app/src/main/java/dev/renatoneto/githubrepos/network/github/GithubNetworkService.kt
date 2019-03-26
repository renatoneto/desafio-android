package dev.renatoneto.githubrepos.network.github

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GithubNetworkService(private val api: GithubApi) : GithubDataSource {

    override suspend fun getRepositoriesList(page: Int) = withContext(Dispatchers.IO) {
        api.getRepositoriesList("language:Java", "stars", page)
    }

    override suspend fun getPullRequestsList(owner: String, repository: String) = withContext(Dispatchers.IO) {
        api.getPullRequestsList(owner, repository)
    }

}