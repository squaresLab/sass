public class Plan1571775154375 extends Plan { 
public static void main(String[] args) { 
if ( StartServer("A") ) {
DecreaseTraffic("A");
} else {
StartServer("B");
}

for (int i = 0; i < 3 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
StartServer("C");
}


}

for (int i = 0; i < 3 ; i++) {
if ( StartServer("A") ) {
StartServer("B");
} else {
StartServer("B");
}

}



}
}
