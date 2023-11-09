package com.souvik.fresh_votes.domain;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
public class CommentId implements Serializable {

    @Serial
    private static final long serialVersionUID = 3050975180642266155L;

    @ManyToOne
    private User user;
    @ManyToOne
    private Feature feature;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }
}
