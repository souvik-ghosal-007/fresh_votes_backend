package com.souvik.fresh_votes.domain;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Embeddable
public class Vote {
    @EmbeddedId
    private VoteId pk;

    private Boolean upvote;

    public VoteId getPk() {
        return pk;
    }

    public void setPk(VoteId pk) {
        this.pk = pk;
    }

    public Boolean getUpvote() {
        return upvote;
    }

    public void setUpvote(Boolean upvote) {
        this.upvote = upvote;
    }
}
