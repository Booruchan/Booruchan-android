package build

import jetbrains.buildServer.configs.kotlin.v2019_2.PublishMode
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.gradle

object Gelbooru : PipelineBuildVcs("Gelbooru", {

    publishArtifacts = PublishMode.SUCCESSFUL
    artifactRules = "./gelbooru/build/libs/* => gelbooru"

    dependencies {
        snapshot(Core) {}
        artifacts(Core) {
            artifactRules = "core/* => ./gelbooru/libs"
        }
    }

    steps {
        gradle {
            name = "$name module build"
            tasks = ":gelbooru:build"
            gradleParams = "-Pjarable"
            buildFile = "build.gradle.kts"
        }
    }
})