class Table(rows:Int, columns:Int){
  private val table = Array.fill[Cell](rows, columns)
    {
      new EmptyCell

    }
  def getCell(ix: Int, iy: Int): Option[Cell] =
  {
    if ( 0 <= ix < rows && 0 <= iy < columns)
      Some(table(ix)(iy))
    else
      None
  }

  def setCell(ix: Int, iy: Int, cell: Cell): Unit = {
    if(getCell(ix, iy).isDefined)
      table(ix)(iy)=cell
  }
}