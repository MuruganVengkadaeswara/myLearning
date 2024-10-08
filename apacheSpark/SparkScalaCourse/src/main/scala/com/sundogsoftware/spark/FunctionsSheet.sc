// functions in scala

// format

// def <fun name>(param:type) : return type = {}


def printit(x : String) : String = {
  return x + " Hello "
}

printit("hi")

def cubeIt(x : Int) = {
  x * x * x
}


def transformInt(x : Int , f:Int =>Int) = {
  f(x)
}


transformInt(5 , cubeIt)