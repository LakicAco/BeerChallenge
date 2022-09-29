package com.aco.beerchallenge.exception;

import com.sun.istack.NotNull;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

public class ResponseStatus implements Serializable {

    @NotNull
    private Boolean status;

    private List<ResponseMessage> messages;

    @NotNull
    private OffsetDateTime timestamp;

    public ResponseStatus(@NotNull Boolean status, @NotNull OffsetDateTime timestamp) {
        this.status = status;
        this.timestamp = timestamp;
    }

    public ResponseStatus(@NotNull Boolean status, List<ResponseMessage> messages, @NotNull OffsetDateTime timestamp) {
        this.status = status;
        this.messages = messages;
        this.timestamp = timestamp;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<ResponseMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ResponseMessage> messages) {
        this.messages = messages;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResponseStatus)) return false;
        ResponseStatus that = (ResponseStatus) o;
        return status.equals(that.status) &&
                Objects.equals(messages, that.messages) &&
                timestamp.equals(that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, messages, timestamp);
    }

    @Override
    public String toString() {
        return "ResponseStatus{" +
                "status=" + status +
                ", messages=" + messages +
                ", timestamp=" + timestamp +
                '}';
    }
}
