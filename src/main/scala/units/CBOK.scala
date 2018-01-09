package units

/**
  * Created by wbilling on 21/05/2017.
  */

case class CBOK()

object CBOK {

  case class At(i:I, level:Int)

  case class I(short:String, n:String) {
    def apply(i:Int) = At(this, i)
  }

  val abs = I("abs", "Abstraction")
  val des = I("des", "Design")

  val eth = I("eth", "Ethics")
  val prof = I("prof", "Professional expectations")
  val team = I("team", "Teamwork")
  val comm = I("comm", "Interpersonal communication")
  val soci = I("soci", "Societal issues")
  val unde = I("unde", "Understanding the ICT profession")

  val hw = I("hw/sw", "Hardware and software")
  val data = I("data", "Data and information")
  val net = I("net", "Networking")

  val prog = I("prog", "Programming")
  val hf = I("hf", "Human factors")
  val sys = I("sys", "Systems development")
  val acq = I("acq", "Systems acquisition")

  val gov = I("gov", "IT governance & organisational")
  val proj = I("pm", "IT project management")
  val serv = I("serv", "IT service management")
  val sec = I("sec", "Security management")

  case class Category(n:String, is:Seq[I])

  val probSolving = Category("Problem Solving", Seq(abs, des))
  val profKnowledge = Category("Professional Knowlege", Seq(eth, prof, team, comm, soci, unde))
  val techResources = Category("Technology resources", Seq(hw, data, net))
  val techBuilding = Category("Technology building", Seq(prog, hf, sys, acq))
  val ictManagement = Category("ICT Management", Seq(gov, proj, serv, sec))

  val categories = Seq(probSolving, profKnowledge, techResources, techBuilding, ictManagement)


}
