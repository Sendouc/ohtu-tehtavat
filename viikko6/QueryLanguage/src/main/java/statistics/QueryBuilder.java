package statistics;

import java.util.ArrayList;

import statistics.matcher.All;
import statistics.matcher.HasAtLeast;
import statistics.matcher.HasFewerThan;
import statistics.matcher.Matcher;
import statistics.matcher.Or;
import statistics.matcher.PlaysIn;

public class QueryBuilder {
    ArrayList<Matcher> matchers;

    public QueryBuilder() {
        matchers = new ArrayList<>();
    }

    public QueryBuilder playsIn(String team) {
        matchers.add(new PlaysIn(team));
        return this;
    }

    public QueryBuilder hasAtLeast(int value, String category) {
        matchers.add(new HasAtLeast(value, category));
        return this;
    }

    public QueryBuilder hasFewerThan(int value, String category) {
        matchers.add(new HasFewerThan(value, category));
        return this;
    }

    public QueryBuilder oneOf(Matcher m1, Matcher m2) {
        matchers.add(new Or(m1, m2));
        return this;
    }

    public Matcher build() {
        return new All(matchers.stream().toArray(Matcher[]::new));
    }
}