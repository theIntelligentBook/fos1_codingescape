package units

import info.tweaked.model.plan.Prerequisite.minCP
import info.tweaked.model.unit._
import units.CBOK._


object STAT430 extends HasUnit {

  val it = new TeachingUnit(
    code = "STAT430",

    name = Some("Statistical Learning\n"),

    description = Seq(
      """
        |Data analysis has been transformed in recent years through the huge increase in data collection and advances in computational methods. This has led to rapidly evolving methods in statistical learning and is one of the core research areas in statistics and computer science. This unit introduces modern approaches to computational data analysis for students and researchers in the sciences, with a blended focus on theory and applications, including big data analysis, inference, and prediction.
      """.stripMargin
    ),

    outcomes = Seq(
      "apply statistical learning and data analysis techniques;",
      "exercise critical thinking and problem solving in data analysis using computer software;",
      "consolidate and analyse data in a broad range of contexts; and",
      "present the results of their analysis in a clear and insightful manner."
    ),

    prerequisite = minCP(72), //COSC220 and

    taught = Seq(
      AssessmentDescription(
        title = "Lectures",
        cbok = Seq(abs(0), data(0), prog(0))
      ),
      AssessmentDescription(
        title = "Tutorials",
        cbok = Seq(abs(0), data(0), prog(0))
      )
    ),

    assessment = Seq(
      AssessmentDescription(
        title = "Computational assignment - short answers",
        percentage = 15,
        lo = Seq(1,2,4),
        cbok = Seq(abs(5), data(5))
      ),
      AssessmentDescription(
        title = "Computational assignment - short answers",
        percentage = 15,
        lo = Seq(1,2,4),
        cbok = Seq(abs(5), data(5))
      ),
      AssessmentDescription(
        title = "Computational assignment - short answers",
        percentage = 15,
        lo = Seq(1,2,4),
        cbok = Seq(abs(5), data(5))
      ),
      AssessmentDescription(
        title = "Computational assignment - short answers",
        percentage = 15,
        lo = Seq(1,2,4),
        cbok = Seq(abs(5), data(5))
      ),
      AssessmentDescription(
        title = "Computational assignment leading to a small report and an end of unit presentation",
        percentage = 40,
        lo = Seq(1, 2, 3, 4),
        cbok = Seq(abs(6), des(4), data(5), comm(5), prog(3))
      )
    )
  )

}
