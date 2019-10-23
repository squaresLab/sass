public class Plan1571774158292 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 4 ; i++) {
if ( StartServer("B") ) {
StartServer("C");
} else {
DecreaseTraffic("A");
}

}

}

StartServer("A");
if ( StartServer("C") ) {
StartServer("B");
} else {
StartServer("C");
}



}
}
