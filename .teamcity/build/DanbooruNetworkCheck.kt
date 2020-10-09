package build

import jetbrains.buildServer.configs.kotlin.v2019_2.PublishMode
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.gradle

/**
 * Checks Danbooru's real api to be sure, that is all works properly
 * */
object DanbooruNetworkCheck : PipelineBuildDaily("Danbooru network check", 0, 0, {
    description = "Checks Danbooru's real api to be sure, that it is works properly"

    publishArtifacts = PublishMode.NORMALLY_FINISHED
    artifactRules = """
        ./danbooru-api-check/build/reports/jacoco/test/html-zip/* => jacocoHtmlReport
    """.trimIndent()

    dependencies {
        snapshot(Core) {}
        artifacts(Core) {
            artifactRules = "core/* => ./danbooru-api-check/libs"
        }
        snapshot(Danbooru) {}
        artifacts(Danbooru) {
            artifactRules = "danbooru/* => ./danbooru-api-check/libs"
        }
    }

    steps {
        gradle {
            name = "$name module api check"
            tasks = ":danbooru-api-check:clean :danbooru-api-check:build --info"
            gradleParams = "-Pjarable -Pnetable"
            buildFile = "build.gradle.kts"
        }
    }
})