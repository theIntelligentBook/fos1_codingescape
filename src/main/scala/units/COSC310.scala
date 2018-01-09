package units

import info.tweaked.model.unit._

import CBOK._

object COSC310 extends HasUnit {

  val it = new TeachingUnit(
    code = "COSC310",

    name = Some("Software Project Management"),

    description = Seq(
      """
        | Successful software product development requires careful, considered and informed management. Without such management the risks of not meeting customer specifications, project failure, cost and schedule over-runs are greatly amplified.
        |""".stripMargin,
      """
        |This unit will explore the complexities and challenges involved in managing a software project. The entire software life cycle process will be covered including initial planning and effort estimation, procurement and scheduling, team effort and risk management. Professional ethics and quality control are also examined. A range of project management styles and techniques are reviewed including Agile, Scrum and Lean software engineering. Cutting-edge project management topics and entrepreneurial-style practices will also be appraised.
      """.stripMargin
    ),

    outcomes = Seq(
      "demonstrate an understanding of challenges and issues in software project management;",
      "demonstrate a sound knowledge of software effort estimation, project acquisition, life cycle processes, professional ethics, risk management, systems acquisition and change management;",
      "compile and interpret stakeholder and user requirements, including managing changing requirements over the lifecycle of a project; acquire a sound knowledge of IT governance, services management, security and privacy issues, and other societal issues; and",
      "utilise software project management tools that are widely used in the industry"
    ),

    taught = Seq(
      AssessmentDescription(
        title = "Lectures",
        percentage = 0,
        lo = Seq.empty,
        cbok = Seq(abs(0), des(0), eth(0), prof(0), team(0), comm(0), soci(0), unde(0), hw(0), data(0), acq(0), gov(0), proj(0), serv(0), sec(0))
      ),
      AssessmentDescription(
        title = "Tutorials",
        percentage = 0,
        lo = Seq.empty,
        cbok = Seq(abs(0), des(0), eth(0), prof(0), team(0), comm(0), acq(0), proj(0))
      )
    ),

    assessment = Seq(
      AssessmentDescription(
        title = "Short essay",
        percentage = 15,
        lo = Seq(1, 2),
        cbok = Seq(abs(4), des(5), eth(3), prof(3), team(3), comm(3), soci(2), unde(2), hw(2), data(3), acq(3), proj(6))
      ),
      AssessmentDescription(
        title = "Short essay",
        percentage = 15,
        lo = 3 to 3,
        cbok = Seq(abs(4), des(5), eth(3), prof(3), team(3), comm(3), soci(2), unde(2), hw(2), data(3), acq(3), proj(6))
      ),
      AssessmentDescription(
        title = "Group project of 2-3 students",
        percentage = 20,
        lo = Seq(1,2,3,4),
        cbok = Seq(abs(4), des(5), eth(3), prof(3), team(3), comm(6), hw(2), data(3), acq(3), proj(6))
      ),
      AssessmentDescription(
        title = "Exam",
        percentage = 50,
        lo = Seq(1, 2, 3, 4),
        cbok = Seq(abs(3), des(2), eth(2), soci(2), unde(2), hw(2), data(2), gov(6), proj(4))
      )
    )
  )

}
