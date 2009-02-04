package chameleon.support.expression;

import chameleon.core.MetamodelException;
import chameleon.core.context.LexicalContext;
import chameleon.core.element.Element;
import chameleon.core.expression.InvocationTargetContainer;
import chameleon.core.type.Type;
import chameleon.core.type.TypeDescendant;
import chameleon.core.type.TypeDescendantImpl;

/**
 * @author Tim Laeremans
 * @author Marko van Dooren
 */
public abstract class ArrayIndex<E extends ArrayIndex> extends TypeDescendantImpl<E,InvocationTargetContainer> implements TypeDescendant<E,InvocationTargetContainer> {
//RESEARCH: with type members, the choice of extension is open. With generic params, we are forced to decide on extensibility
//          when we develop this class, possibly preventing unanticipated reuse.
	
	
    public ArrayIndex() {
        super();
    }

    public Type getNearestType() {
        return parent().getNearestType();
    }

    public abstract E clone();


}
