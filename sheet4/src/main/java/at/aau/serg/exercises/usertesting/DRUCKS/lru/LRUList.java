package at.aau.serg.exercises.usertesting.NACHNAME.lru;

import java.util.ArrayList;
import java.util.function.Predicate;

public class LRUList<E> extends ArrayList<E> {

  /**
   * @param searchCondition criterion applied to each element until the first has been found.
   *                        The found element is then moved to the front of the list.
   * @return the first element that matches the search condition
   * @throws IllegalStateException if no element matches the condition
   */
  public E findFirst(Predicate<E> searchCondition) {
    for (var e : this) {
      if (searchCondition.test(e)) {
        return e;
      }
    }

    // TODO implement

    throw new IllegalStateException("Element not found");
  }
}
