package chameleon.support.property.accessibility;

import chameleon.core.MetamodelException;
import chameleon.core.element.Element;
import chameleon.core.scope.LexicalScope;
import chameleon.core.scope.Scope;
import chameleon.core.type.Type;
import chameleon.core.type.TypeDescendant;

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
  
  public boolean contains(Element element) throws MetamodelException {
  	return (element instanceof TypeDescendant) && (((TypeDescendant)element).getNearestType().subTypeOf(getType()));
  }

 /*@
   @ also public behavior
   @
   @ post \result == ((other instanceof HierarchyScope) && 
   @                  ((HierarchyScope)other).getType().assignableTo(getType())) ||
   @                 ((other instanceof LexicalScope) && 
   @		 	            ((LexicalScope)other).element().nearestAncestor(Type.class).assignableTo(getType()));
   @*/
  public boolean geRecursive(Scope other) throws MetamodelException {
    return (
    		    (other instanceof HierarchyScope) && 
            ((HierarchyScope)other).getType().assignableTo(getType())
           ) ||
           (
            (other instanceof LexicalScope) && 
    		 	  ((LexicalScope)other).element().nearestAncestor(Type.class).assignableTo(getType())
           )
             ;
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
