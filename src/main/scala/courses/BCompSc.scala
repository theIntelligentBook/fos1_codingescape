package courses

import info.tweaked.model.plan.Plan
import info.tweaked.model.unit.Term._
import units._

object BCompSc {

  val core = Plan("Core units in the BCompSc",
    description = Seq(
      "This just shows the core units in the BCompSc, demonstrating how we ensure CBOK is fulfilled even before the choice of major."
    ),
    selection = Seq(
      t1_2018 -> Seq(
        COSC101.it,
        COSC110.it,
        MTHS120.it
      ),
      t2_2018 -> Seq(
        AMTH140.it,
        COSC120.it,
        MTHS130.it,
        STAT100.it
      ),
      t1_2019 -> Seq(
        COSC210.it,
        COSC230.it
      ),
      t2_2019 -> Seq(
        COSC240.it,
        COSC220.it
      ),
      t1_2020 -> Seq(
        COSC310.it

      ),
      t2_2020 -> Seq(
        COSC320.it
      )
    )
  )

  val programming = Plan("Programming skills trail",
  description=Seq(
    """
      |Some units are placed later in the degree because we have a technically-focused degree, and therefore want to
      |ensure students' programming skills are honed before they enter the unit. This page shows only those early
      |programming units
      |""".stripMargin),
    selection = Seq(
      t1_2018 -> Seq(
        COSC101.it,
        COSC110.it
      ),
      t2_2018 -> Seq(
        COSC120.it
      ),
      t1_2019 -> Seq(
        COSC230.it,
        COSC250.it
      ),
      t2_2019 -> Seq(

      )
    )

  )

  val algs = Plan("Algorithms trail",
    description=Seq(
      """
        |
        |""".stripMargin),
    selection = Seq(
      t1_2018 -> Seq(
        COSC110.it
      ),
      t2_2018 -> Seq(
        AMTH140.it,
        COSC120.it
      ),
      t1_2019 -> Seq(
        COSC230.it
      ),
      t2_2020 -> Seq(
        COSC380.it,
        COSC350.it
      )
    )

  )

  val studioProj = Plan("Studio and projects trail",
    description=Seq(
      """
        |Professional skills, communication, and teamwork are scaffolded throughout the course using studio pedagogies
        |such as open-ended problems, with peer critique as students work. This leads up to the capstone experience
        |with a real customer
        |""".stripMargin),
    selection = Seq(
      t1_2018 -> Seq(
        COSC101.it
      ),
      t2_2019 -> Seq(
        COSC220.it

      ),
      t1_2020 -> Seq(
        COSC370.it
      ),
      t2_2020 -> Seq(
        COSC360.it,
        COSC320.it
      )
    )

  )

  val sd = Plan("BCompSc (Software Development), T1 start, plus MTHS110",
    description = Seq(
      "Shows the options in the BCompSc (Software Development), not including all available listed units."
    ),
    selection = Seq(
      t1_2018 -> Seq(
        COSC101.it,
        COSC110.it,
        MTHS110.it
      ),
      t2_2018 -> Seq(
        AMTH140.it,
        COSC120.it,
        MTHS120.it,
        STAT100.it
      ),
      t1_2019 -> Seq(
        COSC210.it,
        COSC230.it,
        COSC250.it
      ),
      t2_2019 -> Seq(
        COSC240.it,
        COSC220.it,
        COSC260.it
      ),
      t1_2020 -> Seq(
        COSC310.it,
        COSC340.it,
        COSC370.it
      ),
      t2_2020 -> Seq(
        COSC350.it,
        COSC330.it,
        COSC360.it,
        COSC320.it
      )
    )
  )

  val ds = Plan("BCompSc (Data Science), T1 start, plus MTHS110",
    description = Seq(
      "Shows the options in the BCompSc (Data Science), not including all available listed units."
    ),
    selection = Seq(
      t1_2018 -> Seq(
        COSC101.it,
        COSC110.it,
        MTHS110.it
      ),
      t2_2018 -> Seq(
        AMTH140.it,
        COSC120.it,
        MTHS120.it,
        STAT100.it
      ),
      t1_2019 -> Seq(
        COSC210.it,
        COSC230.it,
        STAT210.it,
        COSC250.it
      ),
      t2_2019 -> Seq(
        COSC240.it,
        COSC220.it
      ),
      t1_2020 -> Seq(
        COSC310.it,
        STAT330.it
      ),
      t2_2020 -> Seq(
        COSC350.it,
        COSC330.it,
        COSC380.it,
        COSC320.it
      )
    )
  )

  val t1dual = Plan("BCompSc (Software Development & Data Science), T1 start, plus MTHS110",
    description = Seq(
      "The dual major becomes almost fully prescribed (two electives to spare). In this plan, MTHS110 is also inserted"
    ),
    selection = Seq(
      t1_2018 -> Seq(
        COSC101.it,
        COSC110.it,
        MTHS110.it
      ),
      t2_2018 -> Seq(
        AMTH140.it,
        COSC120.it,
        MTHS120.it,
        STAT100.it
      ),
      t1_2019 -> Seq(
        COSC210.it,
        COSC230.it,
        STAT210.it,
        COSC250.it
      ),
      t2_2019 -> Seq(
        COSC240.it,
        COSC220.it,
        COSC260.it,
        COSC350.it
      ),
      t1_2020 -> Seq(
        COSC310.it,
        COSC340.it,
        COSC370.it,
        STAT330.it
      ),
      t2_2020 -> Seq(
        COSC350.it,
        COSC330.it,
        COSC380.it,
        COSC320.it
      )
    )
  )


  val plans = Seq(
    core,
    programming,
    studioProj,
    algs,
    sd,
    ds,
    t1dual
  )

}
