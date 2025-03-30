package jetbrains.kotlin.course.alias.results
import jetbrains.kotlin.course.alias.team.Team
import jetbrains.kotlin.course.alias.team.TeamService
import org.springframework.stereotype.Service

typealias GameResult = List<Team>

@Service
class GameResultsService {
    companion object {
        val gameHistory: MutableList<GameResult> = mutableListOf()
    }

    fun saveGameResults(result: GameResult) {

        require(result.isNotEmpty()) { "Game result must not be empty" }
        val missingTeams = result.filter { it.id !in TeamService.teamsStorage }
        require(missingTeams.isEmpty()) {
            "Teams with IDs ${missingTeams.joinToString { it.id.toString() }} not found in storage"
        }

        gameHistory.add(result)
    }
    fun getAllGameResults(): List<GameResult> {
        return gameHistory.reversed()
    }
}
