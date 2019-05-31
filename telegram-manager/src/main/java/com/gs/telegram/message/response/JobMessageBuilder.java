package com.gs.telegram.message.response;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.gs.common.hazelcast.model.JobCachedModel;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class JobMessageBuilder {

    StringBuilder stringBuilder = new StringBuilder();
    private JobCachedModel model;

    public JobMessageBuilder(JobCachedModel model) {

        this.model = model;
    }

    private JobMessageBuilder withQuery() {
        stringBuilder
                .append("*Query*: ")
                .append("**")
                .append(model.getQuery())
                .append("**")
                .append("\n");
        return this;
    }

    private JobMessageBuilder withTitle() {
        stringBuilder
                .append("\n")
                .append("*Title*: ")
                .append("[")
                .append(model.getTitle())
                .append("]")
                .append("(")
                .append(model.getJobUrl())
                .append(")")
                .append("\n");
        return this;
    }

    private JobMessageBuilder withDescription() {
        stringBuilder
                .append("\n")
                .append("*Description*: ")
                .append("\n")
                .append(model.getDescription())
                .append("\n");
        return this;
    }

    private JobMessageBuilder withLink() {
        stringBuilder
                .append("\n")
                .append("*Link*: ")
                .append(model.getJobUrl())
                .append("\n");
        return this;
    }

    private JobMessageBuilder withDuration() {
        if (model.getDuration() == null) {
            return this;
        }
        stringBuilder
                .append("\n")
                .append("*Duration*: ")
                .append(model.getDuration())
                .append("\n");
        return this;
    }

    private JobMessageBuilder withSkills() {
        if (model.getSkills().isEmpty()) {
            return this;
        }
        stringBuilder
                .append("\n")
                .append("*Skills*: ")
                .append("\n");
        model.getSkills().forEach(skillModel -> stringBuilder
                .append(skillModel.getPrettyName())
                .append(", "));
        stringBuilder
                .append("\n");
        return this;
    }

    private JobMessageBuilder withPosted() {

        String date = model.getCreatedOn().substring(0, 19);
        Instant instant = LocalDateTime.parse(date).toInstant(ZoneOffset.UTC);
        stringBuilder
                .append("*Posted*: ")
                .append("**")
                .append(TimeAgo.using(instant.toEpochMilli()))
                .append("**");

        stringBuilder
                .append("\n");
        return this;
    }

    private JobMessageBuilder withAmount() {

        if (model.getAmount() == null) {
            return this;
        }
        stringBuilder
                .append("*Amount*: ")
                .append("**")
                .append(model.getAmount().getAmount())
                .append(" ")
                .append(model.getAmount().getCurrencyCode())
                .append("**")
                .append("\n");

        return this;
    }

    private JobMessageBuilder withType() {

        if (model.getType() == null) {
            return this;
        }
        stringBuilder
                .append("\n")
                .append("*Type*: ")
                .append(model.getType() == 1 ? PriceType.FIX_PRICE.getName() : PriceType.HOURLY.getName())
                .append("\n");

        return this;
    }

    public String build() {
        withQuery()
                .withTitle()
                .withAmount()
                .withType()
                .withPosted()
                .withSkills();
        //  .withLink()
        // .withDuration();
        return stringBuilder.toString();
    }
}
