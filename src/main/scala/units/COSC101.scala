package units

import info.tweaked.model.plan.Prerequisite.minCP
import info.tweaked.model.unit._
import units.CBOK._


object COSC101 extends HasUnit {

  val it = new TeachingUnit(
    code = "COSC101",

    name = Some("Software Development Studio 1"),

    description = Seq(
      """
        | Software development is a collaborative undertaking that requires the integration of different technologies to build solutions that are elegant and intuitive.
        | This unit introduces students to the fundamentals of how software is produced. They will develop a strong understanding of how data is represented and manipulated within a computer system by solving problems in a series of collaborative software development tasks. Students will build interactive software in a high-level programming language and apply the skills needed to effectively communicate design choices.
      """.stripMargin

    ),

    outcomes = Seq(
      "explain how data and information is represented within a computer system and how it is manipulated to solve problems in a range of disciplines;",
      "solve problems and design software solutions using a high-level programming language and a range of technologies, protocols and algorithms;",
      "apply basic collaborative software development practices to work effectively with team members on campus small-scale software development projects;",
      "understand basic interaction design principles and apply these to the development of interactive software;",
      "demonstrate effectively written and oral communication skills to convey information, approaches and design decisions to a range of audiences"
    ),

    taught = Seq(
      AssessmentDescription(
        title = "Lectures",
        percentage = 0,
        lo = Seq.empty,
        cbok = Seq(abs(0), des(0), eth(0), team(0), comm(0), soci(0), hw(0), data(0), net(0), prog(0), hf(0))
      ),
      AssessmentDescription(
        title = "Tutorials",
        percentage = 0,
        lo = Seq.empty,
        cbok = Seq(abs(0), des(0), hw(0), data(0), net(0))
      )
    ),

    assessment = Seq(
      AssessmentDescription(
        title = "Collaborative software development task",
        percentage = 30,
        lo = 1 to 5,
        cbok = Seq(abs(3), des(3), team(3), comm(3), hw(3), data(3), net(2), hf(2))
      ),
      AssessmentDescription(
        title = "Collaborative software development task",
        percentage = 30,
        lo = 1 to 5,
        cbok = Seq(abs(3), des(3), team(3), comm(3), hw(3), data(3), net(2), hf(2))
      ),
      AssessmentDescription(
        title = "Individual development reflection",
        percentage = 10,
        lo = 2 to 6,
        cbok = Seq(abs(3), des(3), team(3), comm(3), hw(3), data(3), net(2), hf(2))
      ),
      AssessmentDescription(
        title = "Exam",
        percentage = 30,
        lo = Seq(1, 2, 3, 4, 5),
        cbok = Seq(abs(3), des(3), team(3), comm(3), hw(3), data(3))
      )
    )
  )

}
