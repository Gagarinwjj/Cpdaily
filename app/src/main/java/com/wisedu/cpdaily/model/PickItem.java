package com.wisedu.cpdaily.model;

import com.bigkoo.pickerview.model.IPickerViewData;

/**
 * 滚轮选项
 */
public class PickItem implements IPickerViewData {
    String id;
    String name;

    public PickItem(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPickerViewText() {
        return name;
    }
}