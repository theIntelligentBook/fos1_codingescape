package units

import info.tweaked.model.unit._

import CBOK._

object COSC110 extends HasUnit {

  val it = new TeachingUnit(
    code = "COSC110",

    name = Some("Introduction to Programming and the UNIX Environment"),

    description = Seq(
      """
        | Modern software development comprises a multi-billion dollar industry, where problem-solving skills along with a solid understanding of programming techniques and practices are vital for producing high quality software. This unit provides foundation knowledge for the study of computer science, introducing techniques for computer program development, using a high-level programming language. This unit will place emphasis on campus a structured approach for solving complex problems by developing and implementing algorithms that break them down into their component tasks and procedures. Learning is re-enforced throughout the unit through practical exercises and assignments that challenge students with interesting problems. The unit also includes material throughout designed to introduce students to the social implications, ethics and professional practice in information technology. Through this unit, students will gain experience using UNIX-type operating systems for developing and debugging computer programs.
        | Topics include: control structures; data types; algorithm design and analysis; basic hardware components; a user view of operating system.
      """.stripMargin

    ),

    outcomes = Seq(
      "solve problems using a structured approach by constructing and implementing algorithms using a high-level programming language, making use of a range of data-types, control structures, parameter passing, functions and procedures;",
      "describe and apply good program development practices and/or coding style to the production of software;",
      "analyse and debug computer programs to describe their purpose and identify logic and syntactical errors whenever they are present;",
      "explain the principles of intellectual property and how they relate to the ethical considerations surrounding control and availability of information technology;",
      "explain the history and status of information technology by identifying key developments and their relationship with current techniques or technologies; and",
      "understand and apply the tools and commands available in UNIX-type operating systems for tasks relating to software development, basic administration and data processing"
    ),

    taught = Seq(
      AssessmentDescription(
        title = "Lectures",
        percentage = 0,
        lo = Seq.empty,
        cbok = Seq(abs(0), des(0), eth(0), prof(0), team(0), comm(0), soci(0), unde(0), hw(0), data(0), prog(0))
      )
    ),

    assessment = Seq(
      AssessmentDescription(
        title = "10 tutorial exercises @ 1% each",
        percentage = 10,
        lo = 1 to 3,
        cbok = Seq(abs(3), des(3), prog(3))
      ),
      AssessmentDescription(
        title = "Theory assignment",
        percentage = 10,
        lo = 4 to 6,
        cbok = Seq(eth(3), comm(3), soci(2), unde(2))
      ),
      AssessmentDescription(
        title = "Assignment",
        percentage = 10,
        lo = Seq(1, 2, 3, 6),
        cbok = Seq(hw(3), prog(3))
      ),
      AssessmentDescription(
        title = "Assignment",
        percentage = 10,
        lo = Seq(1, 2, 3, 6),
        cbok = Seq(hw(3), prog(3))
      ),
      AssessmentDescription(
        title = "Assignment",
        percentage = 10,
        lo = Seq(1, 2, 3, 6),
        cbok = Seq(abs(3), des(3), prof(2), prog(3))
      ),
      AssessmentDescription(
        title = "Exam",
        percentage = 30,
        lo = 1 to 6,
        cbok = Seq(abs(3), des(3), eth(2), prof(2), hw(3), prog(3))
      )
    )
  )

}
