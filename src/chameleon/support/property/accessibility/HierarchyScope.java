package chameleon.support.property.accessibility;

import chameleon.core.MetamodelException;
import chameleon.core.scope.Scope;
import chameleon.core.type.Type;

/**
 * @author Marko van Dooren
 */
public class HierarchyScope extends Scope {
  
 /*@
   @ public behavior
   @
   @ pre type != null;
   @
   @ post getType() == type;
   @*/
  public HierarchyScope(Type type) {
    _type = type;
  }

 /*@
   @ also public behavior
   @
   @ post \result == (other instanceof HierarchyScope) && 
   @                 ((HierarchyScope)other).getType().subTypeOf(getType());
   @*/
  public boolean geRecursive(Scope other) throws MetamodelException {
    return (other instanceof HierarchyScope) && 
             (
               ((HierarchyScope)other).getType().subTypeOf(getType()) ||
               ((HierarchyScope)other).getType().equals(getType())
             );
  }
  
 /*@
   @ public behavior
   @
   @ post \result != null;
   @*/
  public Type getType() throws MetamodelException {
    return _type;
  }

	private Type _type;

}
