package net.sf.eclipsecs.sample.Saghan;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class InterfaceLimitCheck extends AbstractCheck {

	private int max = 2;

	@Override
	public int[] getDefaultTokens() {
		// TODO Auto-generated method stub
		return new int[] { TokenTypes.CLASS_DEF};
	}

	public void setMax(int limit) {
		max = limit;
	}

	@Override
	public void visitToken(DetailAST ast) {
		DetailAST objBlock = ast.findFirstToken(TokenTypes.IMPLEMENTS_CLAUSE);
		// count the number of direct children of the OBJBLOCK
		// that are METHOD_DEFS
		int interfaceCount = objBlock.getChildCount(TokenTypes.IDENT);
		System.out.println("interfaceCount: " + interfaceCount);
		// report error if limit is reached
		if (interfaceCount > max) {
			log(ast.getLineNo(), "interfaceLimit", max);
		}
	}

}
