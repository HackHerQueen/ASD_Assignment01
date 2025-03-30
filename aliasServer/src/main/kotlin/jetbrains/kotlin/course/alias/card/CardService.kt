package jetbrains.kotlin.course.alias.card

import org.springframework.stereotype.Service

import jetbrains.kotlin.course.alias.util.IdentifierFactory
import jetbrains.kotlin.course.alias.util.words
@JvmInline value class Word(val word: String)

@Service
class CardService {
    private val identifierFactory = IdentifierFactory()
    val cards: List<Card> = generateCards()

    companion object {

        private const val WORDS_IN_CARD: Int = 4


        val cardsAmount: Int get() = words.size / WORDS_IN_CARD
    }

    private fun generateCards(): List<Card> {
        return words
            .shuffled()
            .chunked(WORDS_IN_CARD)
            .take(cardsAmount)
            .map { wordList ->
                Card(
                    // Generation of unique ID
                    id = identifierFactory.uniqueIdentifier(),
                    words = wordList.toWords()
                )
            }
    }


    private fun List<String>.toWords(): List<Word> = this.map { Word(it) }

    fun getCardByIndex(index: Int): Card {
        return cards.getOrNull(index)
            ?: throw Throwable("Card with index $index doesn't exist")
    }
}
fun main() {
    val service = CardService()

    println("Total cards generated: ${service.cards.size}")
    println("First card words: ${service.cards.first().words}")

    try {
        service.getCardByIndex(999) // Should throw
    } catch (e:Throwable) {
        println("Correctly handled invalid index: ${e.message}")
    }
}
