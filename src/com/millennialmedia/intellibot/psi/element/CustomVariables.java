package com.millennialmedia.intellibot.psi.element;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.millennialmedia.intellibot.psi.dto.VariableDto;
import com.millennialmedia.intellibot.psi.util.ReservedVariableScope;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;


public class CustomVariables {
    private Collection<DefinedVariable> declaredVariables = new ArrayList<>();

    public synchronized Collection<DefinedVariable> getCustomVariables() {
        return declaredVariables;
    }

    public synchronized void updateCustomVariables(String customVariableNames, @NotNull Project project) {
        declaredVariables.clear();

        if (customVariableNames.isEmpty()) {
            return;
        }

        for (String name : customVariableNames.split("\n")) {
            PsiElement newVariable = ReservedVariableScope.Global.getVariable(project);
            if (newVariable != null) {
                declaredVariables.add(new VariableDto(newVariable, name, ReservedVariableScope.Global));
            }
        }
    }
}
