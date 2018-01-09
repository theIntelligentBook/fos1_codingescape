package units

import info.tweaked.model.unit._
import units.CBOK._

object COSC260 extends HasUnit {

  val it = new TeachingUnit(
    code = "COSC260",

    name = Some("Web Programming"),

    description = Seq(
      """
        |The internet has changed the way people interact with, and think about computers. From online news to social networking sites the diversity of ways that we utilise the internet is immense yet how many people understand how this technology works and how to create a web page?
        | """.stripMargin,
      """
        |In this unit, the fundamentals and modern topics of web development are covered including the Hypertext Transfer Protocol (HTTP); use of HyperText Markup Language (HTML) to generate web pages; the use of Cascading Style Sheets (CSS) to apply stylistic features to enhance web page presentation; providing dynamic user interactions with web pages using Javascript and the use of server-side scripting with session management.
      """.stripMargin
    ),

    outcomes = Seq(
      "describe how web servers and clients communicate via the use of the HTTP;",
      "design and develop web sites to disseminate information using formal languages HTML, CSS and Javascript;",
      "apply stylistic features to enhance the presentation of information on campus web pages;",
      "enhance the dynamism of user interaction with web pages using Javascript to control event handling;",
      "develop dynamic web applications that interact with the Internet's infrastructure; and",
      "demonstrate knowledge and understanding of the issues of intellectual property, privacy, the history of the web and current issues related to web development"
    ),

    taught = Seq(
      AssessmentDescription(
        title = "Lectures",
        percentage = 0,
        lo = Seq.empty,
        cbok = Seq(abs(0), des(0), eth(0), soci(0), net(0), prog(0), hf(0), sys(0), sec(0))
      ),
      AssessmentDescription(
        title = "Lab exercises",
        percentage = 0,
        lo = Seq.empty,
        cbok = Seq(abs(0), des(0), eth(0), soci(0), net(0), prog(0), hf(0), sys(0), sec(0))
      )
    ),

    assessment = Seq(
      AssessmentDescription(
        title = "Five quizzes",
        percentage = 10,
        lo = 1 to 6,
        cbok = Seq(abs(2), des(2), eth(2), soci(2), net(2), prog(2), hf(2), sys(2), sec(2))
      ),
      AssessmentDescription(
        title = "Programming task",
        percentage = 10,
        lo = Seq(2,3),
        cbok = Seq(abs(3), des(3), net(3), prog(3), hf(3), sys(3))
      ),
      AssessmentDescription(
        title = "Programming task",
        percentage = 10,
        lo = Seq(2, 3, 4),
        cbok = Seq(abs(3), des(3), net(3), prog(3), hf(3), sys(3))
      ),
      AssessmentDescription(
        title = "Website development project",
        percentage = 10,
        lo = Seq(2, 3, 5, 6),
        cbok = Seq(abs(3), des(3), net(3), prog(3), hf(3), sys(3), sec(3))
      ),
      AssessmentDescription(
        title = "Exam",
        percentage = 60,
        lo = Seq(1, 2, 3, 4, 5),
        cbok = Seq(abs(3), des(3), net(3), prog(3), hf(2), sys(2), sec(3))
      )
    )
  )

}
