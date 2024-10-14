
import java.util.Comparator;

public class MySubnetComparator implements Comparator<Subnet> {
    public int compare(Subnet sn1, Subnet sn2) {
        int subnet1 = IPAddress.ipToInt(sn1.networkString);
        int subnet2 = IPAddress.ipToInt(sn2.networkString);
        return Integer.compareUnsigned(subnet1, subnet2);
    }
}
