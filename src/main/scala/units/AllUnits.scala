package units

import info.tweaked.model.unit.TeachingUnit

trait HasUnit {
  def it:TeachingUnit
}


object AllUnits {

  val maths = Seq(MTHS110, MTHS120, MTHS130).map(_.it)

  val stat = Seq(STAT100, STAT210, STAT330).map(_.it)

  val cosc = Seq(
    COSC101, COSC110, COSC120, AMTH140,
    COSC210, COSC220, COSC230, COSC240, COSC250, COSC260,
    COSC310, COSC320, COSC330, COSC340, COSC350, COSC360, COSC370, COSC372, COSC380
  ).map(_.it)

  val postgrad = Seq(
    STAT430, COSC472, COSC510, COSC530, COSC540, COSC550, COSC560, COSC570, COSC580, COSC591, COSC592
  ).map(_.it)

  val units = maths ++ stat ++ cosc ++ postgrad

  val unitMap = units.map({ x => x.code -> x }).toMap


}
