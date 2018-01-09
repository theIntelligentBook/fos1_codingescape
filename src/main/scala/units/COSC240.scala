package units

import info.tweaked.model.unit._
import units.CBOK._

object COSC240 extends HasUnit {

  val it = new TeachingUnit(
    code = "COSC240",

    name = Some("Operating Systems"),

    description = Seq(
      """
        |An operating system is a fundamental component of most modern computing environments. Operating systems have developed in parallel with computer hardware, advancing to ensure the hardware can be used as effectively as possible. This unit introduces the core components of operating systems, including: processes; memory management; file systems; and scheduling. This is extended through an examination of multiprocessor and distributed systems, and illustrated through real-world case studies. Related topics, including concurrency control and security, are also introduced.
      """.stripMargin
    ),

    outcomes = Seq(
      "articulate knowledge of the fundamental principles of computer architecture using appropriate terminology;",
      "describe the historical development of operating systems and its impact on campus availability of technology;",
      "broadly explain theoretical and technical concepts relating to operating systems, including processes, virtual memory, files, security and distributed computing;",
      "tanalyse and evaluate a range of algorithms for process scheduling, concurrency, memory management and file storage; and",
      "plan and implement solutions to sometimes complex programming problems involving C programs and UNIX system calls"
    ),

    taught = Seq(
      AssessmentDescription(
        title = "Lectures",
        percentage = 0,
        lo = Seq.empty,
        cbok = Seq(abs(0), des(0), eth(0), soci(0), unde(0), hw(0), data(0), net(0), prog(0), hf(0), sys(0))
      ),
      AssessmentDescription(
        title = "Lab exercises",
        percentage = 0,
        lo = Seq.empty,
        cbok = Seq(unde(0), hw(0), data(0), net(0), prog(0))
      )
    ),

    assessment = Seq(
      AssessmentDescription(
        title = "Short answer and programming task",
        percentage = 20,
        lo = 1 to 5,
        cbok = Seq(abs(3), des(3), hw(4), data(2), net(3), prog(3), sec(2))
      ),
      AssessmentDescription(
        title = "Short answer and programming task",
        percentage = 20,
        lo = 1 to 5,
        cbok = Seq(abs(3), des(3), hw(4), data(2), net(3), prog(3), sec(2))
      ),
      AssessmentDescription(
        title = "5 online quizzes",
        percentage = 10,
        lo = Seq(1,2,3,4, 5),
        cbok = Seq(eth(1), soci(1), unde(1), hw(1), data(1), prog(1), sys(1), sec(1))
      ),
      AssessmentDescription(
        title = "Exam",
        percentage = 60,
        lo = Seq(1, 2, 3, 4, 5),
        cbok = Seq(abs(3), des(3), soci(2), hw(4), data(4), net(3), prog(3), sec(2))
      )
    )
  )

}
