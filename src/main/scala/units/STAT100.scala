package units

import info.tweaked.model.plan.Prerequisite.MinCP
import info.tweaked.model.unit._
import units.CBOK._


object STAT100 extends HasUnit {

  val it = new TeachingUnit(
    code = "STAT100",

    name = Some("Introduction to Statistical Modelling"),

    description = Seq(
      """
        |Many advances in modern science, ranging from livestock experiments to analysis of Martian soils, rely on statistical methods that link observation or experiment to a better understanding of how complex systems work. In this introductory unit, students both develop statistical literacy and learn how to present and interpret data for a common set of statistical models.
        |""".stripMargin,
      """
        |Topics include exploratory data analysis, randomness associated with experiment data, statistical modelling, sampling, probability, regression, analysis of variance, and tests of significance. Statistical computing forms an integral part of the unit. The R statistical software will be supplied.
      """.stripMargin
    ),

    outcomes = Seq(
      "summarise, display and interpret data sets;",
      "use computer software to generate statistics and graphical representations of data;",
      "fit and interpret simple statistical models;",
      "produce a report that is understandable by someone with little statistical knowledge, which describes statistical analysis, results and conclusions; and",
      "read, understand and critique articles that contain statistical information."
    ),

    taught = Seq(
      AssessmentDescription(
        title = "Lectures",
        cbok = Seq(abs(0), des(0), data(0))
      ),
      AssessmentDescription(
        title = "Tutorials",
        cbok = Seq(abs(0), des(0), data(0))
      )
    ),

    assessment = Seq(
      AssessmentDescription(
        title = "Eight online assignments (requires calculations)",
        percentage = 40,
        lo = Seq(1,2,3,5),
        cbok = Seq(abs(3), des(3), data(3))
      ),
      AssessmentDescription(
        title = "Written assignment (requires calculations and a report)",
        percentage = 10,
        lo = Seq(1,2,3,4,5),
        cbok = Seq(abs(3), des(3), data(3))
      ),
      AssessmentDescription(
        title = "Exam",
        description = Some("Supervised examination"),
        percentage = 50,
        lo = Seq(1, 2, 3, 4,5),
        cbok = Seq(abs(3), des(3))
      )
    )
  )

}
