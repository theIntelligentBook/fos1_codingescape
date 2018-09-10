package units

import info.tweaked.model.plan.Prerequisite.MinCP
import info.tweaked.model.unit._
import units.CBOK._


object STAT210 extends HasUnit {

  val it = new TeachingUnit(
    code = "STAT210",

    name = Some("Statistical Modelling and Experimental Design"),

    description = Seq(
      """
        |Regression models are the foundation of many statistical models and experimental designs. In this unit, students learn to develop and analyse linear and logistic regression-based statistical models, and are taught important experimental designs used by many fields. This unit is appropriate for students in the natural or social sciences interested in developing and applying statistical models in their fields, as well as for students in the computational and mathematical sciences interested in learning about applied statistics.
        |""".stripMargin,
      """
        |Topics include: design and analysis of experiments with one or two factors; orthogonal partitions; block designs; multiple regression, including use of indicator variables; and an introduction to logistic regression. Students undertaking this unit at postgraduate level will also be required to complete an advanced special reading topic.
      """.stripMargin
    ),

    outcomes = Seq(
      "analyse data, and interpret and communicate results and conclusions, from a wide range of experimental designs;",
      "fit and interpret more complex statistical models, including the linear model; and",
      "build on and broaden their theoretical and technical knowledge of statistical terminology, concepts and methodology, which will enable them to read and critically appraise scientific literature with some confidence."
    ),

    prerequisite = MinCP(72), //COSC220 and

    taught = Seq(
      AssessmentDescription(
        title = "Lectures",
        cbok = Seq(abs(0), des(0), data(0), prog(0))
      ),
      AssessmentDescription(
        title = "Tutorials",
        cbok = Seq(abs(0), des(0), data(0), prog(0))
      )
    ),

    assessment = Seq(
      AssessmentDescription(
        title = "Multiple Regression and Polynomial Regression.",
        percentage = 12,
        lo = Seq(1,2,3),
        cbok = Seq(abs(4), des(4), data(4), prog(3))
      ),
      AssessmentDescription(
        title = "Indicator Variables and Contrasts",
        percentage = 12,
        lo = Seq(1,2,3),
        cbok = Seq(abs(4), des(4), data(4), prog(3))
      ),
      AssessmentDescription(
        title = "Blocking, Transformations and Multi-Factor Designs",
        percentage = 12,
        lo = Seq(1,2,3),
        cbok = Seq(abs(4), des(4), data(4), prog(3))
      ),
      AssessmentDescription(
        title = "Logistic Regression",
        percentage = 12,
        lo = Seq(1,2,3),
        cbok = Seq(abs(4), des(4), data(4), prog(3))
      ),
      AssessmentDescription(
        title = "Exam",
        description = Some("Supervised examination"),
        percentage = 50,
        lo = Seq(1, 2, 3),
        cbok = Seq(abs(4), des(4), data(4))
      )
    )
  )

}
