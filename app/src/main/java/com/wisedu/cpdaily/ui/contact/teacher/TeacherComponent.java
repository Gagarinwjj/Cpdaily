package com.wisedu.cpdaily.ui.contact.teacher;


import com.wisedu.cpdaily.di.components.NetComponent;
import com.wisedu.cpdaily.di.scope.ScopeFragment;

import dagger.Component;

/**
 * 注入器
 */
@ScopeFragment
@Component(dependencies = NetComponent.class, modules = TeacherModule.class)
interface TeacherComponent {
    void inject(TeacherFragment fragment);
}
