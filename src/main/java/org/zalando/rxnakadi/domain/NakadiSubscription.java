package org.zalando.rxnakadi.domain;

import java.util.Collection;
import java.util.Set;

import org.zalando.rxnakadi.EventType;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableSet;

public class NakadiSubscription {

    /**
     * Reads from the oldest available event.
     */
    public static final String POSITION_BEGIN = "begin";

    public static final String POSITION_END = "end";

    /**
     * Id of subscription that was created.
     *
     * <p>Generated by Nakadi.</p>
     */
    private String id;

    /**
     * The id of application owning the subscription.
     *
     * <p>The reading and modification of subscription will be limited to this application id.</p>
     */
    private String owningApplication;

    /**
     * EventTypes to subscribe to.
     *
     * <p>Subscriptions that differ only be the order of EventTypes will be considered the same and will have the same
     * id.</p>
     */
    private Set<String> eventTypes;

    /**
     * The value describing the use case of this subscription.
     *
     * <p>In general that is an additional identifier used to differ subscriptions having the same owning_application
     * and event_types.</p>
     */
    private String consumerGroup;

    /**
     * Timestamp of creation of the subscription.
     *
     * <p>Generated by Nakadi.</p>
     */
    private String createdAt;

    /**
     * Position to start reading events from.
     *
     * <p>Applied in the moment when client starts reading from a subscription.</p>
     */
    private String readFrom;

    public NakadiSubscription() {
        // no-arg constructor for GSON
    }

    public NakadiSubscription(final String owningApplication, final Set<EventType> eventTypes,
            final String consumerGroup) {
        this(owningApplication, eventTypes, consumerGroup, POSITION_END);
    }

    public NakadiSubscription(final String owningApplication, final Set<EventType> eventTypes,
            final String consumerGroup, final String readFrom) {
        this.owningApplication = owningApplication;
        this.eventTypes = ImmutableSet.copyOf( //
                eventTypes.stream().map(EventType::toString).sorted().toArray(String[]::new));
        this.consumerGroup = consumerGroup;
        this.readFrom = readFrom;
    }

    public String getId() {
        return id;
    }

    public String getOwningApplication() {
        return owningApplication;
    }

    public void setOwningApplication(final String owningApplication) {
        this.owningApplication = owningApplication;
    }

    public Collection<String> getEventTypes() {
        return eventTypes;
    }

    public String getConsumerGroup() {
        return consumerGroup;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getReadFrom() {
        return readFrom;
    }

    @Override
    public String toString() {
        return
            MoreObjects.toStringHelper(this)                        //
                       .omitNullValues()                            //
                       .add("id", id)                               //
                       .add("owningApplication", owningApplication) //
                       .add("eventTypes", eventTypes)               //
                       .add("consumerGroup", consumerGroup)         //
                       .add("createdAt", createdAt)                 //
                       .add("readFrom", readFrom)                   //
                       .toString();
    }
}