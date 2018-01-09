package units

import info.tweaked.model.plan.Prerequisite.minCP
import info.tweaked.model.unit._
import units.CBOK._


object COSC360 extends HasUnit {

  val it = new TeachingUnit(
    code = "COSC360",

    name = Some("Advanced Web Programming"),

    description = Seq(
      """
        |The Web has evolved beyond the simple model of a browser making Hypertext Transfer Protocol (HTTP) requests to a server. Many modern web systems are reactive distributed applications that communicate using Web protocols, or that use the browser as a platform.
        |""".stripMargin,
      """
        |In this unit, students will learn to architect, design, and build applications for the modern Web. This includes protocols for full duplex, server-to-server and browser-to-browser communication. Students will use modern web engineering workflows to target the Web in a testable manner, and will see how tool chains and both server- and client-side frameworks allow programmers to write complex applications for the Web.
      """.stripMargin
    ),

    outcomes = Seq(
      "work with advanced features of HTTP and Web protocols, including full duplex and browser-to-browser communication;",
      "design, develop, and deploy scalable web systems that present well-designed APIs;",
      "use modern front end frameworks and browser features to develop complex web user interfaces;",
      "use web engineering tools and techniques to target the browser in a testable, maintainable manner; and",
      "develop systems that use non-relational databases and cloud computing services. "
    ),

    prerequisite = minCP(72), //COSC220 and

    taught = Seq(
      AssessmentDescription(
        title = "Lectures",
        cbok = Seq(abs(0), des(0), eth(0), prof(0), soci(0), unde(0), net(0), prog(0), hf(0), sys(0), acq(0), serv(0), sec(0))
      ),
      AssessmentDescription(
        title = "Tutorials",
        cbok = Seq(abs(0), des(0), net(0), prog(0), sys(0), acq(0), serv(0), sec(0))
      )
    ),

    assessment = Seq(
      AssessmentDescription(
        title = "Project concept video",
        percentage = 5,
        lo = Seq(2, 3),
        cbok = Seq(abs(4), des(5), comm(3), net(5), hf(3), sys(3), acq(3))
      ),
      AssessmentDescription(
        title = "Project phase 1 demonstration presentation and materials",
        percentage = 15,
        lo = Seq(1, 2, 3, 4, 5),
        cbok = Seq(abs(4), des(5), comm(3), net(5), prog(5), hf(3), sys(3), acq(3), serv(3), sec(3))
      ),
      AssessmentDescription(
        title = "Finished project and demonstration",
        percentage = 35,
        lo = Seq(1, 2, 3, 4, 5),
        cbok = Seq(abs(4), des(5), comm(3), net(5), prog(5), hf(3), sys(3), acq(3), serv(3), sec(3))
      ),
      AssessmentDescription(
        title = "Studio feedback",
        percentage = 5,
        lo = Seq(1, 2, 3, 4, 5),
        cbok = Seq(abs(4), des(6), comm(3), net(6), prog(6), hf(4), sys(4), acq(4), serv(4), sec(4))
      ),
      AssessmentDescription(
        title = "Quizzes and exercisese",
        percentage = 10,
        lo = Seq(1, 2, 3, 4, 5),
        cbok = Seq(abs(2), des(2), net(3), prog(2), hf(2), sys(3), acq(2), serv(2), sec(2))
      ),
      AssessmentDescription(
        title = "Exam",
        description = Some("Supervised examination"),
        percentage = 30,
        lo = Seq(1, 2, 3, 4, 5),
        cbok = Seq(abs(4), des(5), comm(3), net(4), prog(4), hf(4), sys(4), acq(4), serv(4), sec(4))
      )
    )
  )

}
