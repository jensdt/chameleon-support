package chameleon.support.type;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.rejuse.association.Reference;

import chameleon.core.context.LookupException;
import chameleon.core.member.Member;
import chameleon.core.statement.Block;
import chameleon.core.statement.CheckedExceptionList;
import chameleon.core.statement.ExceptionSource;
import chameleon.core.statement.StatementContainer;
import chameleon.core.type.Type;
import chameleon.core.type.TypeElementImpl;
import chameleon.util.Util;

/**
 * @author Marko van Dooren
 */
public class StaticInitializer extends TypeElementImpl<StaticInitializer,Type> implements StatementContainer<StaticInitializer,Type>, ExceptionSource<StaticInitializer,Type> {

  public StaticInitializer(Block block) {
      setBlock(block);
  }

  public Type getNearestType() {
    return getType();
  }

  public Type getType() {
  	return parent();
  }

  /*********
   * BLOCK *
   *********/

  public Reference getBlockLink() {
    return _blockLink;
  }

  public Block getBlock() {
    return (Block)_blockLink.getOtherEnd();
  }


  private Reference<StaticInitializer,Block> _blockLink = new Reference<StaticInitializer,Block>(this);

  public void setBlock(Block block) {
    _blockLink.connectTo(block.parentLink());
  }

  /**
   * @return
   */
  public StaticInitializer clone() {
    return new StaticInitializer(getBlock().clone());
  }

 /*@
   @ also public behavior
   @
   @ post getBlock() != null ==> \result.contains(getBlock());
   @ post \result.size() == 1;
   @*/
  public List children() {
    return Util.createNonNullList(getBlock());
  }

 /*@
   @ also public behavior
   @
   @ post \result == getBlock().getCEL();
   @*/
  public CheckedExceptionList getCEL() throws LookupException {
    return getBlock().getCEL();
  }

 /*@
   @ also public behavior
   @
   @ post \result == getBlock().getAbsCEL();
   @*/
  public CheckedExceptionList getAbsCEL() throws LookupException {
    return getBlock().getAbsCEL();
  }

 /*@
   @ also public behavior
   @
   @ // A block does not introduce any methods.
   @ post \result.isEmpty();
   @*/
  public List getIntroducedMethods() {
	  return new ArrayList();
  }

 /*@
   @ also public behavior
   @
   @ // A block does not introduce any variables.
   @ post \result.isEmpty();
   @*/
  public Set getIntroducedVariables() {
	  return new HashSet();
  }

  public List getIntroducedIntroducingMembers(){
	return new ArrayList();
}

// /*@
//   @ post \result instanceof EmptyDomain;
//   @*/
//  public AccessibilityDomain getAccessibilityDomain() throws LookupException {
//	  return new EmptyDomain();
//  }

  /**
   * A static initializer does not add members to a type.
   */
  public Set<Member> getIntroducedMembers() {
    return new HashSet<Member>();
  }





}
