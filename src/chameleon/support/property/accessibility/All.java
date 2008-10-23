package chameleon.support.property.accessibility;

import chameleon.core.accessibility.AccessibilityDomain;

/**
 * @author Marko van Dooren
 */
public class All extends AccessibilityDomain {

 /*@
   @ also public behavior
   @
   @ post \result == true;
   @*/
  public boolean geRecursive(AccessibilityDomain other) {
    return true;
  }

  public boolean equals(Object o) {
    return (o instanceof All);
  }

}
