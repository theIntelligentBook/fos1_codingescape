package units

import info.tweaked.model.plan.Prerequisite.minCP
import info.tweaked.model.unit._
import units.CBOK._


object AMTH140 extends HasUnit {

  val it = new TeachingUnit(
    code = "AMTH140",

    name = Some("Discrete Mathematics"),

    description = Seq(
      """
        |Discrete mathematics lays the foundation for studies in computer science and mathematics. Topics in this unit support the general themes of Logic and Proof, Induction and Recursion, Discrete Structures, and Algorithms and their Analysis. Applications include digital logic circuits and number systems, cryptography, finding minimum spanning trees and circuits, analysing the efficiency of sorting and searching algorithms, and topological sorting for scheduling.
      """.stripMargin
    ),

    outcomes = Seq(
      "demonstrate competency and fluency in the terminology and notation of sets, symbolic logic and predicate calculus;",
      "apply algorithms such as sorting algorithms, Horner's scheme, Kruskal's algorithm;",
      "demonstrate a theoretical and technical understanding of graphs;",
      "solve linear recurrence relations and thereby demonstrate the ability to provide and transmit solutions to sometimes complex problems; and",
      "explain the history and status of discrete mathematics as it relates to computer science and digital technologies"
    ),

    taught = Seq(
      AssessmentDescription(
        title = "Lectures",
        cbok = Seq(abs(0), unde(0), hw(0), sec(0))
      ),
      AssessmentDescription(
        title = "Tutorials",
        cbok = Seq(abs(0), hw(0), sec(0))
      )
    ),

    assessment = Seq(

      AssessmentDescription(
        title = "Short answer assignment",
        percentage = 8,
        lo = 1 to 5,
        cbok = Seq(abs(3), unde(2), hw(3), sec(2))
      ),
      AssessmentDescription(
        title = "Short answer assignment",
        percentage = 8,
        lo = 1 to 5,
        cbok = Seq(abs(3), unde(2), hw(3), sec(2))
      ),
      AssessmentDescription(
        title = "Short answer assignment",
        percentage = 8,
        lo = 1 to 5,
        cbok = Seq(abs(3), unde(2), hw(3), sec(2))
      ),
      AssessmentDescription(
        title = "Short answer assignment",
        percentage = 8,
        lo = 1 to 5,
        cbok = Seq(abs(3), unde(2), hw(3), sec(2))
      ),
      AssessmentDescription(
        title = "Short answer assignment",
        percentage = 8,
        lo = 1 to 5,
        cbok = Seq(abs(3), unde(2), hw(3), sec(2))
      ),
      AssessmentDescription(
        title = "Exam",
        description = Some("Supervised examination"),
        percentage = 60,
        lo = Seq(1, 2, 3, 4, 5),
        cbok = Seq(abs(3), hw(3), sec(2))
      )
    )
  )

}
