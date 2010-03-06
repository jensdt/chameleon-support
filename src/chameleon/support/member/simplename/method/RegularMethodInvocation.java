package chameleon.support.member.simplename.method;


import chameleon.core.expression.InvocationTarget;
import chameleon.core.lookup.DeclarationSelector;
import chameleon.support.member.simplename.SimpleNameMethodInvocation;


/**
 * @author Marko van Dooren
 */
public class RegularMethodInvocation<E extends RegularMethodInvocation> extends SimpleNameMethodInvocation<E,NormalMethod> {

  public RegularMethodInvocation(String name, InvocationTarget target) {
    super(target, name);
  }

  /********
   * MISC *
   ********/
  
  protected E cloneInvocation(InvocationTarget target) {
    return (E) new RegularMethodInvocation(name(), target);
  }

  @Override
  public DeclarationSelector<NormalMethod> selector() {
    return new SimpleNameMethodSelector() {
        @Override
        public Class<NormalMethod> selectedClass() {
          return NormalMethod.class;
        }

      };
  }

}
