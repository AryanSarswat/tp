package seedu.address.model.event;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.person.Description;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Represents an Event in Amigos.
 * Guarantees: compulsory fields are present and not null, field values are validated, immutable.
 */
public class Event {

    // Identity fields
    private final Name name;
    private final DateTime dateTime;

    // Data fields
    private final Description description; // optional field
    private final Set<Person> friends = new HashSet<>();

    /**
     * Constructor for event with a description field.
     *
     * @param name        name of event
     * @param description description of event
     * @param dateTime    date and time of event
     */
    public Event(Name name, DateTime dateTime, Description description, Set<Person> friends) {
        requireAllNonNull(name, dateTime, description, friends);
        this.name = name;
        this.dateTime = dateTime;
        this.description = description;
        this.friends.addAll(friends);
    }

    /**
     * Constructor for event without a description field.
     *
     * @param name     name of event
     * @param dateTime date and time of event
     */
    public Event(Name name, DateTime dateTime, Set<Person> friends) {
        requireAllNonNull(name, dateTime, friends);
        this.name = name;
        this.dateTime = dateTime;
        this.description = null;
        this.friends.addAll(friends);
    }

    public Name getName() {
        return name;
    }

    private LocalDate getDate() {
        return dateTime.getDate();
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public Optional<Description> getDescription() {
        return Optional.ofNullable(description);
    }

    /**
     * Returns an immutable person set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Person> getFriends() {
        return Collections.unmodifiableSet(friends);
    }

    /**
     * Returns true if both events have the same name and date.
     * This defines a weaker notion of equality between two events and is used to
     * prevent duplication.
     *
     * @param otherEvent The other event to compare to.
     * @return True if both events have the same name and date.
     */
    public boolean isSameEvent(Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }

        return otherEvent != null
                && otherEvent.getName().equals(getName())
                && otherEvent.getDate().equals(getDate());
    }

    /**
     * Returns true if both events have the exact same fields.
     * This defines a stronger notion of equality between two events.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Event)) {
            return false;
        }

        Event otherEvent = (Event) other;
        return otherEvent.getName().equals(getName())
                && otherEvent.getDateTime().equals(getDateTime())
                && otherEvent.getDescription().equals(getDescription())
                && otherEvent.getFriends().equals(getFriends());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, dateTime, description, friends);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; DateTime: ")
                .append(getDateTime());
        if (description != null) {
            builder.append("; Description: ").append(getDescription());
        }
        Set<Person> friends = getFriends();
        if (!friends.isEmpty()) {
            builder.append("; Friends: ");
            friends.forEach(builder::append);
        }
        return builder.toString();
    }
}
