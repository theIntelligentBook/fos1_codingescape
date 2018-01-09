package units

import info.tweaked.model.unit._
import units.CBOK._

object COSC320 extends HasUnit {

  val it = new TeachingUnit(
    code = "COSC320",

    name = Some("Information Technology Project"),

    description = Seq(
      """
        | This third year unit is the capstone unit of the Bachelor of Computer Science course. In this unit, students
        | will utilise their skills set which has been developed throughout the course to work as part of a team on
        | campus a real-world computing or software engineering project. Projects will be taken from diverse sources
        | including academic linkages, community organisations and industry. Students will be expected to utilise
        | project management tools and version repositories with modern software development methodology being advocated.
      """.stripMargin
    ),

    outcomes = Seq(
      "identify, interpret and analyse stakeholder needs and constraints, uncertainties and risk of the system (social, cultural, legislative, environmental, business, security, privacy, etc.);",
      "tanalyse and evaluate the requirements for a solution to the organisation's information system's needs;",
      "select an appropriate systems solution, assess its impact on campus the organisation and make management recommendations;",
      "apply decision-making methodologies, software engineering practices and project management techniques in a capstone project; and",
      "participate as a member or leader of diverse teams within a multi-level, multi-disciplinary and multi-cultural setting to bring a project to successful completion"
    ),

    taught = Seq(
    ),

    assessment = Seq(
      AssessmentDescription(
        title = "Initial project assessment, requirements, scoping, and feasibility study",
        percentage = 25,
        lo = Seq(1, 2, 3),
        cbok = Seq(abs(5), des(6), eth(3), prof(3), team(6), comm(6), soci(3), prog(6), hf(5), sys(5), proj(6), serv(4), sec(4))
      ),
      AssessmentDescription(
        title = "Mid-project progress report",
        percentage = 25,
        lo = Seq(4, 5),
        cbok = Seq(abs(5), des(6), eth(3), prof(3), team(6), comm(6), soci(3), prog(6), hf(5), sys(5), proj(6), serv(4), sec(4))
      ),
      AssessmentDescription(
        title = "Final project report, presentation and software",
        percentage = 40,
        lo = Seq(1,2,3,4, 5),
        cbok = Seq(abs(5), des(6), eth(3), prof(3), team(6), comm(6), soci(3), prog(6), hf(5), sys(5), proj(6), serv(4), sec(4))
      ),
      AssessmentDescription(
        title = "Client/supervisor feedback survey",
        percentage = 10,
        lo = Seq(1,2,3,4,5),
        cbok = Seq()
      )
    )
  )

}
