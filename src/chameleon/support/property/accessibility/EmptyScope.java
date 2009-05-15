package chameleon.support.property.accessibility;

import chameleon.core.MetamodelException;
import chameleon.core.scope.Scope;

/**
 * @author Marko van Dooren
 */
public class EmptyScope extends Scope {

 /*@
   @ public behavior
   @
   @ post \result == other instanceof EmptyScope;
   @*/
  public boolean geRecursive(Scope other) throws MetamodelException {
    return (other instanceof EmptyScope);
  }
  
  public boolean equals(Object o) {
    return (o instanceof EmptyScope);
  }

}
