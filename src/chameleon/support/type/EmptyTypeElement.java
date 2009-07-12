package chameleon.support.type;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import chameleon.core.element.Element;
import chameleon.core.member.Member;
import chameleon.core.type.Type;
import chameleon.core.type.TypeElementImpl;

public class EmptyTypeElement extends TypeElementImpl<EmptyTypeElement,Type> {

	@Override
	public EmptyTypeElement clone() {
		return new EmptyTypeElement();
	}

	public List<Member> getIntroducedMembers() {
		return new ArrayList<Member>();
	}

	public Type getNearestType() {
		return parent().getNearestType();
	}

	public List<Element> children() {
		return new ArrayList<Element>();
	}

}
