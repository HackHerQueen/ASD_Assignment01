package jetbrains.kotlin.course.alias.team

import org.springframework.stereotype.Service
import jetbrains.kotlin.course.alias.util.Identifier
import jetbrains.kotlin.course.alias.util.IdentifierFactory

@Service
class TeamService {
    private val identifierFactory = IdentifierFactory()

    companion object {
        val teamsStorage: MutableMap<Identifier, Team> = mutableMapOf()
    }


    fun generateTeamsForOneRound(teamsNumber: Int): List<Team> {
        return List(teamsNumber) {
            Team(id = identifierFactory.uniqueIdentifier()).also { team ->
                teamsStorage[team.id] = team
            }
        }


    }
}