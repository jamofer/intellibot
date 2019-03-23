package com.millennialmedia.intellibot.psi.util;

import com.jetbrains.python.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FunctionParser {

    private static final String UNDERSCORE = "_";
    private static final String SELF = "self";

    private static PyDecorator getKeywordDecorator(@NotNull PyFunction function) {
        PyDecoratorList decorators = function.getDecoratorList();

        if (decorators == null) {
            return null;
        }

        for (PyDecorator decorator : decorators.getDecorators()) {
            String decorator_name = decorator.getName();
            if (decorator_name != null && decorator_name.matches("^(robot.)?(api.)?(deco.)?keyword")) {
                return decorator;
            }
        }
        return null;
    }

    private static boolean isPrivate(@NotNull String keyword) {
        // these keeps out intended private functions
        return keyword.startsWith(UNDERSCORE) || keyword.startsWith("ROBOT_LIBRARY_");
    }

    private static String functionToKeyword(@Nullable String function) {
        if (function == null || isPrivate(function)) {
            return null;
        } else {
            return function;
        }
    }

    public static String keywordName(PyFunction function) {
        String keyword = functionToKeyword(function.getName());
        if (keyword != null) {
            // Get info from @keyword
            PyDecorator keyword_decorator = getKeywordDecorator(function);
            if (keyword_decorator != null) {
                if (keyword_decorator.hasArgumentList()) {
                    // Get case 'name =' argument
                    PyExpression kwa = keyword_decorator.getKeywordArgument("name");
                    if (kwa != null) {
                        keyword = kwa.getText().replaceAll("^[\"|\']|[\"|\']$", "");
                    } else {
                        // Otherwise, check if first argument is unnamed
                        PyExpression[] kda = keyword_decorator.getArguments();

                        // Argument exists and is unnamed
                        if (kda.length > 0 && kda[0].getName() == null) {
                            keyword = kda[0].getText().replaceAll("^[\"|\']|[\"|\']$", "");
                        }
                    }
                }
            }
        }

        return keyword;
    }

    public static boolean keywordHasArguments(PyFunction keyword_function) {
        PyParameter[] parameters = keyword_function.getParameterList().getParameters();

        if (parameters == null || parameters.length == 0) {
            return false;
        }

        for (PyParameter parameter : parameters) {
            String name = parameter.getName();
            if (name != null && !SELF.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}
