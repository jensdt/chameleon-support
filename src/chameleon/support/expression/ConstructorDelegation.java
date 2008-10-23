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
import chameleon.support.member.simplename.method.RegularMethod;

/**
 * @author Marko van Dooren
 */

public abstract class ConstructorDelegation<E extends ConstructorDelegation>
    extends Invocation<E, RegularMethod> {

  public ConstructorDelegation(InvocationTarget target) {
    super(target);
  }

  protected class NamelessConstructorSelector extends
      DeclarationSelector<RegularMethod> {
    @Override
    public RegularMethod filter(Declaration declaration)
        throws MetamodelException {
      RegularMethod result = null;
      if (selectedClass().isInstance(declaration)) {
        RegularMethod decl = (RegularMethod) declaration;
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
    public WeakPartialOrder<RegularMethod> order() {
      return new WeakPartialOrder<RegularMethod>() {
        @Override
        public boolean contains(RegularMethod first, RegularMethod second)
            throws MetamodelException {
          return new MoreSpecificTypesOrder().contains(first.signature()
              .getParameterTypes(), second.signature().getParameterTypes());
        }
      };
    }

    @Override
    public Class<RegularMethod> selectedClass() {
      return RegularMethod.class;
    }
  }
  
  @Override
  public DeclarationSelector<RegularMethod> selector() {
    return new NamelessConstructorSelector();
  }

}
