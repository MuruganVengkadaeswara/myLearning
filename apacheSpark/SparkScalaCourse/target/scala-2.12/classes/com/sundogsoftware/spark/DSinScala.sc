// Tuples

val stuff = ("Hi" , "Hello" , "Hola")
print(stuff._2)



val ship = "Hi" -> "1997"
println(ship._2)

val stuff1 = ("Hi" , 123,13.3)

val list1 = List("Hi" , "Hello" , "GM")

println(list1.tail)

for(item <- list1){
  println(item)
}


val map2 = Map("key"->"value" , "key1" -> "value2" )

util.Try(map2("key4")) getOrElse "DO"




val numList = List(1,2,3,4,5,6,7,8,9,10)

numList.filter((x : Int)=>{
  x % 3 == 0
})







