public class Plan1571773435370 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
StartServer("A");
}


if ( StartServer("C") ) {
StartServer("B");
} else {
DecreaseTraffic("A");
}


}

}
}
