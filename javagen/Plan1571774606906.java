public class Plan1571774606906 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("B") ) {
StartServer("A");
} else {
StartServer("B");
}

if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
StartServer("A");
}


}

}
}
