// Functions

// format def <function name>(parameter name: type...) : return type = { }

def squareIt(x: Int) : Int = {
  x * x
}

def cubeIt(x : Int) : Int = {x * x * x}

println(squareIt(2))

println(cubeIt(3))

def transformInt(x: Int, f: Int => Int): Int = {
  f(x)
}

val result = transformInt(2, cubeIt)
println(result)

// Lambda
transformInt(3, x => x * x * x)
transformInt(3, y => y * y * y)

transformInt(10, x => x / 2)

transformInt(2, x => {val y = x * 2; y * y})

// EXERCISE
// Strings have a built-in .toUpperCase method. For example, "foo".toUpperCase gives you back FOO.
// Write a function that converts a string to upper-case, and use that function of a few test strings.
// Then, do the same thing using a function literal instead of a separate, named function.

def toUpper(x: String): String = {
  x.toUpperCase()
}

toUpper("foobar")

def upper(x: String, f: String => String): String = {
  f(x)
}

upper("barfoo", x => x.toUpperCase())
upper("felipe_martins", toUpper)