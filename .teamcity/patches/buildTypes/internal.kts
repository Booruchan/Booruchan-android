package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.PullRequests
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.pullRequests
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the buildType with id = 'internal'
accordingly, and delete the patch script.
*/
changeBuildType(RelativeId("internal")) {
    features {
        add {
            pullRequests {
                vcsRootExtId = "${DslContext.settingsRoot.id}"
                provider = github {
                    authType = token {
                        token = "credentialsJSON:48aba3a6-9f18-45d4-9d8d-ec3744e9f450"
                    }
                    filterAuthorRole = PullRequests.GitHubRoleFilter.MEMBER
                }
            }
        }
    }
}
