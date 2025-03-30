package jetbrains.kotlin.course.alias.util

import alias.JsCard
import alias.JsTeam
import jetbrains.kotlin.course.alias.card.Card

import jetbrains.kotlin.course.alias.results.GameJsResult
import jetbrains.kotlin.course.alias.results.GameResult
import jetbrains.kotlin.course.alias.team.Team
import jetbrains.kotlin.course.alias.team.TeamService

// convert card to JsCard
fun Card.toJsCard(): JsCard = JsCard(
    id = this.id,
    words = this.words.map { word -> word.word }.toTypedArray()
)

// Convert Team to JsTeam
fun Team.toJsTeam(): JsTeam = JsTeam(
    id = this.id,
    points = this.points,
    name = this.name
)


fun List<Team>.toArrayJsTeams(): Array<JsTeam> =
    this.map { it.toJsTeam() }.toTypedArray()


fun GameJsResult.toGameResult(): GameResult = this.map { jsTeam ->
    TeamService.teamsStorage[jsTeam.id]?.let { existingTeam ->

        Team(
            id = existingTeam.id,
            points = jsTeam.points
        ).apply {

        }
    } ?: error("Unknown team with id: ${jsTeam.id}")
}