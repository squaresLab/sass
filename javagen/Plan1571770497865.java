public class Plan1571770497865 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
DecreaseTraffic("A");
}

if ( StartServer("C") ) {
StartServer("B");
for (int i = 0; i < 2 ; i++) {
StartServer("A");
}


} else {
StartServer("B");
}


}

}
}
