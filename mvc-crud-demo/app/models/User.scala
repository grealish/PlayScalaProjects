package models

case class User(username: String, firstname: String, lastname: String, email: String) {
    var id:Int = User.nextId
}

object User {
    // user object methods go here
    private var currentId = 0
    
    def nextId: Int = {
        // used to increment ID
        currentId += 1
        currentId
    }
}