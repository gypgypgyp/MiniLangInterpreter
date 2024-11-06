package cs5004.parser;
/**
 * A generic immutable pair record in Java.
 * This record class simplifies the creation of a pair of related objects of possibly differing types.
 * It provides an immutable data carrier for the two objects.
 *
 * @param <T> the type of the first element in the pair
 * @param <U> the type of the second element in the pair
 * @param first the first element of the pair
 * @param second the second element of the pair
 */
public record Pair<T, U>(T first, U second) {
}
