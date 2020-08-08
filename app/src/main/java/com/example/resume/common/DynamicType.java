package com.example.resume.common;

public class DynamicType<T> {
    T value;
    DynamicTypeChangeListener<T> listener;

    public DynamicType(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
        if (listener != null)
            this.listener.onChanged(value);
    }

    public void setListener(DynamicTypeChangeListener<T> listener) {
        this.listener = listener;
        if (listener != null)
            listener.onChanged(this.value);
    }
}
