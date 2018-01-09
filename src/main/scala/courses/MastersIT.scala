package courses

import info.tweaked.model.plan.Plan
import info.tweaked.model.unit.TeachingUnit
import info.tweaked.model.unit.Term.{t1_2018, t1_2019, t1_2020, t2_2018, t2_2019, t2_2020, yl1_2019}
import units._

object MastersIT {

  val mitRuleA = Plan("Master of Information Technology, rule A, core",
    description = Seq(
      "Core units in the MIT rule A."
    ),
    selection = Seq(
      t1_2018 -> Seq(
        COSC101.it,
        COSC110.it,
        COSC210.it,
        COSC510.it
      ),
      t2_2018 -> Seq(
        COSC120.it,
        COSC220.it,
        COSC260.it
      ),
      t1_2019 -> Seq(
        COSC570.it
      ),
      t2_2019 -> Seq(
        COSC560.it
      ),
      yl1_2019 -> Seq(
        COSC592.it
      )
    )
  )

  val mitRuleAel = Plan("Master of Information Technology, rule A, plus listed",
    description = Seq(
      "Core plus ICT listed units in the MIT rule A."
    ),
    selection = Seq(
      t1_2018 -> Seq(
        COSC101.it,
        COSC110.it,
        COSC210.it,
        COSC510.it
      ),
      t2_2018 -> Seq(
        COSC120.it,
        COSC220.it,
        COSC260.it
      ),
      t1_2019 -> Seq(
        COSC570.it,
        COSC540.it
      ),
      t2_2019 -> Seq(
        COSC560.it,
        COSC530.it,
        COSC550.it
      ),
      yl1_2019 -> Seq(
        COSC592.it
      )
    )
  )

  val mitB = Plan("Master of Information Technology (Business)",
    description = Seq(
      "Master of Technology (Business), T1 start"
    ),
    selection = Seq(
      t1_2018 -> Seq(
        COSC101.it,
        COSC110.it,
        COSC210.it,
        TeachingUnit.x("MM431", Some("Marketing Management"))
      ),
      t2_2018 -> Seq(
        COSC120.it,
        COSC220.it,
        COSC260.it,
        TeachingUnit.x("LSSU2551", Some("Introduction to Business Law"))
      ),
      t1_2019 -> Seq(
        COSC510.it,
        COSC540.it,
        TeachingUnit.x("AFM524", Some("Entrepreneurship")),
        TeachingUnit.x("MM521", Some("Processes of Management"))
      ),
      t2_2019 -> Seq(
        COSC372.it,
        COSC560.it,
        TeachingUnit.x("AFM406", Some("Introductory Accounting")),
        COSC591.it
      )
    )
  )

  val mDatSci = Plan("Master of Data Science, rule A, core",
    description = Seq(
      "Core units in the MDatSci."
    ),
    selection = Seq(
      t1_2018 -> Seq(
        COSC110.it,
        COSC210.it,
        COSC510.it,
        TeachingUnit.x("SCI410", Some("Introduction to Programming in the Sciences"))
      ),
      t2_2018 -> Seq(
        STAT100.it,
        MTHS120.it,
        COSC472.it
      ),
      t1_2019 -> Seq(
        STAT210.it,
        STAT430.it,
        TeachingUnit.x("SCI501", Some("Research Methods"))
      ),
      t2_2019 -> Seq(
        COSC550.it,
        COSC580.it,
        COSC591.it
      )
    )
  )



  val plans = Seq(
    mitRuleA,
    mitRuleAel,
    mitB,
    mDatSci
  )

}
