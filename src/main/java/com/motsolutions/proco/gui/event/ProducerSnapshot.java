package com.motsolutions.proco.gui.event;

public class ProducerSnapshot {
    String id;
    int counter;
    long time;

    public ProducerSnapshot(String id, int counter, long time) {
        this.id = id;
        this.counter = counter;
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProducerSnapshot)) return false;

        ProducerSnapshot that = (ProducerSnapshot) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Ready to produce " + id + counter + " at " + time;
    }
}
