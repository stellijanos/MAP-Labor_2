package org.online_shop.models;

// Singleton design pattern
public class Session {

    private static Session instance;
    private String sessionId;

    private Session() {
    }

    public static Session getInstance() {
        if (instance == null) {
            synchronized (Session.class) {
                if (instance == null) {
                    instance = new Session();
                }
            }
        }
        return instance;
    }

    public String getId() {
        return sessionId;
    }

    public void setId(String id) {
        if (sessionId == null)
            sessionId = id;
    }

    public boolean destroy() {
        this.sessionId = null;
        instance = null;
        return true;
    }

}
