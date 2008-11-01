package chameleon.support.expression;

import java.util.List;

import org.rejuse.logic.ternary.Ternary;

import chameleon.core.MetamodelException;
import chameleon.core.declaration.Declaration;
import chameleon.core.declaration.DeclarationSelector;
import chameleon.core.expression.Invocation;
import chameleon.core.expression.InvocationTarget;
import chameleon.core.relation.WeakPartialOrder;
import chameleon.core.type.Type;
import chameleon.support.member.MoreSpecificTypesOrder;
import chameleon.support.member.simplename.method.NormalMethod;

/**
 * @author Marko van Dooren
 */

public abstract class ConstructorDelegation<E extends ConstructorDelegation>
    extends Invocation<E, NormalMethod> {

  public ConstructorDelegation(InvocationTarget target) {
    super(target);
  }

  protected class NamelessConstructorSelector extends
      DeclarationSelector<NormalMethod> {
    @Override
    public NormalMethod filter(Declaration declaration)
        throws MetamodelException {
      NormalMethod result = null;
      if (selectedClass().isInstance(declaration)) {
        NormalMethod decl = (NormalMethod) declaration;
        List<Type> actuals = getActualParameterTypes();
        List<Type> formals = decl.signature().getParameterTypes();
        if (new MoreSpecificTypesOrder().contains(actuals, formals)
            && (decl.is(language().CONSTRUCTOR) == Ternary.TRUE)) {
          result = decl;
        }
      }
      return result;
    }

    // @FIXME: generalize along with implementation in
    // SimpleNameMethodInvocation
    @Override
    public WeakPartialOrder<NormalMethod> order() {
      return new WeakPartialOrder<NormalMethod>() {
        @Override
        public boolean contains(NormalMethod first, NormalMethod second)
            throws MetamodelException {
          return new MoreSpecificTypesOrder().contains(first.signature()
              .getParameterTypes(), second.signature().getParameterTypes());
        }
      };
    }

    @Override
    public Class<NormalMethod> selectedClass() {
      return NormalMethod.class;
    }
  }
  
  @Override
  public DeclarationSelector<NormalMethod> selector() {
    return new NamelessConstructorSelector();
  }

}
