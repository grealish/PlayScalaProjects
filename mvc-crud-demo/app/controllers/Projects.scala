package controllers

import models.Project
import play.api.data.Forms._
import play.api.data._
import play.api.mvc.{Action, Controller}

import scala.collection.mutable.HashMap

/**
 * Created by grealish on 26.08.14.
 */
object Projects extends Controller {

  // we create here the prject object

  val projects: scala.collection.mutable.Map[Int, Project] = new HashMap
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

  def save = Action { implicit request =>
    val project = projectForm.bindFromRequest.get
    projects.put(project.id, project)
    Redirect(routes.Projects.list)
  }

  def edit(id: Int) = Action {
    val bindedForm = projectForm.fill(projects.get(id).get)
    Ok(views.html.projects.edit(bindedForm))
  }

  def update(id: Int) = Action { implicit request =>
    val project = projectForm.bindFromRequest.get
    //project.id = 0
    projects.put(id, project)
    Redirect(routes.Projects.list)
  }

  def list = Action {
    Ok(views.html.projects.list(projects.values))
  }

  def delete(id: Int) = Action {
    projects.remove(id)
    Redirect(routes.Projects.list)
  }
}
