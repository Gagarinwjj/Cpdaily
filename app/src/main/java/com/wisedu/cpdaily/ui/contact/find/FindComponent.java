package com.wisedu.cpdaily.ui.contact.find;

import com.wisedu.cpdaily.di.components.NetComponent;
import com.wisedu.cpdaily.di.scope.ScopeFragment;

import dagger.Component;

/**
 * 注入器
 */
@ScopeFragment
@Component(dependencies = NetComponent.class, modules = FindModule.class)
interface FindComponent {
    void inject(FindFragment fragment);
}
