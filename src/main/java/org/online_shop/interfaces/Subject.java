package org.online_shop.interfaces;

public interface Subject {
    void add(UserObserver observer);
    void remove(UserObserver observer);
    void notifyObservers();
}
