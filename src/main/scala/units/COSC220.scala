package units

import info.tweaked.model.plan.Prerequisite.minCP
import info.tweaked.model.unit._
import units.CBOK._


object COSC220 extends HasUnit {

  val it = new TeachingUnit(
    code = "COSC220",

    name = Some("Software Development Studio 2"),

    description = Seq(
      """
        | Modern software development is highly collaborative. Teams of developers work together to take software from conception into production, and continue to evolve and develop the software based on campus feedback from its use. This introduces a number of engineering challenges, as the teams must ensure that the program works and is well designed even while its design and code are being modified by many people at once. Many of the software engineering practices and tool chains that are core to a developer's work are designed to support this collaborative nature of programming.
        | In this unit, students work in teams to develop different features of a class-wide development project, to learn how software engineers design, build, deploy, and modify large programs together. The project introduces continuous integration, deployment, and delivery practices, as well as lean and agile software development.
      """.stripMargin

    ),

    outcomes = Seq(
      "work with other programmers and other teams of programmers on campus large software projects",
      "apply collaborative software development practices and tool chains;",
      "design and develop features for a software system, from conception through to testing, deployment, and continuous improvement;",
      "design, model, and investigate user interaction and user experience with software;",
      "apply techniques for verifying the quality of software during development and explain the ethical considerations around software failure risks and their impact; and",
      "investigate, analyse, understand, and modify the design of program code, including code written by others"
    ),

    prerequisite = minCP(72), //and COSC220.it,

    taught = Seq(
      AssessmentDescription(
        title = "Lectures",
        percentage = 0,
        lo = Seq.empty,
        cbok = Seq(abs(0), des(0), eth(0), prof(0), team(0), comm(0), soci(0), unde(0), data(0), net(0), prog(0), hf(0), sys(0), acq(0), gov(0), proj(0), serv(0))
      ),
      AssessmentDescription(
        title = "Tutorials",
        percentage = 0,
        lo = Seq.empty,
        cbok = Seq(abs(0), des(0), prof(0), soci(0), net(0), prog(0), hf(0), sys(0), acq(0))
      )
    ),

    assessment = Seq(
      AssessmentDescription(
        title = "Online quizzes and Exercises.",
        percentage = 10,
        lo = Seq(2, 3, 5, 6),
        cbok = Seq(abs(2), des(2), prof(2), data(2), prog(2), hf(2), sys(2), acq(2), gov(2), proj(2), serv(2))
      ),
      AssessmentDescription(
        title = "Collaborative software development project",
        percentage = 40,
        lo = 1 to 6,
        cbok = Seq(abs(5), des(5), eth(3), prof(3), team(3), comm(3), soci(3), unde(3), data(4), net(3), prog(5), hf(4), sys(4), acq(3), proj(3))
      ),
      AssessmentDescription(
        title = "Individual development reflection",
        percentage = 10,
        lo = 2 to 6,
        cbok = Seq(abs(4), des(4), prof(4), team(4), comm(4), data(4), hf(4), sys(6), proj(4))
      ),
      AssessmentDescription(
        title = "Studio feedback",
        percentage = 5,
        lo = 1 to 6,
        cbok = Seq(des(6), prof(3), team(3), comm(3), hf(3), sys(4))
      ),
      AssessmentDescription(
        title = "Exam",
        percentage = 35,
        lo = Seq(1, 2, 3, 4, 5),
        cbok = Seq(abs(4), des(4), prof(3), team(3), data(3), hf(3), sys(4), acq(3), proj(3), serv(3))
      )
    )
  )

}
