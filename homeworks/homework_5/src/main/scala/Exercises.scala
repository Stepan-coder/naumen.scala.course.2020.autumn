object Exercises
{
  trait Animal
  {
    def name: String
  }

  case class Cat(override val name: String) extends Animal

  case class Dog(override val name: String) extends Animal


  case class Shelter[+T <: Animal](animals: List[T])
  {
    def +[AddedType >: T <: Animal](animal: AddedType):
    Shelter[AddedType] = Shelter[AddedType](animal :: animals)

    def ++[AddedType >: T <: Animal](shelter: Shelter[AddedType]):
    Shelter[AddedType] = Shelter[AddedType](shelter.animals ++ animals)

    def getNames: List[String] = animals.map(_.name)

    def feed[RightAnimal >: T <: Animal](food: Food[RightAnimal]):
    List[String] = animals.map(food.feed(_))
  }

  trait Food [T <: Animal]
  {
    def feed(animal: T) : String = s"${animal.name} eats ${this.toString.toLowerCase}"
  }

  case object Meat extends Food[Animal]

  case object Milk extends Food[Cat]

  case object Bread extends Food[Dog]