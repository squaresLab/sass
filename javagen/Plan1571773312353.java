public class Plan1571773312353 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
DecreaseTraffic("A");
StartServer("A");
StartServer("C");


} else {
DecreaseDimmer("B");
}

StartServer("B");

}

}
}
