package com.eeServiceCenter.desktop.persistence;

public interface CrudPresistence<T> {
    public boolean save(T entity);
    public boolean delete(String text);
}
