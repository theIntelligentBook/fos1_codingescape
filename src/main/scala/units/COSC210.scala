package units

import info.tweaked.model.unit._
import units.CBOK._

object COSC210 extends HasUnit {

  val it = new TeachingUnit(
    code = "COSC210",

    name = Some("Database Management Systems"),

    description = Seq(
      """
        | The widespread application of digital information systems in almost every area of society produces vast
        | amounts of data. Appropriate management of this data is essential for it to be effectively used for decision
        | making processes and value-adding activities.
      """.stripMargin,
      """
        | This unit provides an introduction to the concepts of database design from the conceptual level through to the
        | physical level within database management systems.  Students will study relational databases and learn how to
        | model, create and efficiently query them using Structured Query Language (SQL). Further to this, relational
        | algebra will be introduced to provide a more generalised definition of data and queries within relational
        | database systems.  The concepts of functional dependency, normalisation and relational decomposition will be
        | introduced to promote sound database design to avoid redundancy. The unit will introduce approaches for
        | connecting external applications to databases and provide awareness of security issues involved with data
        | access. Further database topics including, query processing, transaction management and database security will
        | be covered to provide students with a solid understanding of the techniques that underpin modern database
        | management systems. Students will also gain experience in the design of object oriented and spatial databases.
      """.stripMargin
    ),

    outcomes = Seq(
      "describe the essential components of database management systems (DBMS) and apply data modelling approaches to design databases for real-world scenarios;",
        "explain the principles of the relational model, implement databases using a relational DBMS and understand the architectures that allow application software to use data stored within a database schema;",
        "work with data stored in a relational DBMS using SQL to create database tables, extract, present and modify data;",
        "implement integrity constraints and functions (using SQL) that reflect business logic;",
        "understand and apply the principles of normalisation and functional dependencies to assess and optimise a relational schema; and",
        "explain the concepts of transaction management, query processing, object-oriented modelling and spatial databases together with the principles and issues around information privacy as they relate to the storage and dissemination of data and apply views and permissions to implement security constraints"
    ),

    taught = Seq(
      AssessmentDescription(
        title = "Lectures",
        percentage = 0,
        lo = Seq.empty,
        cbok = Seq(abs(0), des(0), soci(0), data(0), net(0), sec(0))
      ),
      AssessmentDescription(
        title = "Lab exercises",
        percentage = 0,
        lo = Seq.empty,
        cbok = Seq(prog(0))
      )
    ),

    assessment = Seq(
      AssessmentDescription(
        title = "5 online quizzes worth 2% each",
        percentage = 10,
        lo = Seq(1,2,4,5,6),
        cbok = Seq(abs(2), des(2), data(4), sys(2))
      ),
      AssessmentDescription(
        title = "Programming assignment",
        percentage = 10,
        lo = 1 to 3,
        cbok = Seq(abs(2), des(2), data(4), sys(2))
      ),
      AssessmentDescription(
        title = "Theory assignment",
        percentage = 10,
        lo = Seq(1,2,4,5,6),
        cbok = Seq(abs(2), des(2), data(4), sys(2))
      ),
      AssessmentDescription(
        title = "Theory assignment 2",
        percentage = 10,
        lo = Seq(1, 2, 4, 5, 6),
        cbok = Seq(abs(2), des(2), data(4), sys(2))
      ),
      AssessmentDescription(
        title = "Practical assignment",
        percentage = 10,
        lo = Seq(1, 2, 4, 5, 6),
        cbok = Seq(abs(3), des(3), data(4), net(2), prog(3))
      ),
      AssessmentDescription(
        title = "Practical assignment 2",
        percentage = 10,
        lo = Seq(2, 3, 4, 5),
        cbok = Seq(abs(3), des(3), data(4), net(2), prog(3))
      ),
      AssessmentDescription(
        title = "Exam",
        percentage = 50,
        lo = Seq(1, 2, 4, 5, 6),
        cbok = Seq(abs(3), des(3), soci(2), data(4), prog(3), sys(2), acq(2), sec(2))
      )
    )
  )

}
