package com.adolfosalado.jav.models

data class Lesson(
    var id: String,
    var name: String,
    var firstDescription: String,
    var firstImage: ByteArray,
    var secondDescription: String,
    var secondImage: ByteArray,
    var thirdDescription: String,
    var userId: String,
    val completed: Boolean,
    var question: List<String>,
    var user: Character,
    var weapons: List<String>,
    var levelOfUser: String = name.split(" ")[1]


) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Lesson

        if (id != other.id) return false
        if (name != other.name) return false
        if (firstDescription != other.firstDescription) return false
        if (!firstImage.contentEquals(other.firstImage)) return false
        if (secondDescription != other.secondDescription) return false
        if (!secondImage.contentEquals(other.secondImage)) return false
        if (thirdDescription != other.thirdDescription) return false
        if (userId != other.userId) return false
        if (completed != other.completed) return false
        if (question != other.question) return false
        if (user != other.user) return false
        if (weapons != other.weapons) return false
        if (levelOfUser != other.levelOfUser) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + firstDescription.hashCode()
        result = 31 * result + firstImage.contentHashCode()
        result = 31 * result + secondDescription.hashCode()
        result = 31 * result + secondImage.contentHashCode()
        result = 31 * result + thirdDescription.hashCode()
        result = 31 * result + userId.hashCode()
        result = 31 * result + completed.hashCode()
        result = 31 * result + question.hashCode()
        result = 31 * result + user.hashCode()
        result = 31 * result + weapons.hashCode()
        result = 31 * result + levelOfUser.hashCode()
        return result
    }
}
