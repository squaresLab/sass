public class Plan1571773966885 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");

if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
DecreaseDimmer("C");
}



StartServer("A");

}

}
}
