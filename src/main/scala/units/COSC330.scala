package units

import info.tweaked.model.plan.Prerequisite.MinCP
import info.tweaked.model.unit._
import units.CBOK._


object COSC330 extends HasUnit {

  val it = new TeachingUnit(
    code = "COSC330",

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
      "explain the fundamental paradigms of modern concurrent and parallel programming using appropriate terminology;",
      "analyse a given application or problem and implement a parallelisation strategy for a given parallel or distributed platform;",
      "develop software for parallel or distribute architectures; and \nevaluate parallel processing algorithms for speed, efficiency, and select appropriate approaches for different situations and requirements1.\texplain the fundamental paradigms of modern concurrent and parallel programming using appropriate terminology; \n2.\tanalyse a given application or problem and implement a parallelisation strategy for a given parallel or distributed platform; \n3.\tdevelop software for parallel or distribute architectures; and",
      "evaluate parallel processing algorithms for speed, efficiency, and select appropriate approaches for different situations and requirements"
    ),

    prerequisite = MinCP(72), //COSC220 and

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
        percentage = 10,
        lo = Seq(2, 3, 4),
        cbok = Seq(abs(6), des(6), hw(5), data(5), net(5), prog(5), acq(3))
      ),
      AssessmentDescription(
        title = "Programming in C programming language",
        percentage = 10,
        lo = Seq(2, 3, 4),
        cbok = Seq(abs(6), des(6), hw(5), data(5), net(5), prog(5), acq(3))
      ),
      AssessmentDescription(
        title = "Programming in C programming language",
        percentage = 10,
        lo = Seq(2, 3, 4),
        cbok = Seq(abs(6), des(6), hw(5), data(5), net(5), prog(5), acq(3))
      ),
      AssessmentDescription(
        title = "Programming in C programming language",
        percentage = 10,
        lo = Seq(2, 3, 4),
        cbok = Seq(abs(6), des(6), hw(5), data(5), net(5), prog(5), acq(3))
      ),
      AssessmentDescription(
        title = "Exam",
        description = Some("Supervised examination"),
        percentage = 60,
        lo = Seq(1, 2, 3, 4),
        cbok = Seq(abs(5), des(5), hw(5), data(3), net(3), prog(3))
      )
    )
  )

}
