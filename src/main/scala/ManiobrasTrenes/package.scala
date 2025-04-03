package object ManiobrasTrenes {
  type Vagon = Any
  type Tren = List[Vagon]
  type Estado = (Tren, Tren, Tren)

  trait Movimiento

  case class Uno(n:Int) extends Movimiento
  case class Dos(n:Int) extends Movimiento

  type Maniobra = List[Movimiento]

  def aplicarMovimiento(e:Estado, m:Movimiento): Estado = {
    val (principal, uno, dos) = e
     m match {
       case Uno(0) => e
       case Dos(0) => e
       case Uno(n) =>
         if (n > 0)
           val cantidadAMover = math.min(n, principal.length)
           val (quedaEnPrincipal, paraUno) = principal.splitAt(principal.length - cantidadAMover)
           (quedaEnPrincipal, paraUno ++ uno, dos)
         else
           val cantidadAMover = math.min(-n, uno.length)
           val (paraPrincipal, quedaEnUno) = uno.splitAt(cantidadAMover)
           (principal ++ paraPrincipal, quedaEnUno, dos)

       case Dos(n) =>
         if (n>0)
           val cantidadAMover = math.min(n, principal.length)
           val (quedaEnPrincipal, paraDos) = principal.splitAt(principal.length - cantidadAMover)
           (quedaEnPrincipal, uno, paraDos ++ dos)
         else
           val cantidadAMover = math.min(-n, dos.length)
           val (paraPrincipal, quedaEnDos) = dos.splitAt(cantidadAMover)
           (principal ++ paraPrincipal, uno, quedaEnDos)


     }
  }

  def aplicarMovimientos(e:Estado, movs:Maniobra): List[Estado] = {
    movs match
      case Nil => List(e)
      case x::xs => e :: aplicarMovimientos(aplicarMovimiento(e,x),xs)
  }

  def definirManiobra(t1:Tren, t2:Tren):Maniobra = {

    def machMovimientos(e:Estado, t2:Tren, movs:Maniobra, i:Int): Maniobra= {
      t2 match
        case x::Nil => movs.reverse
        case x::xs =>
          val mov = Uno(e._1.length - e._1.indexOf(x))
          val e1 = aplicarMovimiento(e,mov)
          val mov2 = Dos(e1._1.length - i)
          val e2 = aplicarMovimiento(e1,mov2)
          val mov3 = Uno(-e2._2.length)
          val e3 = aplicarMovimiento(e2,mov3)
          val mov4 = Dos(-e3._3.length)
          val e4 = aplicarMovimiento(e3,mov4)
          machMovimientos(e4,xs,mov4::mov3::mov2::mov::movs,i+1)
    }
    machMovimientos((t1,Nil,Nil),t2,Nil,0)
  }




}
