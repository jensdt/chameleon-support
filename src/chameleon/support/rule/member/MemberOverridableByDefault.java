package chameleon.support.rule.member;

import org.rejuse.property.PropertySet;

import chameleon.core.element.Element;
import chameleon.core.language.Language;
import chameleon.core.member.Member;
import chameleon.core.property.ChameleonProperty;
import chameleon.core.property.PropertyRule;
import chameleon.oo.language.ObjectOrientedLanguage;

public class MemberOverridableByDefault extends PropertyRule<Member> {

	public MemberOverridableByDefault() {
		super(Member.class);
	}

	public PropertySet<Element,ChameleonProperty> suggestedProperties(Member element) {
		return createSet(language(ObjectOrientedLanguage.class).OVERRIDABLE);
	}
	
}
