package pl.edu.pw.elka.prm2t.kolekcje;

public abstract class AbstractBag<Item> implements Bag<Item> {
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("[");
        for (final Item i : this) {
            sb.append(i).append(" ");
        }
        if (sb.charAt(sb.length() - 1) == ' ') {
            sb.delete(sb.length() - 1, sb.length());
        }
        sb.append("]");
        return sb.toString();
    }
}
