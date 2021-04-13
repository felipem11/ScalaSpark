  // VALUES are immutable
  val hello: String = "Hola!"

  // VARIABLES are mutalbe
  var helloThere: String = hello
  helloThere = "Oi!!"
  println(helloThere)

  val immutableHelloThere = hello + " There"
  println(immutableHelloThere)

  // Data types

  val numberOne: Int = 1
  val truth: Boolean = true
  val letterA: Char = 'A'
  val pi: Double = 3.14159265
  val piSinglePrecision: Float = 3.14159265f
  val bigNumber: Long = 1234567890
  val smallNumber: Byte = 127

  println("Here is a mess: " + numberOne + truth + letterA + pi + piSinglePrecision)

  println(f"Pi is about $piSinglePrecision%.3f")
  println(f"Zero padding ont the left: $numberOne%05d")

  println(s"I can use the s refix to use variables like $numberOne $truth $pi $bigNumber")

  println(s"The s prefix isn't limited to variables; Ican include any expression like ${1 + 2}")

  val theUltimateAnswer: String = "To life, the universe, and everything is 42."
  val pattern = """.* ([\d]+).*""".r
  val pattern(answerString) = theUltimateAnswer
  val answer = answerString.toInt
  println(answer)

  // Booleans
  val isGreater: Boolean = 1 > 2
  val isLesser: Boolean = 1 > 2
  val impossible = isGreater & isLesser
  val impossibleAnotherWay: Boolean = isGreater && isLesser
  val impossibleAnotherWay2: Boolean = false && false

  val picard:String = "Picard"
  val bestCaptain: String = "Picard"
  val isBest: Boolean = picard == bestCaptain

  //EXERCISE
  // write some code that takes the value of pi, doubles it, and then prints it within a string with
  // three decimal places of precision to the right
  println(f"Exercise: ${piSinglePrecision * 2}%.3f")




