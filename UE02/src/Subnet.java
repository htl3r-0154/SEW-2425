
public class Subnet {
    String networkString;
    int subnet;
    public Subnet(String network) {
        this.networkString = network.substring(0,network.indexOf("/"));
        this.subnet = Integer.parseInt(network.substring(network.indexOf("/")+1),network.length());
    }

    public IPAddress getNet() {
        return new IPAddress(networkString.substring(networkString.indexOf("/")+1));
    }
}
