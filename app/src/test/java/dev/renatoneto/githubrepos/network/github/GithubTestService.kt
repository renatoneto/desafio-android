package dev.renatoneto.githubrepos.network.github

import dev.renatoneto.githubrepos.model.github.GithubPullRequest
import dev.renatoneto.githubrepos.model.github.GithubRepository
import dev.renatoneto.githubrepos.model.github.GithubUser
import dev.renatoneto.githubrepos.network.github.data.GithubRepositoriesResponse

class GithubTestService {

    companion object {

        val user = GithubUser("login", "https://avatars2.githubusercontent.com/u/858781")

        val listResponse = GithubRepositoriesResponse(
            arrayListOf(
                GithubRepository("repository 1", user, "repository description", 100, 50)
            )
        )

        val listEmptyResponse = GithubRepositoriesResponse(arrayListOf())

        val pullRequestsResponse = arrayListOf(
            GithubPullRequest(
                "pull request title", "pull request body",
                1337, "https://google.com", user
            )
        )

        val pullRequestsEmptyResponse = arrayListOf<GithubPullRequest>()

    }

}