package units

import info.tweaked.model.unit._
import units.CBOK._

object COSC591 extends HasUnit {

  val it = new TeachingUnit(
    code = "COSC591",

    name = Some("Information Technology Project"),

    description = Seq(
      """
        |This is a postgraduate project in information technology. The student will apply skills and knowledge in information technology to real-world situations and produce industry standard documentation and solutions.
      """.stripMargin
    ),

    outcomes = Seq(
      "apply knowledge and skills to demonstrate autonomy, expert judgement, adaptability and responsibility in a group work environment;",
      "analyse requirements, critically evaluate options, conduct research and present a proposed solution to clients and other stakeholders;",
      "integrate proposed tools/concepts into systems, conduct research and develop software or technology-based solutions to real-world information technology problems; and",
      "document information technology solutions to industry standard and present process and outcomes to specialist and non-specialist audiences"
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
        title = "Project completion, user manuals",
        percentage = 20,
        lo = Seq(1,2,3,4),
        cbok = Seq(abs(5), des(6), eth(3), prof(3), team(6), comm(6), soci(3), prog(6), hf(5), sys(5), proj(6), serv(4), sec(4))
      )
    )
  )

}
