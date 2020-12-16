
trait Monad[F[_]] {

    def pure[A](a: A): F[A]

    def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B]

    def map2[A, B, C](fa: F[A], fb: F[B])(f: (A, B) => C): F[C] = flatMap(fa)(a => flatMap(fb)(b => pure(f(a, b))))

    def sequence[A](fas: List[F[A]]): F[List[A]] = {
        var list = List.empty[A]
        fas.map(fa => flatMap(fa)(a => {
                list = list :+ a
                pure(list)})).last
    }

    def compose[A, B, C](f: A => F[B])(g: B => F[C]): A => F[C] = {  A => flatMap(f(A))(b => g(b)) }
}

trait Functor[F[_]] {
    def map[A, B](a: F[A])(f: A => B): F[B]
}

object Functor {
    def functorFromMonad[F[_]](M: Monad[F]): Functor[F] = new Functor[F] {
        def map[A, B](a: F[A])(f: A => B): F[B] =  M.flatMap(a)(b => M.pure(f(b)))
    }
}
