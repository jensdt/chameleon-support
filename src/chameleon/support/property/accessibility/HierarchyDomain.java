package chameleon.support.property.accessibility;

import chameleon.core.MetamodelException;
import chameleon.core.accessibility.AccessibilityDomain;
import chameleon.core.type.Type;

/**
 * @author marko
 */
public class HierarchyDomain extends AccessibilityDomain {
  
 /*@
   @ public behavior
   @
   @ pre type != null;
   @
   @ post getType() == type;
   @*/
  public HierarchyDomain(Type type) {
    _type = type;
  }

 /*@
   @ also public behavior
   @
   @ post \result == (other instanceof Hierarchy) && 
   @                 ((Hierarchy)other).getType().subTypeOf(getType());
   @*/
  public boolean geRecursive(AccessibilityDomain other) throws MetamodelException {
    return (other instanceof HierarchyDomain) && 
             (
               ((HierarchyDomain)other).getType().subTypeOf(getType()) ||
               ((HierarchyDomain)other).getType().equals(getType())
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

	/**
	 * 
	 * @uml.property name="_type"
	 * @uml.associationEnd 
	 * @uml.property name="_type" multiplicity="(1 1)"
	 */
	private Type _type;

}
