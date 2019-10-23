public class Plan1571768831635 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
StartServer("A");
} else {
StartServer("C");
}

if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {

}


}

}
}
