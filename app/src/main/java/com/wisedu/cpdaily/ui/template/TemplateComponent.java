package com.wisedu.cpdaily.ui.template;


import com.wisedu.cpdaily.di.components.NetComponent;
import com.wisedu.cpdaily.di.scope.ScopeFragment;

import dagger.Component;

/**
 * 注入器
 */
@ScopeFragment
@Component(dependencies = NetComponent.class, modules = TemplateModule.class)
interface TemplateComponent {
    void inject(TemplateFragment fragment);
}
