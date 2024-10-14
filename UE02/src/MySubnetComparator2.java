
import java.util.Comparator;

public class MySubnetComparator2 implements Comparator<Subnet> {

    public int compare(Subnet sn1, Subnet sn2) {
        int subnet1 = sn1.subnet;
        int subnet2 = sn2.subnet;
        int network1 = IPAddress.ipToInt(sn1.networkString);
        int network2 = IPAddress.ipToInt(sn2.networkString);
        int cmp = Integer.compareUnsigned(subnet1, subnet2);
        if(cmp == 0){
            return Integer.compareUnsigned(network1, network2);
        }else{
            return Integer.compareUnsigned(subnet2, subnet1);
        }
    }
}
