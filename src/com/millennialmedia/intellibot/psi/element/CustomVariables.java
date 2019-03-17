package com.millennialmedia.intellibot.psi.element;

import com.millennialmedia.intellibot.psi.dto.VariableDto;
import com.millennialmedia.intellibot.psi.util.ReservedVariableScope;

import java.util.Collection;


public class CustomVariables {
    private Collection<DefinedVariable> declaredVariables;

    public synchronized Collection<DefinedVariable> getCustomVariables() {
        return declaredVariables;
    }

    public synchronized void updateCustomVariables(String customVariableNames) {
        declaredVariables.clear();

        for (String name : customVariableNames.split("\n")) {
            declaredVariables.add(new VariableDto(null, name, ReservedVariableScope.Global));
        }
    }
}
