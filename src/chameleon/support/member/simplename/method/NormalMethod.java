/*
 * Copyright 2000-2004 the Jnome development team.
 *
 * @author Marko van Dooren
 * @author Nele Smeets
 * @author Kristof Mertens
 * @author Jan Dockx
 *
 * This file is part of Jnome.
 *
 * Jnome is free software; you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * Jnome is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Jnome; if not, write to the Free Software Foundation, Inc., 59 Temple Place,
 * Suite 330, Boston, MA 02111-1307 USA
 */
package chameleon.support.member.simplename.method;


import chameleon.core.method.Method;
import chameleon.core.method.RegularMethod;
import chameleon.core.type.TypeReference;
import chameleon.support.member.simplename.SimpleNameMethodSignature;


/**
 * @author Marko van Dooren
 */
public class NormalMethod extends RegularMethod<NormalMethod,SimpleNameMethodSignature>  {

  public NormalMethod(SimpleNameMethodSignature sig, TypeReference returnType) {
    super(sig, returnType);
  }


  public boolean sameKind(Method other) {
  	return(other instanceof NormalMethod);
  }  

  protected NormalMethod cloneThis() {
    return new NormalMethod((SimpleNameMethodSignature) signature().clone(), (TypeReference)getReturnTypeReference().clone());
  }
  
	
//	   private Reference<RegularMethod,ConstructorDelegation> _invokingConstructor = new Reference<RegularMethod,ConstructorDelegation>(this);
//
//	   //STUDENTSCREWUP
//	   
//	   /**
//	    * In C# a constructor can be specified to be executed before
//	    * the statements in this constructor are executed, this can
//	    * be done by the keyword "this" or "base" followed by the 
//	    * necessary parameterlist
//	    */
//	   public void setInvokingConstructor(ConstructorDelegation literal){
//	   	_invokingConstructor.connectTo(literal.getParentLink());
//	   }
//	   
//	   public ConstructorDelegation getInvokingConstructor(){
//	   	return (ConstructorDelegation)_invokingConstructor.getOtherEnd();
//	   } 

}


