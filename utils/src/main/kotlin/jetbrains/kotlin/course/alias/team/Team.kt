package jetbrains.kotlin.course.alias.team

import jetbrains.kotlin.course.alias.util.Identifier

data class Team(
    val id: Identifier,
    val points: Int = 0
) {
    val name: String = "Team#${id + 1}"
}

fun main() {
    val team1 = Team(id = 0)
    val team2 = Team(id = 1, points = 5)

    println(team1)
    println(team1.name)
    println(team2)
    println(team2.name)
}