package org.sonar.samples.java.checks;

import com.google.common.collect.ImmutableList;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.Arguments;
import org.sonar.plugins.java.api.tree.ExpressionTree;
import org.sonar.plugins.java.api.tree.IdentifierTree;
import org.sonar.plugins.java.api.tree.LiteralTree;
import org.sonar.plugins.java.api.tree.MemberSelectExpressionTree;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.Tree;

import java.util.List;
import java.util.Objects;

@Rule(
        key = "AssertEqualsParameterOrderCheck",
        name = "First parameter of assertEquals is the expected parameter",
        description = "The first parameter of assertEquals is the expected value and the second is the actual value " +
                "calculated by the test case. In this invocation only the second parameter is a literal, which means " +
                "that the parameters have been swapped.",
        priority = Priority.INFO,
        tags = {"bad-practice", "confusing"})
public class AssertEqualsParameterOrderCheck extends IssuableSubscriptionVisitor {
    private static final String METHOD_NAME = "assertEquals";

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return ImmutableList.of(Tree.Kind.METHOD_INVOCATION);
    }

    @Override
    public void visitNode(Tree tree) {
        MethodInvocationTree mtree = (MethodInvocationTree) tree;
        String name = extractMethodName(mtree);
        if (Objects.equals(METHOD_NAME, name)) {
            Arguments arguments = mtree.arguments();
            if (arguments.size() == 2 && !isLiteral(arguments.get(0)) && isLiteral(arguments.get(1))) {
                reportIssue(tree, "The first argument of assertEquals is the expected value.");
            }
        }

    }

    private boolean isLiteral(ExpressionTree tree) {
        return tree instanceof LiteralTree;
    }

    private String extractMethodName(MethodInvocationTree mtree) {
        ExpressionTree methodSelect = mtree.methodSelect();
        if (methodSelect instanceof IdentifierTree) {
            return ((IdentifierTree) methodSelect).name();
        }
        else if (methodSelect instanceof MemberSelectExpressionTree) {
            return ((MemberSelectExpressionTree) methodSelect).identifier().name();
        }
        return null;
    }
}
