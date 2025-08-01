///////////////////////////////////////////////////////////////////////////////////////////////
// checkstyle: Checks Java source code and other text files for adherence to a set of rules.
// Copyright (C) 2001-2025 the original author or authors.
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
///////////////////////////////////////////////////////////////////////////////////////////////

package com.puppycrawl.tools.checkstyle.checks.naming;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.utils.ScopeUtil;

/**
 * <div>
 * Checks that {@code static}, non-{@code final} variable names conform to a specified pattern.
 * </div>
 * <ul>
 * <li>
 * Property {@code applyToPackage} - Control if check should apply to package-private
 *   members.
 * Type is {@code boolean}.
 * Default value is {@code true}.
 * Since version 5.0
 * </li>
 * <li>
 * Property {@code applyToPrivate} - Control if check should apply to private members.
 * Type is {@code boolean}.
 * Default value is {@code true}.
 * Since version 5.0
 * </li>
 * <li>
 * Property {@code applyToProtected} - Control if check should apply to protected
 *   members.
 * Type is {@code boolean}.
 * Default value is {@code true}.
 * Since version 5.0
 * </li>
 * <li>
 * Property {@code applyToPublic} - Control if check should apply to public members.
 * Type is {@code boolean}.
 * Default value is {@code true}.
 * Since version 5.0
 * </li>
 * <li>
 * Property {@code format} - Sets the pattern to match valid identifiers.
 * Type is {@code java.util.regex.Pattern}.
 * Default value is {@code "^[a-z][a-zA-Z0-9]*$"}.
 * </li>
 * </ul>
 *
 * <p>
 * Parent is {@code com.puppycrawl.tools.checkstyle.TreeWalker}
 * </p>
 *
 * <p>
 * Violation Message Keys:
 * </p>
 * <ul>
 * <li>
 * {@code name.invalidPattern}
 * </li>
 * </ul>
 *
 * @since 3.0
 */
public class StaticVariableNameCheck
    extends AbstractAccessControlNameCheck {

    /** Creates a new {@code StaticVariableNameCheck} instance. */
    public StaticVariableNameCheck() {
        super("^[a-z][a-zA-Z0-9]*$");
    }

    @Override
    public int[] getDefaultTokens() {
        return getRequiredTokens();
    }

    @Override
    public int[] getAcceptableTokens() {
        return getRequiredTokens();
    }

    @Override
    public int[] getRequiredTokens() {
        return new int[] {TokenTypes.VARIABLE_DEF};
    }

    @Override
    protected final boolean mustCheckName(DetailAST ast) {
        final DetailAST modifiersAST =
            ast.findFirstToken(TokenTypes.MODIFIERS);
        final boolean isStatic = modifiersAST.findFirstToken(TokenTypes.LITERAL_STATIC) != null;
        final boolean isFinal = modifiersAST.findFirstToken(TokenTypes.FINAL) != null;

        return isStatic
                && !isFinal
                && shouldCheckInScope(modifiersAST)
                && !ScopeUtil.isInInterfaceOrAnnotationBlock(ast);
    }

}
