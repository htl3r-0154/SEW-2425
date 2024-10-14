
import java.util.Objects;

public class IPAddress implements Comparable<IPAddress> {
    public String networkString;

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure {@link Integer#signum
     * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for
     * all {@code x} and {@code y}.  (This implies that {@code
     * x.compareTo(y)} must throw an exception if and only if {@code
     * y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code
     * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z))
     * == signum(y.compareTo(z))}, for all {@code z}.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     * @apiNote It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     */
    @Override
    public int compareTo(IPAddress o) {
        return Integer.compareUnsigned(ipToInt(this.ipString), ipToInt(o.ipString));
    }

    public static int ipToInt(String ipAddress) {
        String[] octets = ipAddress.split("\\.");
        int result = 0;

        // Each octet contributes 8 bits to the final integer
        for (int i = 0; i < 4; i++) {
            int octet = Integer.parseInt(octets[i]);
            result |= (octet << (24 - (i * 8)));
        }

        return result;
    }

    String ipString;

    public IPAddress(String ipString) {
        this.ipString = ipString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IPAddress ipAddress = (IPAddress) o;
        return Objects.equals(ipString, ipAddress.ipString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ipString);
    }


   /* private long ip; //ip as 32bit Integer

    public IpAddress() {
        ip = 2130706433;
    }

    public IpAddress(int ip) {
        this.ip = ip;
    }

    public IpAddress(int octett1, int octett2, int octett3, int octett4) {
        this.ip = Integer.toUnsignedLong(octett1 << 24) + + Integer.toUnsignedLong(octett2 << 16) + Integer.toUnsignedLong(octett3 << 8) + Integer.toUnsignedLong(octett4);
    }
    public IpAddress(int[] octettSet) {
        this.ip = Integer.toUnsignedLong(octettSet[0]) << 24 + + Integer.toUnsignedLong(octettSet[1] << 16) + Integer.toUnsignedLong(octettSet[2] << 8) + Integer.toUnsignedLong(octettSet[3]);
    }
    public IpAddress(String ip) {
        String[] octettSet = ip.split(".");
        this.ip = Integer.toUnsignedLong(Integer.parseInt(octettSet[0]) << 24) + Integer.toUnsignedLong(Integer.parseInt(octettSet[1]) << 16) + Integer.toUnsignedLong(Integer.parseInt(octettSet[2]) << 8) + Integer.toUnsignedLong(Integer.parseInt(octettSet[3]));
    }*/
    public int getIP(){
        return ipToInt(this.ipString);
    }
}
