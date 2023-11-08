package pt.ipca.whaza.models

class User(id: Int, name: String, email: String, password: String, groupid: Int) {
    val id: Int
    val name: String
    val email: String
    val password: String
    val groupid: Int

    init {
        this.id = id
        this.name = name
        this.email = email
        this.password = password
        this.groupid = groupid
    }
}