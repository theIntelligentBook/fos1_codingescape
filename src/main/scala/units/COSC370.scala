package units

import info.tweaked.model.content._
import info.tweaked.model.plan.Prerequisite.minCP
import info.tweaked.model.unit._
import CBOK._


object COSC370 extends HasUnit {

  import AllUnits._

  val it = new TeachingUnit(
    code = "COSC370",

    name = Some("User Experience and Interaction Design"),

    description = Seq(
      """
        | Almost all programs are written to be used by people to solve problems for people. The way that people can use, interact, and work with programs is a fundamental part of the design of a program, not just a surface feature.
        | In this unit, students will use design thinking processes to generate concepts for how people can interact with a program. They will apply theories of human computer interaction to analyse, understand and inform the design of programs. They will also develop interactive programs that use a variety of inputs, and discover how they can empirically investigate how people interact with them and think about them.
      """.stripMargin

    ),

    outcomes = Seq(
      "apply design methods and thinking techniques to generate unique designs for interactive programs;",
      "apply theories of human computer interaction to analyse and inform program designs;",
      "write interactive programs that use a variety of input techniques, including for mobile devices;",
      "empirically investigate how users interact with programs; and",
      "apply common programming design patterns, structures, and development practices for interactive programs."
    ),

    prerequisite = minCP(72), //COSC220 and

    taught = Seq(
      AssessmentDescription(
        title = "Lectures",
        cbok = Seq(abs(0), des(0), eth(0), prof(0), soci(0), unde(0), net(0), prog(0), hf(0), sys(0), acq(0))
      ),
      AssessmentDescription(
        title = "Tutorials",
        cbok = Seq(abs(0), des(0), eth(0), prof(0), soci(0), unde(0), net(0), prog(0), hf(0), sys(0), acq(0))
      )
    ),

    assessment = Seq(
      AssessmentDescription(
        title = "Project concept",
        description = Some("Presentation video"),
        percentage = 5,
        lo = Seq(2),
        cbok = Seq(des(5), comm(5), soci(5), net(3), hf(5))
      ),
      AssessmentDescription(
        title = "Design prototype",
        description = Some("Video and materials"),
        percentage = 20,
        lo = Seq(2, 3, 4, 5),
        cbok = Seq(abs(4), des(5), comm(5), soci(5), net(3), prog(5), hf(5), sys(5), acq(3))
      ),
      AssessmentDescription(
        title = "Finished project demo",
        description = Some("Video and materials"),
        percentage = 20,
        lo = Seq(2, 3, 4, 5),
        cbok = Seq(abs(4), des(5), comm(5), soci(5), net(3), prog(5), hf(5), sys(5), acq(3))
      ),
      AssessmentDescription(
        title = "Studio feedback",
        description = Some("Videos"),
        percentage = 5,
        lo = Seq(2, 3, 4, 5),
        cbok = Seq(abs(4), des(6), comm(6), hf(6), sys(4))
      ),
      AssessmentDescription(
        title = "Reflective Journal",
        description = Some("Written"),
        percentage = 10,
        lo = Seq(2, 3, 4, 5),
        cbok = Seq(abs(4), des(6), comm(6), net(4), prog(4), hf(6), sys(6))
      ),
      AssessmentDescription(
        title = "Quizzes",
        description = Some("Self-paced"),
        percentage = 10,
        lo = Seq(1, 2, 3, 4, 5),
        cbok = Seq(abs(2), des(2), team(2), comm(2), soci(2), unde(2), net(2), prog(2), hf(2), sys(2), acq(2))
      ),
      AssessmentDescription(
        title = "Exam",
        description = Some("Supervised examination"),
        percentage = 30,
        lo = Seq(1, 2, 3, 4, 5),
        cbok = Seq(abs(4), des(4), team(3), comm(4), soci(4), net(3), hf(5), sys(4))
      )
    )
  )

}
