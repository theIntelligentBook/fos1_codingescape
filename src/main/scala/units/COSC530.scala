package units

import info.tweaked.model.plan.Prerequisite.minCP
import info.tweaked.model.unit._
import units.CBOK._


object COSC530 extends HasUnit {

  val it = new TeachingUnit(
    code = "COSC530",

    name = Some("Parallel and Distributed Computing"),

    description = Seq(
      """
        |Parallel and distributed computing platforms are vital to high performance computing systems in the commercial and scientific domains as they provide the capabilities for large-scale computation.  As a result, the ability to develop parallelisation strategies for complex processing tasks is a key skill for computer scientists, engineers and mathematical modellers.
        |""".stripMargin,
      """
        |This unit provides in-depth coverage of the algorithms and principles for developing software for UNIX-based parallel and distributed computer systems.  The unit provides an overview of UNIX processes, the development of multi-threaded software and the use of system calls to manage synchronization and inter-process communication.  Also covered are the principles of shared resources, mutual exclusion and students will be required to describe implementation strategies for developing efficient multi-threaded and multi-process software for high-performance computation.  Throughout this unit, students will develop software for both distributed and single-machine platforms through a range of lab exercises and programming assignments.
      """.stripMargin
    ),

    outcomes = Seq(
      "explain, in depth, the fundamental paradigms of modern concurrent and parallel programming, and apply these paradigms to solve complex problems;",
      "design efficient parallelisation strategies for complex problems an implement them on campus a target parallel or distributed computing platform;",
      "explain the factors that limit the increase in performance possible through parallelisation and develop optimal configurations for complex problems;",
      "develop software for parallel and distributed architectures; and ",
      "evaluate parallel processing algorithms for speed, efficiency and select appropriate approaches for different situations and requirements. "
    ),

    prerequisite = minCP(72), //COSC220 and

    taught = Seq(
      AssessmentDescription(
        title = "Lectures",
        cbok = Seq(abs(0), des(0), hw(0), data(0), net(0), prog(0), acq(0))
      ),
      AssessmentDescription(
        title = "Tutorials",
        cbok = Seq(abs(0), des(0), hw(0), data(0), net(0), prog(0), acq(0))
      )
    ),

    assessment = Seq(
      AssessmentDescription(
        title = "Programming in C programming language",
        percentage = 7,
        lo = Seq(1, 2, 3, 4, 5),
        cbok = Seq(abs(6), des(6), hw(5), data(5), net(5), prog(5), acq(3))
      ),
      AssessmentDescription(
        title = "Programming in C programming language",
        percentage = 7,
        lo = Seq(1, 2, 3, 4, 5),
        cbok = Seq(abs(6), des(6), hw(5), data(5), net(5), prog(5), acq(3))
      ),
      AssessmentDescription(
        title = "Programming in C programming language",
        percentage = 7,
        lo = Seq(1, 2, 3, 4, 5),
        cbok = Seq(abs(6), des(6), hw(5), data(5), net(5), prog(5), acq(3))
      ),
      AssessmentDescription(
        title = "Programming in C programming language",
        percentage = 7,
        lo = Seq(1, 2, 3, 4, 5),
        cbok = Seq(abs(6), des(6), hw(5), data(5), net(5), prog(5), acq(3))
      ),
      AssessmentDescription(
        title = "Programming in C programming language",
        percentage = 12,
        lo = Seq(1, 2, 3, 4, 5),
        cbok = Seq(abs(6), des(6), hw(5), data(5), net(5), prog(6))
      ),
      AssessmentDescription(
        title = "Exam",
        description = Some("Supervised examination"),
        percentage = 60,
        lo = Seq(1, 2, 3, 4),
        cbok = Seq(abs(5), des(5), hw(5), data(3), net(3))
      )
    )
  )

}
