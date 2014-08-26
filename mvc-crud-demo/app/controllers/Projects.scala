package controllers

import models.Project
import play.api.data._
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}
import scala.collection.mutable.Map
import scala.collection.immutable.HashMap

/**
 * Created by grealish on 26.08.14.
 */
object Projects extends Controller {

  // we create here the prject object

  val projects: Map[Int, Project] = new HashMap
  // case class Project (name: String, stakeholders: String, budget: String, active: boolean,)
  val projectForm = Form (mapping(
        "name" -> text,
        "stakeholders" -> text,
        "budget" -> number,
        "active" -> boolean)(Project.apply)(Project.unapply)
    )

  def add = Action {
    Ok(views.html.projects.add(projectForm))
  }

  def save = Action {
    val project = projectForm.bindFromRequest.get
    projects.put(project.id, project)
    Redirect(routes.Projects.list)
  }

  def edit(id: Int) = Action {
    val binded = projectForm.fill(projects.get(id).get)
    Ok(views.html.users.edit(bindedForm))
  }

  def update(id: Int) = Action {
    // bindedForm = form.fill(projects.
    val project = projectForm.bindFromRequest.get
    project.id = id
    projects.put(id, user)
    Redirect(routes.Projects.list)
  }
}
