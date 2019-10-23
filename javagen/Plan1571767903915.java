public class Plan1571767903915 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
DecreaseDimmer("A");
}

StartServer("B");

StartServer("A");

}

}
}
