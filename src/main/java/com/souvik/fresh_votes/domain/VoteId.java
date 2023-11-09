package com.souvik.fresh_votes.domain;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serial;
import java.io.Serializable;

@Embeddable
public class VoteId implements Serializable {

    @Serial
    private static final long serialVersionUID = 5136582008335022073L;

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