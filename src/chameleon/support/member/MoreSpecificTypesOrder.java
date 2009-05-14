/**
 * 
 */
package chameleon.support.member;

import java.util.Iterator;
import java.util.List;

import chameleon.core.MetamodelException;
import chameleon.core.method.MethodHeader;
import chameleon.core.relation.WeakPartialOrder;
import chameleon.core.type.Type;
import chameleon.support.member.simplename.operator.infix.InfixOperator;

public class MoreSpecificTypesOrder extends WeakPartialOrder<List<Type>> {
  @Override
  public boolean contains(List<Type> first, List<Type> second)
      throws MetamodelException {
//    MethodSignature firstSig = first.signature();
//    MethodSignature secondSig = second.signature();
    boolean result = first.size() == second.size();
    Iterator<Type> firstIter = first.iterator();
    Iterator<Type> secondIter = second.iterator();
    while(result && firstIter.hasNext()) {
      Type firstType = firstIter.next();
      Type secondType = secondIter.next();
      result = result && firstType.assignableTo(secondType);
    }
    return result;
  }
}