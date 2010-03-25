package chameleon.support.type;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import chameleon.core.element.Element;
import chameleon.core.member.Member;
import chameleon.core.type.Type;
import chameleon.core.type.TypeElementImpl;
import chameleon.core.validation.Valid;
import chameleon.core.validation.VerificationResult;

public class EmptyTypeElement extends TypeElementImpl<EmptyTypeElement,Type> {

	@Override
	public EmptyTypeElement clone() {
		return new EmptyTypeElement();
	}

	public List<Member> getIntroducedMembers() {
		return new ArrayList<Member>();
	}

	public List<Element> children() {
		return new ArrayList<Element>();
	}

	@Override
	public VerificationResult verifySelf() {
		return Valid.create();
	}

}
