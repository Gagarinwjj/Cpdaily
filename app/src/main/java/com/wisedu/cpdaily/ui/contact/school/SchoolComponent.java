package com.wisedu.cpdaily.ui.contact.school;


import com.wisedu.cpdaily.di.components.NetComponent;
import com.wisedu.cpdaily.di.modules.ApiModule;
import com.wisedu.cpdaily.di.scope.ScopeFragment;

import dagger.Component;

/**
 * 注入器
 */
@ScopeFragment
@Component(dependencies = NetComponent.class, modules = {SchoolModule.class, ApiModule.class})
interface SchoolComponent {
    void inject(SchoolFragment fragment);
}
