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

       case Dos(n) => {
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
  }

  def aplicarMovimientos(e:Estado, movs:Maniobra): List[Estado] = {
    movs match
      case Nil => List(e)
      case x::xs => e :: aplicarMovimientos(aplicarMovimiento(e,x),xs)
  }

  def definirManiobra(t1:Tren, t2:Tren):Maniobra = {
    List()
  }

}
