package chameleon.support.property.accessibility;

import chameleon.core.MetamodelException;
import chameleon.core.accessibility.AccessibilityDomain;

/**
 * @author Marko van Dooren
 */
public class EmptyDomain extends AccessibilityDomain {

 /*@
   @ public behavior
   @
   @ post \result == other instanceof EmptyDomain;
   @*/
  public boolean geRecursive(AccessibilityDomain other) throws MetamodelException {
    return (other instanceof EmptyDomain);
  }
  
  public boolean equals(Object o) {
    return (o instanceof EmptyDomain);
  }

}
