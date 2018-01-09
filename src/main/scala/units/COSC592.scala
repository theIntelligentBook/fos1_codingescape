package units

import info.tweaked.model.unit._
import units.CBOK._

object COSC592 extends HasUnit {

  val it = new TeachingUnit(
    code = "COSC592",

    name = Some("Masters Information Technology Project"),

    description = Seq(
      """
        |This is a Masters level project in major information technology. Students will apply the skills and knowledge they have acquired throughout their course to a realistic computing or software engineering project. Projects will be taken from a variety of sources, and will be representative of realistic development or innovation projects. Students are expected to investigate and scope the problem, to design, implement, and evaluate an appropriate solution, and to manage the project itself.
        |""".stripMargin,
      """
        |Where feasible, projects will be undertaken by groups of students, as required by the estimated size of the problem.
      """.stripMargin
    ),

    outcomes = Seq(
      "investigate, critically analyse and research, with advanced understanding, stakeholder needs, constraints, risks, and potential benefits for a problem domain;",
      "generate, analyse, research, evaluate and propose potential solutions to a client, using expert, specialised cognitive and technical skills;",
      "apply knowledge, skills and expert judgement to develop, document, deploy, research and evaluate a solution, taking into account client feedback during the development process;",
      "apply expert level decision-making, software engineering, computer science, and project management knowledge, methodologies, and techniques to a development project; and",
      "interpret and transmit progress and evaluation outcomes of a development project, during and after its implementation to specialist and non-specialist audiences, with advanced and integrated understanding. "
    ),

    taught = Seq(
    ),

    assessment = Seq(
      AssessmentDescription(
        title = "Project proposal",
        percentage = 15,
        lo = Seq(1, 2, 3, 4, 5),
        cbok = Seq(abs(5), des(6), eth(3), prof(3), team(6), comm(6), soci(3), prog(6), hf(5), sys(5), proj(6), serv(4), sec(4))
      ),
      AssessmentDescription(
        title = "Mid-project progress report and design documentation",
        percentage = 15,
        lo = Seq(1,2,3,4,5),
        cbok = Seq(abs(5), des(6), eth(3), prof(3), team(6), comm(6), soci(3), prog(6), hf(5), sys(5), proj(6), serv(4), sec(4))
      ),
      AssessmentDescription(
        title = "Final project report, presentation and software",
        percentage = 50,
        lo = Seq(1,2,3,4, 5),
        cbok = Seq(abs(5), des(6), eth(3), prof(3), team(6), comm(6), soci(3), prog(6), hf(5), sys(5), proj(6), serv(4), sec(4))
      ),
      AssessmentDescription(
        title = "Client/supervisor feedback survey",
        percentage = 10,
        lo = Seq(1,2,3,4),
        cbok = Seq()
      )
    )
  )

}
