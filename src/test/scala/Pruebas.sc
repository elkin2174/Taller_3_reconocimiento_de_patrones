import ManiobrasTrenes._
// Pruebas del documento
val e1 = (List('a', 'b', 'c', 'd'), Nil, Nil)
val e2 = aplicarMovimiento(e1, Uno(2))
val e3 = aplicarMovimiento(e2, Dos(3))
val e4 = aplicarMovimiento(e3, Dos(-1))
val e5 = aplicarMovimiento(e4, Uno(-2))

val e6 = aplicarMovimiento(e1,Uno(0))
val e7 = aplicarMovimiento(e1,Uno(-2))

aplicarMovimientos(e1, List(Uno(2), Dos(3), Dos(-1), Uno(-2), Dos(-1)))
val e = (List('a', 'b'), List('c'), List('d'))
aplicarMovimientos(e, List(Uno(1), Dos(1), Uno(-2)))
definirManiobra(List('a','b','c','d'),List('d','b','c','a'))


//Pruebas Definidas
//Creacion de estados Test
val eTest = (List(1,2,3,4,5),Nil,Nil)
val eTest2 = (List("carro","volqueta","camion","autobus"),Nil,Nil)
val eTest3 = (List(List(1,2),List(2,3,4),List(6,8,9),List('a','b')),Nil,Nil)
val eTest4 = (List(1,7),Nil,List(2,8,5))
val eTest5 = (List(eTest,eTest3,eTest4),Nil,List(eTest2))

//Aplicar movimiento sobre los estados
aplicarMovimiento(eTest,Dos(3))
aplicarMovimiento(eTest2,Uno(2))
aplicarMovimiento(eTest3, Dos(-5))
aplicarMovimiento(eTest4, Dos(-2))
aplicarMovimiento(eTest5, Uno(2))

//Movimientos para pruebas sobre aplicarMovimientos
val movs1 = List(Uno(3), Dos(2), Uno(-6), Uno (-2), Dos(1))
val movs2 = List(Dos(2), Dos(1), Uno(-1), Dos(-1))
val movs3 = List(Uno(0),Dos(0))
val movs4 = List()
val movs5 = List(Uno(1), Uno(1), Dos(1),Dos(-1))

//Pruebas sobre aplicaar movimientos
aplicarMovimientos(eTest,movs1)
aplicarMovimientos(eTest2, movs2)
aplicarMovimientos(eTest3,movs3)
aplicarMovimientos(eTest4, movs4)
aplicarMovimientos(eTest5,movs5)
aplicarMovimientos(eTest, movs5)

//Pruebas para generar la maniobra
val maniobra = definirManiobra(List(1,2,3,4,5),List(5,3,1,2,4))
aplicarMovimientos((List(1,2,3,4,5),Nil,Nil),maniobra)
val maniobra2 = definirManiobra(List("carro","volqueta","camion","autobus"),List("volqueta","autobus","carro","camion"))
aplicarMovimientos(eTest2,maniobra2)
val maniobra3 = definirManiobra(List(List(1,2),List(2,3,4),List(6,8,9),List('a','b')),List(List(2,3,4),List(1,2),List('a','b'),List(6,8,9)))
aplicarMovimientos(eTest3,maniobra3)
val maniobra4 = definirManiobra(List("volqueta","autobus","carro","camion"),List("carro","volqueta","camion","autobus"))
aplicarMovimientos((List("volqueta","autobus","carro","camion"),Nil,Nil),maniobra4)
val maniobra5 = definirManiobra(List(List(1,2),List(2,3,4),List(6,8,9),List('a','b')), List(List('a','b'),List(6,8,9),List(2,3,4), List(1,2)))
aplicarMovimientos((List(List(1,2),List(2,3,4),List(6,8,9),List('a','b')),Nil,Nil),maniobra5)

