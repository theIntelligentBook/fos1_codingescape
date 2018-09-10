package units

import info.tweaked.model.plan.Prerequisite.MinCP
import info.tweaked.model.unit._
import units.CBOK._


object COSC372 extends HasUnit {

  val it = new TeachingUnit(
    code = "COSC372",

    name = Some("Management Information Systems"),

    description = Seq(
      """
        |Information systems is a key component of every business. It’s about the use of information technology to improve business processes and involves, among many things, organizing data, analyzing data and managing information to facilitate strategic decision making.
        |""".stripMargin,
      """
        |In this unit, critical skills of bridging technology and business needs will be learnt. Topics including IT infrastructure, web services, mobile technology, business intelligence, database management, business process, enterprise systems, e-business and collaboration will be covered. Emerging cutting edge topics will be reviewed as well such as cloud computing, business and data analytics, and how they have been used to solve real world business problems as applied to customer segmentation, customer relationship management, among others.
      """.stripMargin

    ),

    outcomes = Seq(
      "demonstrate an in depth understanding of principles, practices and the role of management information systems (MIS) in organisations, how IT is adopted and used in organizations",
      "possess a sound knowledge of storing, managing, and processing data to yield relevant information in a MIS, with focus on both business process and information technology",
      "exhibit an understanding of the role that business intelligence and business analytics play in decision support system for making informed business decisions"
    ),

    prerequisite = MinCP(72), //COSC220 and

    taught = Seq(
      AssessmentDescription(
        title = "Lectures",
        cbok = Seq(abs(0), des(0), eth(0), comm(0), data(0), soci(0), unde(0), acq(0), hf(0), gov(0), serv(0))
      ),
      AssessmentDescription(
        title = "Tutorials",
        cbok = Seq(abs(0), des(0), comm(0), data(0), acq(0), gov(0), serv(0))
      )
    ),

    assessment = Seq(
      AssessmentDescription(
        title = "Essay",
        percentage = 25,
        lo = Seq(1),
        cbok = Seq(abs(3), des(5), eth(2), comm(3), soci(2), hf(4), gov(4), serv(4))
      ),
      AssessmentDescription(
        title = "Essay",
        percentage = 25,
        lo = Seq(2,3),
        cbok = Seq(abs(3), des(5), comm(3), data(4), acq(3), gov(4), serv(4))
      ),
      AssessmentDescription(
        title = "Exam",
        description = Some("Supervised examination"),
        percentage = 50,
        lo = Seq(1, 2, 3),
        cbok = Seq(abs(3), des(4), eth(2), comm(3), data(4), acq(3), gov(4), serv(4))
      )
    )
  )

}
