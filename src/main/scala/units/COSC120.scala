package units

import info.tweaked.model.unit._
import units.CBOK._

object COSC120 extends HasUnit {

  val it = new TeachingUnit(
    code = "COSC120",

    name = Some("Object Oriented Programming"),

    description = Seq(
      """
        | Object-oriented programming is one of several popular programming paradigms in use today. It is based around the concept of 'classes' and their instances, known also as 'objects' and their interactions. Objects contain both data, called attributes, and code, known as methods.
        | In this unit, techniques for computer program development using Java, an object-oriented programming language, are introduced. Topics covered include classes, objects, encapsulation, inheritance, polymorphism, single and multidimensional arrays, file input/output, decision structures and loops. Programming language features for parameter passing, exception handling, and simple user interface development will also be covered.
      """.stripMargin

    ),

    outcomes = Seq(
      "explain object oriented programming concepts including classes, objects, encapsulation, inheritance, and polymorphism;",
      "apply object oriented design principles to algorithm design and analysis; and",
      "develop computer programs using Java, which is an object oriented programming language"
    ),

    taught = Seq(
      AssessmentDescription(
        title = "Lectures",
        percentage = 0,
        lo = Seq.empty,
        cbok = Seq(abs(0), des(0), unde(0), hw(0), data(0), prog(0))
      ),
      AssessmentDescription(
        title = "Lab exercises",
        percentage = 0,
        lo = Seq.empty,
        cbok = Seq(abs(0), des(0), prog(0))
      )
    ),

    assessment = Seq(
      AssessmentDescription(
        title = "Programming assignment",
        percentage = 10,
        lo = 1 to 3,
        cbok = Seq(abs(3), des(3), data(2), prog(3))
      ),
      AssessmentDescription(
        title = "Programming assignment",
        percentage = 10,
        lo = 1 to 3,
        cbok = Seq(abs(3), des(3), data(2), prog(3))
      ),
      AssessmentDescription(
        title = "Programming assignment",
        percentage = 10,
        lo = 1 to 3,
        cbok = Seq(abs(3), des(3), data(2), prog(3))
      ),
      AssessmentDescription(
        title = "Programming assignment",
        percentage = 10,
        lo = 1 to 3,
        cbok = Seq(abs(3), des(3), data(2), prog(3))
      ),
      AssessmentDescription(
        title = "Exam",
        percentage = 60,
        lo = 1 to 3,
        cbok = Seq(abs(3), des(3), unde(2), hw(2), data(2), prog(3))
      )
    )
  )

}
