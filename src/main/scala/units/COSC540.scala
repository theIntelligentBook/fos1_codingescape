package units

import info.tweaked.model.plan.Prerequisite.minCP
import info.tweaked.model.unit._
import units.CBOK._


object COSC540 extends HasUnit {

  val it = new TeachingUnit(
    code = "COSC540",

    name = Some("Computer Networks and Information Security"),

    description = Seq(
      """
        |Computer networking technology is a key component of modern information systems. Distributed applications on campus server, mobile and desktop systems must be implemented in a secure and efficient manner to ensure effective use of the available resources. This unit studies the foundations of computer networks and network security, providing detailed knowledge of a range protocols, techniques and standards used within networked systems throughout the world. The unit introduces the network security model and covers all technology layers outlined by the Open System Interconnection (OSI) model. Topics in network security provide students with a solid understanding of the principles and methods for secure access and transfer of information across insecure channels, including symmetric encryption, public key cryptography and its modes of operation. Throughout this unit, students will develop distributed applications, implementing existing application layer protocols and building new protocols to solve complex problems.
        |""".stripMargin,
      """
        |Topics covered include: application protocols, principles of reliable data transfer, internet protocol, routing algorithms, wireless and cellular networks, public key cryptography, security in wireless networks, message authentication codes and network programming.
      """.stripMargin
    ),

    outcomes = Seq(
      "analyse the functions of computer systems and devices in computer networks and understand the security threats relevant to the transfer of information across a complex communication system;",
        "describe and design a range of transport, network, data-link and physical layer protocols/algorithms and assess them in terms of their features, purpose and performance;",
        "develop advanced networked applications that implement both established protocols (based upon existing documented standards) and derived protocols developed for specific purposes;",
        "analyse and interpret the underlying algorithms in cryptographic techniques used for secure communication and develop distributed applications that allow for the secure transfer of information;",
        "understand and make decisions surrounding the complex administrative and social aspects of specific communication technologies, security threats and wireless/mobile computing platforms; and",
        "design secure and efficient distributed systems using a range of network technologies protocols for a range of purposes. "
    ),

    prerequisite = minCP(72), //COSC220 and

    taught = Seq(
      AssessmentDescription(
        title = "Lectures",
        cbok = Seq(eth(0), soci(0), unde(0), hw(0), data(0), net(0), acq(0), serv(0), sec(0))
      ),
      AssessmentDescription(
        title = "Tutorials",
        cbok = Seq(abs(0), des(0), soci(0), unde(0), hw(0), data(0), net(0), sys(0))
      )
    ),

    assessment = Seq(
      AssessmentDescription(
        title = "Theory assignment",
        percentage = 5,
        lo = Seq(1, 2, 5, 6),
        cbok = Seq(hw(3), data(3), net(3))
      ),
      AssessmentDescription(
        title = "Programming assignment",
        percentage = 10,
        lo = Seq(2, 3, 6),
        cbok = Seq(abs(3), des(3), hw(3), data(3), net(3), prog(3))
      ),
      AssessmentDescription(
        title = "Theory assignment",
        percentage = 10,
        lo = Seq(1, 2, 4, 5, 6),
        cbok = Seq(hw(3), data(3), net(3))
      ),
      AssessmentDescription(
        title = "Programming assignment",
        percentage = 10,
        lo = Seq(2, 3, 4, 6),
        cbok = Seq(abs(3), des(3), hw(3), data(3), net(3), prog(3), sec(4))
      ),
      AssessmentDescription(
        title = "Theory assignment",
        percentage = 10,
        lo = Seq(1, 2, 4, 5, 6),
        cbok = Seq(soci(3), unde(4), serv(2), sec(2))
      ),
      AssessmentDescription(
        title = "Programming project",
        percentage = 15,
        lo = Seq(2, 3, 4, 6),
        cbok = Seq(abs(3), des(3), hw(3), data(4), net(3), prog(3), sec(4))
      ),
      AssessmentDescription(
        title = "Exam",
        description = Some("Supervised examination"),
        percentage = 50,
        lo = Seq(1, 2, 4, 5),
        cbok = Seq(eth(2), soci(2), hw(4), data(3), net(5), prog(2), sec(4))
      )
    )
  )

}
