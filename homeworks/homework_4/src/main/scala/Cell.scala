trait Cell

class EmptyCell extends Cell
{
  override def toString: String = "empty"
}

class NumberCell(Value: Int) extends Cell
{
  override def toString: String = Value.toString
}

class StringCell(Text: String) extends Cell
{
  override def toString: String = Text
}


class ReferenceCell(ix: Int, iy: Int, table: Table) extends Cell
{
  override def toString: String =
  {
    if (table.isOutOfRange(ix, iy))
      "outOfRange"
    else
    var thisValue = table.getCell(ix, iy)
    nextCell match
    {
      case Some(cell: Cell) => cell.toString
      case Some(cell: ReferenceCell) =>
        if (cell.table.getCell(ix, iy).get == this)
          "cyclic"
        else
          cell.table.getCell(ix, iy).get.toString
      case None => "outOfRange"
    }
  }
}