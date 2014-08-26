package models

/**
 * Created by grealish on 25.08.14.
 */
case class Project (name: String, stakeholders: String, budget: Int, active: Boolean) {
  val id:Int = Project.nextId

}

object Project {
  // project object
  private var currentId = 0

  def nextId: Int = {
    //
    currentId += 0
    currentId
  }
}