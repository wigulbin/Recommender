package com.example.recommender.spotify.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExplicitContent {
    @JsonProperty("filter_enabled")
    private boolean filterEnabled;
    @JsonProperty("filter_locked")
    private boolean filter_locked;

    public ExplicitContent(){}
    public ExplicitContent(boolean filterEnabled, boolean filter_locked) {
        this.filterEnabled = filterEnabled;
        this.filter_locked = filter_locked;
    }

    public boolean isFilterEnabled() {
        return filterEnabled;
    }

    public void setFilterEnabled(boolean filterEnabled) {
        this.filterEnabled = filterEnabled;
    }

    public boolean isFilter_locked() {
        return filter_locked;
    }

    public void setFilter_locked(boolean filter_locked) {
        this.filter_locked = filter_locked;
    }
}
